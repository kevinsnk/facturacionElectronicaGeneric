package api.mh.facturacionElectronica.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import api.mh.facturacionElectronica.ex.model.DatosEmisor;
import api.mh.facturacionElectronica.ex.model.DatosReceptor;
import api.mh.facturacionElectronica.ex.model.EncabezadoDTE;
import api.mh.facturacionElectronica.ex.model.FelControl;
import api.mh.facturacionElectronica.ex.model.SessionToken;
import api.mh.facturacionElectronica.ex.model.anulacionDTE.DocumentoAnulacion;
import api.mh.facturacionElectronica.ex.model.anulacionDTE.DteAnulacion;
import api.mh.facturacionElectronica.ex.model.anulacionDTE.EmisorAnulacion;
import api.mh.facturacionElectronica.ex.model.anulacionDTE.IdentificacionAnulacion;
import api.mh.facturacionElectronica.ex.model.anulacionDTE.MotivoAnulacion;
import api.mh.facturacionElectronica.jdbc.DteJDBC;
import api.mh.facturacionElectronica.model.AnulacionDTERequest;
import api.mh.facturacionElectronica.model.AnulacionDTEResponse;
import api.mh.facturacionElectronica.model.FirmardocumentoAnulacionRequest;
import api.mh.facturacionElectronica.model.FirmardocumentoResponse;
import api.mh.facturacionElectronica.services.FacturaElectronicaService;
import api.mh.facturacionElectronica.util.CodigoGeneracion;
import api.mh.facturacionElectronica.util.SendEmail;

public class AnularDTEController extends AbstractController {

	private static final Logger logger = LogManager.getLogger(AnularDTEController.class);
	
	public final Properties properties = new Properties();

