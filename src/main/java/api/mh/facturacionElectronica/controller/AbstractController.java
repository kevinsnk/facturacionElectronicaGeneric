package api.mh.facturacionElectronica.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import api.mh.facturacionElectronica.ex.model.FeContingencia;
import api.mh.facturacionElectronica.ex.model.FelControl;
import api.mh.facturacionElectronica.jdbc.DteJDBC;
import api.mh.facturacionElectronica.jdbc.FeContingenciaJDBC;
import api.mh.facturacionElectronica.jdbc.FelControlJDBC;
import api.mh.facturacionElectronica.model.AnulacionDTEResponse;
import api.mh.facturacionElectronica.model.ContingenciaResponse;
import api.mh.facturacionElectronica.model.RecepcionDTEResponse;

public abstract class AbstractController {
	
	private static final Logger logger = LogManager.getLogger(AbstractController.class);

	public abstract void procesarDTE(Object entity, Object token, Boolean contingencia);

	public abstract <T> T obtenerDTE(Object entity, Boolean contingencia);

	public abstract <T> T construirDTE(Object entity, Boolean contingencia);

	public abstract <T> T construirRecepcionDte(String string);

	public FelControl guardarRespuestaMH(Object t1, Object t2, String firma, String numControl, String jsonEnvio,
			Boolean contingencia, String respuestaEmail) {
		FelControlJDBC felControlJDBC = new FelControlJDBC();
		RecepcionDTEResponse recepcionDTEResponse = (RecepcionDTEResponse) t1;
		FelControl felControl = (FelControl) t2;

		felControl.setuEstado(getEstadoDTE(recepcionDTEResponse.getEstado()));
		felControl.setuCertificado((felControl.getuEstado() == "A") ? "SI" : "NO");
		felControl.setuFechaPro((felControl.getuEstado() == "A") ? new Date() : null);
		felControl.setuEnviado(respuestaEmail);
		felControl.setuECodGene(recepcionDTEResponse.getCodigoGeneracion());
		felControl.setuESellRecep(recepcionDTEResponse.getSelloRecibido());
		felControl.setuENumCont(numControl);
		felControl.setuFirma(firma);
		felControl.setuJsonEnviado(jsonEnvio);

		if (!contingencia) {
			felControl.setuEModFact("Previo");
			felControl.setuETipoTrans("Normal ");
		} else {
			felControl.setuEModFact("Diferido");
			felControl.setuETipoTrans("Contingencia");
		}

		Gson gson = new Gson();
		String json = gson.toJson(recepcionDTEResponse);
		felControl.setuJson(json);

		logger.info("SE PROCEDE A ACTUALIZAR REGISTROS DEL DTE");
		try {
			felControlJDBC.edit(felControl);
			if (felControl.getuEstado().equals("A")) {
				editarDTE(felControl);
			} else {
				felControl.setuECodGene(null);
				felControl.setuESellRecep("Error en certificación, revise información");
				felControl.setuENumCont(null);
				felControl.setuFirma(null);
				felControl.setuJsonEnviado(null);

				editarDTE(felControl);
			}
		} catch (SQLException e) {
			logger.error("ERROR AL GUARDAR EN TABLA felControl");
			e.printStackTrace();
		}
		return felControl;
	}
	
	public FelControl editarEnvioEmail(FelControl felControl, String respuestaEmail) {
		FelControlJDBC felControlJDBC = new FelControlJDBC();
		
		felControl.setuEnviado(respuestaEmail);
		try {
			felControlJDBC.edit(felControl);
		} catch (SQLException e) {
			logger.error("ERROR AL editar la tabla felControl");
			e.printStackTrace();
		}
		return felControl;
	}

