package api.mh.facturacionElectronica.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import api.mh.facturacionElectronica.ex.model.DatosEmisor;
import api.mh.facturacionElectronica.ex.model.DatosReceptor;
import api.mh.facturacionElectronica.ex.model.DetalleDTE;
import api.mh.facturacionElectronica.ex.model.EncabezadoDTE;
import api.mh.facturacionElectronica.ex.model.FelControl;
import api.mh.facturacionElectronica.ex.model.PagosDTE;
import api.mh.facturacionElectronica.ex.model.SessionToken;
import api.mh.facturacionElectronica.ex.model.fex.CuerpoDocumentoDTE;
import api.mh.facturacionElectronica.ex.model.fex.DTE;
import api.mh.facturacionElectronica.ex.model.fex.EmisorDTE;
import api.mh.facturacionElectronica.ex.model.fex.IdentificacionDTE;
import api.mh.facturacionElectronica.ex.model.fex.ReceptorDTE;
import api.mh.facturacionElectronica.ex.model.fex.ResumenDTE;
import api.mh.facturacionElectronica.jdbc.DteJDBC;
import api.mh.facturacionElectronica.model.FirmardocumentoFEXRequest;
import api.mh.facturacionElectronica.model.FirmardocumentoResponse;
import api.mh.facturacionElectronica.model.RecepcionDTERequest;
import api.mh.facturacionElectronica.model.RecepcionDTEResponse;
import api.mh.facturacionElectronica.services.FacturaElectronicaService;
import api.mh.facturacionElectronica.util.CodigoGeneracion;
import api.mh.facturacionElectronica.util.SendEmail;

public class FexController extends AbstractController {

	private static final Logger logger = LogManager.getLogger(FexController.class);
	
	public final Properties properties = new Properties();

