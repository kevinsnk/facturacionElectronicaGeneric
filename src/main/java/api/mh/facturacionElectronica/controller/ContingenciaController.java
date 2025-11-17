package api.mh.facturacionElectronica.controller;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import api.mh.facturacionElectronica.ex.model.DatosEmisor;
import api.mh.facturacionElectronica.ex.model.FeContingencia;
import api.mh.facturacionElectronica.ex.model.FelControl;
import api.mh.facturacionElectronica.ex.model.SessionToken;
import api.mh.facturacionElectronica.ex.model.contingencia.DTE;
import api.mh.facturacionElectronica.ex.model.contingencia.DetalleDTE;
import api.mh.facturacionElectronica.ex.model.contingencia.EmisorDTE;
import api.mh.facturacionElectronica.ex.model.contingencia.IdentificacionDTE;
import api.mh.facturacionElectronica.ex.model.contingencia.Motivo;
import api.mh.facturacionElectronica.jdbc.DteJDBC;
import api.mh.facturacionElectronica.jdbc.FelControlJDBC;
import api.mh.facturacionElectronica.model.ContingenciaRequest;
import api.mh.facturacionElectronica.model.ContingenciaResponse;
import api.mh.facturacionElectronica.model.FirmaContingenciaRequest;
import api.mh.facturacionElectronica.model.FirmardocumentoResponse;
import api.mh.facturacionElectronica.services.FacturaElectronicaService;
import api.mh.facturacionElectronica.util.CodigoGeneracion;

public class ContingenciaController extends AbstractController {

	private static final Logger logger = LogManager.getLogger(ContingenciaController.class);
	
	public final Properties properties = new Properties();

	@Override
	public ContingenciaResponse procesarDTE(Object entity, Object token, Boolean contingencia) {
		FeContingencia feContingencia = (FeContingencia) entity;
		SessionToken sessionToken = (SessionToken) token;
		FirmaContingenciaRequest firmaContingenciaRequest = new FirmaContingenciaRequest();
		FirmardocumentoResponse firmardocumentoResponse = new FirmardocumentoResponse();
		ContingenciaRequest recepcionDTERequest = new ContingenciaRequest();
		ContingenciaResponse recepcionDTEResponse = new ContingenciaResponse();

		FacturaElectronicaService facturaElectronicaService = new FacturaElectronicaService();

		try {
			Gson gson = new GsonBuilder().serializeNulls().create();

			firmaContingenciaRequest = obtenerDTE(feContingencia, contingencia);
			String jsonEntrada = gson.toJson(firmaContingenciaRequest);
			logger.info("========================= PETICION CONTINGENCIA =========================");
			logger.info(jsonEntrada);
			logger.info("=========================================================================");

			firmardocumentoResponse = facturaElectronicaService.certificarDTE(jsonEntrada);
			logger.info("Respuesta Firma contingencia");
			logger.info(firmardocumentoResponse.getBody());
			recepcionDTERequest = construirRecepcionDte(firmardocumentoResponse.getBody());
			recepcionDTEResponse = facturaElectronicaService.contingenciaDte(recepcionDTERequest,
					sessionToken.getToken());

			String jsonSalida = gson.toJson(recepcionDTEResponse);
			logger.info("========================= REPONSE CONTINGENCIA =========================");
			logger.info(jsonSalida);
			logger.info("========================================================================");

			jsonEntrada = gson.toJson(firmaContingenciaRequest.getDteJson());

			guardarRespuestaContingencia(feContingencia, recepcionDTEResponse, jsonEntrada);
		} catch (Exception e) {
			logger.error("Error en metodo procesarDTE");
			recepcionDTEResponse = null;
			e.printStackTrace();
		}
		
		return recepcionDTEResponse;

	}

