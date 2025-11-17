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
import api.mh.facturacionElectronica.ex.model.IdentificacionDTE;
import api.mh.facturacionElectronica.ex.model.PagosDTE;
import api.mh.facturacionElectronica.ex.model.SessionToken;
import api.mh.facturacionElectronica.ex.model.fse.ApendiceDTE;
import api.mh.facturacionElectronica.ex.model.fse.CuerpoDocumentoDTE;
import api.mh.facturacionElectronica.ex.model.fse.DTE;
import api.mh.facturacionElectronica.ex.model.fse.EmisorDTE;
import api.mh.facturacionElectronica.ex.model.fse.ResumenDTE;
import api.mh.facturacionElectronica.ex.model.fse.SujetoExcluidoDTE;
import api.mh.facturacionElectronica.jdbc.DteJDBC;
import api.mh.facturacionElectronica.model.FirmardocumentoFSERequest;
import api.mh.facturacionElectronica.model.FirmardocumentoResponse;
import api.mh.facturacionElectronica.model.RecepcionDTERequest;
import api.mh.facturacionElectronica.model.RecepcionDTEResponse;
import api.mh.facturacionElectronica.services.FacturaElectronicaService;
import api.mh.facturacionElectronica.util.CodigoGeneracion;
import api.mh.facturacionElectronica.util.SendEmail;

public class FseController extends AbstractController {

	private static final Logger logger = LogManager.getLogger(FseController.class);
	
	public final Properties properties = new Properties();