	@Override
	public AnulacionDTEResponse procesarDTE(Object entity, Object token, Boolean contingencia) {
		FelControl felControl = (FelControl) entity;
		SessionToken sessionToken = (SessionToken) token;
		AnulacionDTERequest anulacionDTERequest = new AnulacionDTERequest();
		AnulacionDTEResponse anulacionDTEResponse = new AnulacionDTEResponse();
		FirmardocumentoAnulacionRequest firmardocumentoAnulacionRequest = new FirmardocumentoAnulacionRequest();
		FirmardocumentoResponse firmardocumentoResponse = new FirmardocumentoResponse();
		
		String envioEmail = "NO";

		FacturaElectronicaService facturaElectronicaService = new FacturaElectronicaService();

		try {
			Gson gson = new GsonBuilder().serializeNulls().create();

			firmardocumentoAnulacionRequest = obtenerDTE(felControl, contingencia);

			String jsonEntrada = gson.toJson(firmardocumentoAnulacionRequest);
			logger.info("========================= PETICION =========================");
			logger.info(jsonEntrada);
			logger.info("========================= PETICION =========================");

			firmardocumentoResponse = facturaElectronicaService.certificarDTE(jsonEntrada);
			logger.info("Respuesta Firma DTE");
			logger.info(firmardocumentoResponse.getBody());

			anulacionDTERequest = construirAnulacionDTE(firmardocumentoResponse.getBody());
			anulacionDTEResponse = facturaElectronicaService.anularDTE(anulacionDTERequest, sessionToken.getToken());

			String jsonSalida = gson.toJson(anulacionDTEResponse);
			logger.info("========================= REPONSE =========================");
			logger.info(jsonSalida);
			logger.info("============================================================");

			jsonEntrada = gson.toJson(firmardocumentoAnulacionRequest.getDteJson());

			guardarRespuestaAnulacion(anulacionDTEResponse, felControl, jsonEntrada, envioEmail);
			
			try {
				if (anulacionDTEResponse.getEstado().equals("PROCESADO")) {
					SendEmail email = new SendEmail();
					envioEmail = email.enviarComprobanteDTE(felControl, jsonEntrada,
							firmardocumentoAnulacionRequest.getDteJson().getDocumento().getCorreo());
				}
			} catch (Exception e) {
				envioEmail = "NO";
			}
			
			editarEnvioEmail(felControl, envioEmail);
			
		} catch (Exception e) {
			logger.warn("Error en metodo procesarDTE");
			e.printStackTrace();
		}
		
		return anulacionDTEResponse;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DteAnulacion construirDTE(Object entity, Boolean contingencia) {
		FelControl felControl = (FelControl) entity;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream path = loader.getResourceAsStream("resources/config/config.properties");

		DteJDBC dteJDBC = new DteJDBC();

		// Se inicializan Models
		DatosEmisor datosEmisor = new DatosEmisor();
		DatosReceptor datosReceptor = new DatosReceptor();
		EncabezadoDTE encabezadoDTE = new EncabezadoDTE();

		DteAnulacion dte = new DteAnulacion();
		IdentificacionAnulacion identificacionDTE = new IdentificacionAnulacion();
		EmisorAnulacion emisorDTE = new EmisorAnulacion();
		DocumentoAnulacion documentoAnulacion = new DocumentoAnulacion();
		MotivoAnulacion motivoAnulacion = new MotivoAnulacion();

		try {
			// Se carga archivo properties
			properties.load(path);

			CodigoGeneracion codigoGeneracion = new CodigoGeneracion();

			datosEmisor = dteJDBC.getEmisorDTE();
			datosReceptor = dteJDBC.getReceptorDTE(felControl.getCardCode());
			encabezadoDTE = getMotivoAnulacion(felControl);

			String patternDay = "yyyy-MM-dd";
			String patternHour = "HH:mm:ss";
			SimpleDateFormat formatearFecha = new SimpleDateFormat(patternDay);
			SimpleDateFormat formatearHora = new SimpleDateFormat(patternHour);

			identificacionDTE.setAmbiente(properties.getProperty("AMBIENTE"));
			identificacionDTE.setCodigoGeneracion(codigoGeneracion.getCodigoGeneracion());
			identificacionDTE.setFecAnula(formatearFecha.format(new Date()));
			identificacionDTE.setHorAnula(formatearHora.format(new Date()));
			identificacionDTE.setVersion(Integer.valueOf(properties.getProperty("VERSION_ANULACION")));

			emisorDTE.setCodEstable(datosEmisor.getCodEstable());
			emisorDTE.setCodEstableMH(datosEmisor.getCodEstableMH());
			emisorDTE.setCodPuntoVenta(datosEmisor.getCodPuntoVenta());
			emisorDTE.setCodPuntoVentaMH(datosEmisor.getCodPuntoVentaMH());
			emisorDTE.setCorreo(datosEmisor.getCorreo());
			emisorDTE.setNit(datosEmisor.getNit());
			emisorDTE.setNombre(datosEmisor.getNombre());
			emisorDTE.setNomEstablecimiento(datosEmisor.getNombre());
			emisorDTE.setTelefono(datosEmisor.getTelefono());
			emisorDTE.setTipoEstablecimiento(datosEmisor.getTipoEstablecimiento());

			documentoAnulacion.setCodigoGeneracion(encabezadoDTE.getuECodGene());
			documentoAnulacion.setCorreo(datosReceptor.getCorreo());

			logger.info("FECHA HORA: " + encabezadoDTE.getuEFechaHora());
			documentoAnulacion.setFecEmi(formatearFecha.format(encabezadoDTE.getuEFechaHora()));

			documentoAnulacion.setMontoIva(encabezadoDTE.getIva());
			documentoAnulacion.setNombre(datosReceptor.getNombre());
			documentoAnulacion.setNumDocumento(datosReceptor.getNumDocumento());
			documentoAnulacion.setNumeroControl(encabezadoDTE.getuENumCont());
			documentoAnulacion.setSelloRecibido(encabezadoDTE.getuESellRecep());
			documentoAnulacion.setTelefono(datosReceptor.getTelefono());
			documentoAnulacion.setTipoDocumento(datosReceptor.getTipoDocumento());

			String tipoDocu = "";
			switch (felControl.uTipoDocAnul) {
			case "OINV":
				tipoDocu = dteJDBC.getTipoDoc(felControl.getuDocEntry());
				break;
			case "ORIN":
				tipoDocu = "NC";
				break;
			case "ODLN":
				tipoDocu = "NRE";
				break;
			case "OPCH":
				tipoDocu = "FSE";
				break;
			default:
				break;
			}

			documentoAnulacion.setTipoDte(obtenerTipoDocumento(tipoDocu, properties));

			motivoAnulacion.setTipoAnulacion(encabezadoDTE.getTipoAnulacion());
			motivoAnulacion.setMotivoAnulacion(encabezadoDTE.getMotivoAnulacion());
			motivoAnulacion.setNombreResponsable(encabezadoDTE.getNombreResponsable());
			motivoAnulacion.setNombreSolicita(encabezadoDTE.getNombreSolicita());
			motivoAnulacion.setNumDocResponsable(encabezadoDTE.getNumDocResponsable());
			motivoAnulacion.setNumDocSolicita(encabezadoDTE.getNumDocSolicita());
			motivoAnulacion.setTipDocResponsable(encabezadoDTE.getTipDocResponsable());
			motivoAnulacion.setTipDocSolicita(encabezadoDTE.getTipDocSolicita());

			// Se setean los valores a la clase DteAnulacion
			dte.setIdentificacion(identificacionDTE);
			dte.setEmisor(emisorDTE);
			dte.setDocumento(documentoAnulacion);
			dte.setMotivo(motivoAnulacion);

		} catch (IOException e) {
			logger.warn("Error en metodo construirDTE");
			e.printStackTrace();
		}

		return dte;
	}

	public AnulacionDTERequest construirAnulacionDTE(String documento) {
		AnulacionDTERequest anulacionDTERequest = new AnulacionDTERequest();

		anulacionDTERequest.setAmbiente(properties.getProperty("AMBIENTE"));
		anulacionDTERequest.setIdEnvio(1);
		anulacionDTERequest.setVersion(Integer.valueOf(properties.getProperty("VERSION_ANULACION")));
		anulacionDTERequest.setDocumento(documento);

		return anulacionDTERequest;
	}

	@SuppressWarnings("unchecked")
	@Override
	public FirmardocumentoAnulacionRequest obtenerDTE(Object entity, Boolean contingencia) {
		FelControl felControl = (FelControl) entity;
		FirmardocumentoAnulacionRequest anulacionRequest = new FirmardocumentoAnulacionRequest();
		DteAnulacion documento = new DteAnulacion();

		documento = construirDTE(felControl, contingencia);

		anulacionRequest.setNit(properties.getProperty("USER_AUTH"));
		anulacionRequest.setPasswordPri(properties.getProperty("PSW_AUTH"));
		anulacionRequest.setActivo(true);
		anulacionRequest.setDteJson(documento);

		return anulacionRequest;
	}

	public EncabezadoDTE getMotivoAnulacion(FelControl felControl) {
		EncabezadoDTE encabezadoDTE = new EncabezadoDTE();
		DteJDBC dteJDBC = new DteJDBC();
		switch (felControl.getuTipoDocAnul()) {
		case "OINV":
			encabezadoDTE = dteJDBC.getMotivoCancelacionOINV(felControl.getuDocEntry());
			break;
		case "ORIN":
			encabezadoDTE = dteJDBC.getMotivoCancelacionORIN(felControl.getuDocEntry());
			break;
		case "ODLN":
			encabezadoDTE = dteJDBC.getMotivoCancelacionODLN(felControl.getuDocEntry());
			break;
		case "OPCH":
			encabezadoDTE = dteJDBC.getMotivoCancelacionOPCH(felControl.getuDocEntry());
			break;
		default:
			break;
		}
		return encabezadoDTE;
	}

	@Override
	public <T> T construirRecepcionDte(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