	public FelControl guardarRespuestaAnulacion(Object t1, Object t2, String jsonEnvio, String respuestaEmail) {
		FelControlJDBC felControlJDBC = new FelControlJDBC();
		AnulacionDTEResponse anulacionDTEResponse = (AnulacionDTEResponse) t1;
		FelControl felControl = (FelControl) t2;

		felControl.setuFechaPro(new Date());
		felControl.setuEstado(getEstadoDTE(anulacionDTEResponse.getEstado()));
		felControl.setuCertificado((felControl.getuEstado() == "A") ? "SI" : "NO");
		felControl.setuEnviado(respuestaEmail);
		felControl.setuECodGene(anulacionDTEResponse.getCodigoGeneracion());
		felControl.setuESellRecep(anulacionDTEResponse.getSelloRecibido());
		felControl.setuJsonEnviado(jsonEnvio);

		Gson gson = new Gson();
		String json = gson.toJson(anulacionDTEResponse);
		felControl.setuJson(json);

		logger.info("SE PROCEDE A ACTUALIZAR REGISTROS DEL DTE");
		try {
			felControlJDBC.edit(felControl);
			if (felControl.getuEstado().equals("A")) {
				editarDTEAnulacion(felControl);
			} else {
				felControl.setuECodGene(null);
				felControl.setuESellRecep("Error en certificación, revise información");
				//felControl.setuENumCont(null);
				felControl.setuFirma(null);
				//felControl.setuJsonEnviado(null);

				editarDTE(felControl);
			}
		} catch (SQLException e) {
			logger.error("ERROR AL GUARDAR EN TABLA felControl");
			e.printStackTrace();
		}
		return felControl;
	}

	public String getEstadoDTE(String estadoDTE) {
		String estado = "";

		switch (estadoDTE) {
		case "RECHAZADO":
			estado = "E";
			break;
		case "PROCESADO":
			estado = "A";
			break;
		case "RECIBIDO":
			estado = "A";
			break;
		default:
			estado = "I";
			break;
		}

		return estado;
	}

	public void editarDTE(FelControl felControl) {

		DteJDBC dteJDBC = new DteJDBC();

		switch (felControl.getuTipo()) {
		case "FAC":
			dteJDBC.editOINV(felControl);
			break;
		case "CCF":
			dteJDBC.editOINV(felControl);
			break;
		case "FAE":
			dteJDBC.editOINV(felControl);
			break;
		case "NC":
			dteJDBC.editORIN(felControl);
			break;
		case "ND":
			dteJDBC.editOINV(felControl);
			break;
		case "NRE":
			dteJDBC.editODLN(felControl);
			break;
		case "FSE":
			dteJDBC.editOPCH(felControl);
			break;

		default:
			break;
		}
	}
	
	public void editarDTEAnulacion(FelControl felControl) {

		DteJDBC dteJDBC = new DteJDBC();

		switch (felControl.getuTipoDocAnul()) {
		case "OINV":
			dteJDBC.editOINV(felControl);
			break;
		case "ORIN":
			dteJDBC.editORIN(felControl);
			break;
		case "ODLN":
			dteJDBC.editODLN(felControl);
			break;
		case "OPCH":
			dteJDBC.editOPCH(felControl);
			break;

		default:
			break;
		}
	}

	public String obtenerTipoDocumento(String tipo, Properties properties) {
		String codigoDocumento = "";
		switch (tipo) {
		case "FAC":
			codigoDocumento = properties.getProperty("DTE_FC");
			break;
		case "CCF":
			codigoDocumento = properties.getProperty("DTE_CCF");
			break;
		case "FAE":
			codigoDocumento = properties.getProperty("DTE_FAE");
			break;
		case "NC":
			codigoDocumento = properties.getProperty("DTE_NC");
			break;
		case "ND":
			codigoDocumento = properties.getProperty("DTE_ND");
			break;
		case "NRE":
			codigoDocumento = properties.getProperty("DTE_NR");
			break;
		case "FSE":
			codigoDocumento = properties.getProperty("DTE_FSE");
			break;

		default:
			codigoDocumento = null;
			break;
		}

		return codigoDocumento;
	}

	public FeContingencia guardarRespuestaContingencia(Object t1, Object t2, String jsonEnvio) {
		FeContingencia feContingencia = (FeContingencia) t1;
		ContingenciaResponse contingenciaResponse = (ContingenciaResponse) t2;
		FeContingenciaJDBC feContingenciaJDBC = new FeContingenciaJDBC();
		
		feContingencia.setuEstado(getEstadoDTE(contingenciaResponse.getEstado()));
		if(feContingencia.getuEstado().equals("A")){
			feContingencia.setuESelleRecepcion(contingenciaResponse.getSelloRecibido());
		}
		feContingencia.setuJsonContingencia(jsonEnvio);
		
		logger.info("SE PROCEDE A ACTUALIZAR REGISTROS DEL DTE");
		try {
			feContingenciaJDBC.edit(feContingencia);
		} catch (SQLException e) {
			logger.error("ERROR AL GUARDAR EN TABLA felControl");
			e.printStackTrace();
		}
		
		return feContingencia;
	}
}