	@Override
	public RecepcionDTEResponse procesarDTE(Object entity, Object token, Boolean contingencia) {
		FelControl felControl = (FelControl) entity;
		SessionToken sessionToken = (SessionToken) token;
		FirmardocumentoFSERequest firmardocumentoRequest = new FirmardocumentoFSERequest();
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
							firmardocumentoRequest.getDteJson().getSujetoExcluido().getCorreo());
				}
			} catch (Exception e) {
				envioEmail = "NO";
			}

			editarEnvioEmail(felControl, envioEmail);
		} catch (Exception e) {
			logger.error("Error en metodo procesarDTE");
			recepcionDTEResponse = null;
			e.printStackTrace();
		}
		
		return recepcionDTEResponse;
	}

	@SuppressWarnings("unchecked")
	@Override
	public FirmardocumentoFSERequest obtenerDTE(Object entity, Boolean contingencia) {
		FelControl felControl = (FelControl) entity;
		FirmardocumentoFSERequest firmardocumentoRequest = new FirmardocumentoFSERequest();
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
		SujetoExcluidoDTE sujetoExcluidoDTE = new SujetoExcluidoDTE();
		ResumenDTE resumenDTE = new ResumenDTE();
		ArrayList<CuerpoDocumentoDTE> listaDocumentos = new ArrayList<CuerpoDocumentoDTE>();
		ArrayList<ApendiceDTE> apendiceDTE = null;

		String patternDay = "yyyy-MM-dd";
		String patternHour = "hh:mm:ss";
		SimpleDateFormat formatearFecha = new SimpleDateFormat(patternDay);
		SimpleDateFormat formatearHora = new SimpleDateFormat(patternHour);

		try {
			// Se carga archivo properties
			properties.load(path);

			encabezadoDTE = dteJDBC.getEncabezadoFSE(felControl.getuDocEntry());

			String numeroControl = "DTE-" + properties.getProperty("DTE_FSE") + "-" + "B001P001" + "-"
					+ ("000000000000000" + felControl.getuNumero()).substring(felControl.getuNumero().length());

			CodigoGeneracion codigoGeneracion = new CodigoGeneracion();

			IdentificacionDTE identificacionDTE = new IdentificacionDTE();
			identificacionDTE.setVersion(Integer.valueOf(properties.getProperty("VERSION_FSE")));
			identificacionDTE.setAmbiente(properties.getProperty("AMBIENTE"));
			identificacionDTE.setTipoDte(properties.getProperty("DTE_FSE"));
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
				identificacionDTE.setMotivoContin(null);
				identificacionDTE.setFecEmi(formatearFecha.format(new Date()));
				identificacionDTE.setHorEmi(formatearHora.format(new Date()));
			} else {
				identificacionDTE.setTipoModelo(2);
				identificacionDTE.setTipoOperacion(2);
				identificacionDTE.setTipoContingencia(BigDecimal.valueOf(3));
				identificacionDTE.setMotivoContin("Prueba del sistema para contingencia");
				identificacionDTE.setFecEmi(formatearFecha.format(felControl.getuFechaIn()));
				identificacionDTE.setHorEmi(formatearHora.format(new Date()));
			}
			identificacionDTE.setTipoMoneda(properties.getProperty("DTE_MONEDA"));

			datosEmisor = dteJDBC.getEmisorDTE();
			datosReceptor = dteJDBC.getReceptorDTE(encabezadoDTE.getCodReceptor());
			listaDetallesDTE = dteJDBC.getDetalleFSE(felControl.getuDocEntry());

			emisorDTE.setNit(datosEmisor.getNit());
			emisorDTE.setNrc(datosEmisor.getNrc());
			emisorDTE.setNombre(datosEmisor.getNombre());
			emisorDTE.setCodActividad(datosEmisor.getCodActividad());
			emisorDTE.setDescActividad(datosEmisor.getDescActividad());
			emisorDTE.setDireccion(datosEmisor.getDireccion());
			emisorDTE.setTelefono(datosEmisor.getTelefono());
			emisorDTE.setCorreo(datosEmisor.getCorreo());
			emisorDTE.setCodEstableMH(datosEmisor.getCodEstableMH());
			emisorDTE.setCodEstable(datosEmisor.getCodEstable());
			emisorDTE.setCodPuntoVentaMH(datosEmisor.getCodPuntoVentaMH());
			emisorDTE.setCodPuntoVenta(datosEmisor.getCodPuntoVenta());

			sujetoExcluidoDTE.setTipoDocumento(datosReceptor.getTipoDocumento());
			sujetoExcluidoDTE.setNumDocumento(datosReceptor.getNumDocumento());
			sujetoExcluidoDTE.setNombre(datosReceptor.getNombre());
			sujetoExcluidoDTE.setCodActividad(datosReceptor.getCodActividad());
			sujetoExcluidoDTE.setDescActividad(datosReceptor.getDescActividad());
			sujetoExcluidoDTE.setDireccion(datosReceptor.getDireccion());
			sujetoExcluidoDTE.setTelefono(datosReceptor.getTelefono());
			sujetoExcluidoDTE.setCorreo(datosReceptor.getCorreo());

			for (DetalleDTE detalleDTE : listaDetallesDTE) {
				CuerpoDocumentoDTE cuerpoDocumentoDTE = new CuerpoDocumentoDTE();
				cuerpoDocumentoDTE.setNumItem(detalleDTE.getNumItem());
				cuerpoDocumentoDTE.setTipoItem(detalleDTE.getTipoItem());
				cuerpoDocumentoDTE.setCantidad(detalleDTE.getCantidad());
				cuerpoDocumentoDTE.setCodigo(detalleDTE.getCodigo());
				cuerpoDocumentoDTE.setUniMedida(detalleDTE.getUniMedida());
				cuerpoDocumentoDTE.setDescripcion(detalleDTE.getDescripcion());
				cuerpoDocumentoDTE.setPrecioUni(detalleDTE.getPrecioUni());
				cuerpoDocumentoDTE.setMontoDescu(detalleDTE.getMontoDescu());
				cuerpoDocumentoDTE.setCompra(detalleDTE.getVentaGravada());

				listaDocumentos.add(cuerpoDocumentoDTE);
			}

			resumenDTE.setTotalCompra(encabezadoDTE.getMontoTotalOperacion());
			resumenDTE.setDescu(encabezadoDTE.getDescuento());
			resumenDTE.setTotalDescu(encabezadoDTE.getTotalDescu());
			resumenDTE.setSubTotal(encabezadoDTE.getSubTotal());
			resumenDTE.setIvaRete1(encabezadoDTE.getIvaRete1());
			resumenDTE.setReteRenta(encabezadoDTE.getReteRenta());
			resumenDTE.setTotalPagar(encabezadoDTE.getTotalPagar());
			resumenDTE.setTotalLetras(encabezadoDTE.getTotalLetras());
			resumenDTE.setCondicionOperacion(encabezadoDTE.getCondicionOperacion());
			resumenDTE.setObservaciones(encabezadoDTE.getObservaciones());

			PagosDTE pagosDTE = new PagosDTE();
			ArrayList<PagosDTE> listaPagos = new ArrayList<PagosDTE>();

			pagosDTE.setCodigo("01");
			pagosDTE.setMontoPago(resumenDTE.getTotalPagar());
			pagosDTE.setPlazo(null);
			pagosDTE.setReferencia("");
			listaPagos.add(pagosDTE);

			resumenDTE.setPagos(listaPagos);

			// Se setean los valores a la clase DTE
			dte.setIdentificacion(identificacionDTE);
			dte.setEmisor(emisorDTE);
			dte.setSujetoExcluido(sujetoExcluidoDTE);
			dte.setCuerpoDocumento(listaDocumentos);
			dte.setResumen(resumenDTE);
			dte.setApendice(apendiceDTE);

		} catch (IOException e) {
			logger.error("Error en metodo construirDTE");
			e.printStackTrace();
		}

		return dte;
	}

	@SuppressWarnings("unchecked")
	@Override
	public RecepcionDTERequest construirRecepcionDte(String documentoFirmado) {
		RecepcionDTERequest recepcionDTERequest = new RecepcionDTERequest();

		recepcionDTERequest.setAmbiente(properties.getProperty("AMBIENTE"));
		recepcionDTERequest.setIdEnvio(1);
		recepcionDTERequest.setVersion(Integer.valueOf(properties.getProperty("VERSION_FSE")));
		recepcionDTERequest.setTipoDte(properties.getProperty("DTE_FSE"));
		recepcionDTERequest.setDocumento(documentoFirmado);

		return recepcionDTERequest;
	}

}