	@Override
	public void procesarDTE(Object entity, Object token, Boolean contingencia) {
		FelControl felControl = (FelControl) entity;
		SessionToken sessionToken = (SessionToken) token;
		FirmardocumentoFEXRequest firmardocumentoRequest = new FirmardocumentoFEXRequest();
		FirmardocumentoResponse firmardocumentoResponse = new FirmardocumentoResponse();
		RecepcionDTERequest recepcionDTERequest = new RecepcionDTERequest();
		RecepcionDTEResponse recepcionDTEResponse = new RecepcionDTEResponse();
		String envioEmail = "NO";

		FacturaElectronicaService facturaElectronicaService = new FacturaElectronicaService();

		try {
			Gson gson = new GsonBuilder().serializeNulls().create();

			firmardocumentoRequest = obtenerDTE(felControl, contingencia);
			String jsonEntrada = gson.toJson(firmardocumentoRequest);
			logger.info("========================= PETICION =========================");
			logger.info(jsonEntrada);
			logger.info("============================================================");

			firmardocumentoResponse = facturaElectronicaService.certificarDTE(jsonEntrada);
			logger.info("Respuesta Firma DTE");
			logger.info(firmardocumentoResponse.getBody());
			recepcionDTERequest = construirRecepcionDte(firmardocumentoResponse.getBody());
			recepcionDTEResponse = facturaElectronicaService.recepcionDTE(recepcionDTERequest, sessionToken.getToken());

			String jsonSalida = gson.toJson(recepcionDTEResponse);
			logger.info("========================= REPONSE =========================");
			logger.info(jsonSalida);
			logger.info("===========================================================");

			jsonEntrada = gson.toJson(firmardocumentoRequest.getDteJson());

			guardarRespuestaMH(recepcionDTEResponse, felControl, firmardocumentoResponse.getBody(),
					firmardocumentoRequest.getDteJson().getIdentificacion().getNumeroControl(), jsonEntrada,
					contingencia, envioEmail);

			try {
				if (recepcionDTEResponse.getEstado().equals("PROCESADO")) {
					SendEmail email = new SendEmail();
					envioEmail = email.enviarComprobanteDTE(felControl, jsonEntrada,
							firmardocumentoRequest.getDteJson().getReceptor().getCorreo());
				}
			} catch (Exception e) {
				envioEmail = "NO";
			}

			editarEnvioEmail(felControl, envioEmail);
		} catch (Exception e) {
			logger.error("Error en metodo procesarDTE");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public FirmardocumentoFEXRequest obtenerDTE(Object entity, Boolean contingencia) {
		FelControl felControl = (FelControl) entity;
		FirmardocumentoFEXRequest firmardocumentoRequest = new FirmardocumentoFEXRequest();
		DTE dte = new DTE();

		dte = construirDTE(felControl, contingencia);

		firmardocumentoRequest.setNit(properties.getProperty("USER_AUTH"));
		firmardocumentoRequest.setPasswordPri(properties.getProperty("PSW_AUTH"));
		firmardocumentoRequest.setActivo(true);
		firmardocumentoRequest.setDteJson(dte);

		return firmardocumentoRequest;
	}

	@SuppressWarnings("unchecked")
	@Override
	public RecepcionDTERequest construirRecepcionDte(String documentoFirmado) {
		RecepcionDTERequest recepcionDTERequest = new RecepcionDTERequest();

		recepcionDTERequest.setAmbiente(properties.getProperty("AMBIENTE"));
		recepcionDTERequest.setIdEnvio(1);
		recepcionDTERequest.setVersion(Integer.valueOf(properties.getProperty("VERSION_FEX")));
		recepcionDTERequest.setTipoDte(properties.getProperty("DTE_FAE"));
		recepcionDTERequest.setDocumento(documentoFirmado);

		return recepcionDTERequest;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DTE construirDTE(Object entity, Boolean contingencia) {
		FelControl felControl = (FelControl) entity;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream path = loader.getResourceAsStream("resources/config/config.properties");

		DteJDBC dteJDBC = new DteJDBC();

		// Se inicializan Models
		DatosEmisor datosEmisor = new DatosEmisor();
		DatosReceptor datosReceptor = new DatosReceptor();
		ArrayList<DetalleDTE> listaDetallesDTE = new ArrayList<DetalleDTE>();

		DTE dte = new DTE();
		EncabezadoDTE encabezadoDTE = new EncabezadoDTE();
		EmisorDTE emisorDTE = new EmisorDTE();
		ReceptorDTE receptorDTE = new ReceptorDTE();
		// VentaTerceroDTE ventaTerceroDTE = new VentaTerceroDTE();
		ResumenDTE resumenDTE = new ResumenDTE();
		ArrayList<CuerpoDocumentoDTE> listaDocumentos = new ArrayList<CuerpoDocumentoDTE>();

		String patternDay = "yyyy-MM-dd";
		String patternHour = "hh:mm:ss";
		SimpleDateFormat formatearFecha = new SimpleDateFormat(patternDay);
		SimpleDateFormat formatearHora = new SimpleDateFormat(patternHour);

		try {
			// Se carga archivo properties
			properties.load(path);

			encabezadoDTE = dteJDBC.getEncabezadoDTE(felControl.getuDocEntry());

			String numeroControl = "DTE-" + properties.getProperty("DTE_FAE") + "-" + "B001P001" + "-"
					+ ("000000000000000" + felControl.getuNumero()).substring(felControl.getuNumero().length());

			CodigoGeneracion codigoGeneracion = new CodigoGeneracion();

			IdentificacionDTE identificacionDTE = new IdentificacionDTE();
			identificacionDTE.setVersion(Integer.valueOf(properties.getProperty("VERSION_FEX")));
			identificacionDTE.setAmbiente(properties.getProperty("AMBIENTE"));
			identificacionDTE.setTipoDte(properties.getProperty("DTE_FAE"));
			identificacionDTE.setNumeroControl(numeroControl);
			if (felControl.getuECodGene() == null || felControl.getuECodGene().isEmpty()) {
				identificacionDTE.setCodigoGeneracion(codigoGeneracion.getCodigoGeneracion());
			} else {
				identificacionDTE.setCodigoGeneracion(felControl.getuECodGene());
			}

			if (!contingencia) {
				identificacionDTE.setTipoModelo(1);
				identificacionDTE.setTipoOperacion(1);
				identificacionDTE.setTipoContingencia(null);
				identificacionDTE.setMotivoContingencia(null);
				identificacionDTE.setFecEmi(formatearFecha.format(new Date()));
				identificacionDTE.setHorEmi(formatearHora.format(new Date()));
			} else {
				identificacionDTE.setTipoModelo(2);
				identificacionDTE.setTipoOperacion(2);
				identificacionDTE.setTipoContingencia(BigDecimal.valueOf(3));
				identificacionDTE.setMotivoContingencia("Prueba del sistema para contingencia");
				identificacionDTE.setFecEmi(formatearFecha.format(felControl.getuFechaIn()));
				identificacionDTE.setHorEmi(formatearHora.format(new Date()));
			}

			identificacionDTE.setTipoMoneda(properties.getProperty("DTE_MONEDA"));

			datosEmisor = dteJDBC.getEmisorDTE();
			datosReceptor = dteJDBC.getReceptorDTE(encabezadoDTE.getCodReceptor());
			listaDetallesDTE = dteJDBC.getDetalleDTE(felControl.getuDocEntry());

			emisorDTE.setNit(datosEmisor.getNit());
			emisorDTE.setNrc(datosEmisor.getNrc());
			emisorDTE.setNombre(datosEmisor.getNombre());
			emisorDTE.setCodActividad(datosEmisor.getCodActividad());
			emisorDTE.setDescActividad(datosEmisor.getDescActividad());
			emisorDTE.setNombreComercial(datosEmisor.getNombreComercial());
			emisorDTE.setTipoEstablecimiento(datosEmisor.getTipoEstablecimiento());
			emisorDTE.setDireccion(datosEmisor.getDireccion());
			emisorDTE.setTelefono(datosEmisor.getTelefono());
			emisorDTE.setCorreo(datosEmisor.getCorreo());
			emisorDTE.setCodEstableMH(datosEmisor.getCodEstableMH());
			emisorDTE.setCodEstable(datosEmisor.getCodEstable());
			emisorDTE.setCodPuntoVentaMH(datosEmisor.getCodPuntoVentaMH());
			emisorDTE.setCodPuntoVenta(datosEmisor.getCodPuntoVenta());

			emisorDTE.setTipoItemExpor(encabezadoDTE.getTipoItem());
			emisorDTE.setRecintoFiscal(null);
			emisorDTE.setRegimen(null);

			receptorDTE.setNombre(datosReceptor.getNombre());
			receptorDTE.setTipoDocumento(datosReceptor.getTipoDocumento());
			receptorDTE.setNumDocumento(datosReceptor.getNumDocumento());
			receptorDTE.setNombreComercial(datosReceptor.getNombreComercial());
			receptorDTE.setCodPais(datosReceptor.getCodPais());
			receptorDTE.setNombrePais(datosReceptor.getNombrePais());
			receptorDTE.setComplemento(datosReceptor.getDireccion().getComplemento());
			receptorDTE.setTipoPersona(datosReceptor.getTipoPersona());
			receptorDTE.setDescActividad(datosReceptor.getDescActividad());
			receptorDTE.setTelefono(datosReceptor.getTelefono());
			receptorDTE.setCorreo(datosReceptor.getCorreo());

			for (DetalleDTE detalleDTE : listaDetallesDTE) {
				CuerpoDocumentoDTE cuerpoDocumentoDTE = new CuerpoDocumentoDTE();
				cuerpoDocumentoDTE.setNumItem(detalleDTE.getNumItem());
				cuerpoDocumentoDTE.setCantidad(detalleDTE.getCantidad());
				cuerpoDocumentoDTE.setCodigo(detalleDTE.getCodigo());
				cuerpoDocumentoDTE.setUniMedida(detalleDTE.getUniMedida());
				cuerpoDocumentoDTE.setDescripcion(detalleDTE.getDescripcion());
				cuerpoDocumentoDTE.setPrecioUni(detalleDTE.getPrecioUni());
				cuerpoDocumentoDTE.setMontoDescu(detalleDTE.getMontoDescu());
				cuerpoDocumentoDTE.setVentaGravada(detalleDTE.getVentaGravada());

				ArrayList<String> listaTributos = new ArrayList<String>();
				listaTributos.add("C3");
				cuerpoDocumentoDTE.setTributos(listaTributos);

				cuerpoDocumentoDTE.setNoGravado(detalleDTE.getNoGravado());

				listaDocumentos.add(cuerpoDocumentoDTE);
			}

			resumenDTE.setTotalGravada(encabezadoDTE.getTotalGravada());
			resumenDTE.setDescuento(encabezadoDTE.getDescuento());
			resumenDTE.setPorcentajeDescuento(encabezadoDTE.getPorcentajeDescuento());
			resumenDTE.setTotalDescu(encabezadoDTE.getTotalDescu());
			resumenDTE.setSeguro(encabezadoDTE.getSeguro());
			resumenDTE.setFlete(encabezadoDTE.getFlete());
			resumenDTE.setMontoTotalOperacion(encabezadoDTE.getMontoTotalOperacion());
			resumenDTE.setTotalNoGravado(encabezadoDTE.getTotalNoGravado());
			resumenDTE.setTotalPagar(encabezadoDTE.getTotalPagar());
			resumenDTE.setTotalLetras(encabezadoDTE.getTotalLetras());
			resumenDTE.setCondicionOperacion(encabezadoDTE.getCondicionOperacion());

			PagosDTE pagosDTE = new PagosDTE();
			ArrayList<PagosDTE> listaPagos = new ArrayList<PagosDTE>();

			pagosDTE.setCodigo("01");
			pagosDTE.setMontoPago(resumenDTE.getMontoTotalOperacion());
			pagosDTE.setPlazo(null);
			pagosDTE.setReferencia("");
			listaPagos.add(pagosDTE);

			resumenDTE.setPagos(listaPagos);
			resumenDTE.setCodIncoterms(encabezadoDTE.getCodIncoterms());
			resumenDTE.setDescIncoterms(encabezadoDTE.getDescIncoterms());
			resumenDTE.setNumPagoElectronico(encabezadoDTE.getNumPagoElectronico());
			resumenDTE.setObservaciones(encabezadoDTE.getObservaciones());

			// Se setean los valores a la clase DTE
			dte.setIdentificacion(identificacionDTE);
			dte.setEmisor(emisorDTE);
			dte.setReceptor(receptorDTE);
			dte.setOtrosDocumentos(null);
			dte.setVentaTercero(null);
			dte.setCuerpoDocumento(listaDocumentos);
			dte.setResumen(resumenDTE);
			dte.setApendice(null);

		} catch (IOException e) {
			logger.error("Error en metodo construirDTE");
			e.printStackTrace();
		}

		return dte;
	}
}