	@SuppressWarnings("unchecked")
	@Override
	public FirmaContingenciaRequest obtenerDTE(Object entity, Boolean contigencia) {
		FeContingencia feContingencia = (FeContingencia) entity;
		FirmaContingenciaRequest firmaContingenciaRequest = new FirmaContingenciaRequest();
		DTE dte = new DTE();

		dte = construirDTE(feContingencia, contigencia);

		firmaContingenciaRequest.setNit(properties.getProperty("USER_AUTH"));
		firmaContingenciaRequest.setPasswordPri(properties.getProperty("PSW_AUTH"));
		firmaContingenciaRequest.setActivo(true);
		firmaContingenciaRequest.setDteJson(dte);

		return firmaContingenciaRequest;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DTE construirDTE(Object entity, Boolean contigencia) {
		FeContingencia feContingencia = (FeContingencia) entity;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream path = loader.getResourceAsStream("resources/config/config.properties");

		DteJDBC dteJDBC = new DteJDBC();
		FelControlJDBC felControlJDBC = new FelControlJDBC();

		DatosEmisor datosEmisor = new DatosEmisor();

		// Se inicializan Models
		List<DetalleDTE> listaDetallesDTE = new ArrayList<DetalleDTE>();
		List<FelControl> listaFelControl = new ArrayList<FelControl>();

		DTE dte = new DTE();
		IdentificacionDTE identificacionDTE = new IdentificacionDTE();
		EmisorDTE emisorDTE = new EmisorDTE();
		Motivo motivo = new Motivo();

		String patternDay = "yyyy-MM-dd";
		String patternHour = "HH:mm:ss";
		SimpleDateFormat formatearFecha = new SimpleDateFormat(patternDay);
		SimpleDateFormat formatearHora = new SimpleDateFormat(patternHour);

		try {
			// Se carga archivo properties
			properties.load(path);

			datosEmisor = dteJDBC.getEmisorDTE();
			listaFelControl = felControlJDBC.listDteVinculadosContingencia(feContingencia.getCode());

			CodigoGeneracion codigoGeneracion = new CodigoGeneracion();

			identificacionDTE.setVersion(Integer.valueOf(properties.getProperty("VERSION_CONTINGENCIA")));
			identificacionDTE.setAmbiente(properties.getProperty("AMBIENTE"));
			identificacionDTE.setCodigoGeneracion(codigoGeneracion.getCodigoGeneracion());
			identificacionDTE.setfTransmision(formatearFecha.format(new Date()));
			identificacionDTE.sethTransmision(formatearHora.format(new Date()));

			emisorDTE.setNit(datosEmisor.getNit());
			emisorDTE.setNombre(datosEmisor.getNombre());
			emisorDTE.setCorreo(datosEmisor.getCorreo());
			emisorDTE.setNombreResponsable(datosEmisor.getNombre());
			emisorDTE.setTipoDocResponsable("36");
			emisorDTE.setNumeroDocResponsable(datosEmisor.getNit());
			emisorDTE.setTelefono(datosEmisor.getTelefono());
			emisorDTE.setTipoEstablecimiento(datosEmisor.getTipoEstablecimiento());
			emisorDTE.setCodEstableMH(null);
			emisorDTE.setCodPuntoVenta(null);

			motivo.setMotivoContingencia(feContingencia.getuMotivo());
			motivo.setTipoContingencia(feContingencia.getuContingencia());
			motivo.setfInicio(formatearFecha.format(feContingencia.getuFechaIni()));
			motivo.sethInicio(feContingencia.getuHoraIni() + ":00:00");
			motivo.setfFin(formatearFecha.format(feContingencia.getuFechaFin()));
			motivo.sethFin(feContingencia.getuHoraFin() + ":00:00");

			for (FelControl detalle : listaFelControl) {
				String tipoDTE = obtenerTipoDocumento(detalle.getuTipo(), properties);
				detalle.setuTipo(tipoDTE);

				if (detalle.getuECodGene() == null) {
					detalle.setuECodGene(codigoGeneracion.getCodigoGeneracion());
					felControlJDBC.editConGeneracionContingencia(detalle);
				}
				DetalleDTE detalleDTE = new DetalleDTE();
				detalleDTE.setNoItem(detalle.getNoItem());
				detalleDTE.setCodigoGeneracion(detalle.getuECodGene());
				detalleDTE.setTipoDoc(detalle.getuTipo());

				listaDetallesDTE.add(detalleDTE);
			}

			dte.setIdentificacion(identificacionDTE);
			dte.setEmisor(emisorDTE);
			dte.setDetalleDTE(listaDetallesDTE);
			dte.setMotivo(motivo);

		} catch (Exception e) {
			logger.error("Error en metodo procesarDTE");
			e.printStackTrace();
		}
		return dte;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ContingenciaRequest construirRecepcionDte(String documentoFirmado) {
		ContingenciaRequest contingenciaRequest = new ContingenciaRequest();

		contingenciaRequest.setDocumento(properties.getProperty("USER_AUTH"));
		contingenciaRequest.setDocumento(documentoFirmado);

		return contingenciaRequest;
	}

}
