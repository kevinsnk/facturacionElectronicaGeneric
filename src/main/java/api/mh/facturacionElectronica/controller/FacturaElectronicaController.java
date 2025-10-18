package api.mh.facturacionElectronica.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.mh.facturacionElectronica.ex.model.FeContingencia;
import api.mh.facturacionElectronica.ex.model.FelControl;
import api.mh.facturacionElectronica.ex.model.SessionToken;
import api.mh.facturacionElectronica.jdbc.FeContingenciaJDBC;

public class FacturaElectronicaController {

	private static final Logger logger = LogManager.getLogger(FacturaElectronicaController.class);
	
	public void procesoFel() {
		SessionTokenController sessionTokenController = new SessionTokenController();
		FelControlController felControlController = new FelControlController();

		SessionToken sessionToken = new SessionToken();
		List<FelControl> lista = new ArrayList<FelControl>();
		Boolean tokenValido = false;

		logger.debug("ENTRO AL RUNNABLE");

		tokenValido = sessionTokenController.validarToken();

//		if (tokenValido) {
//			logger.debug("TOKEN VALIDO");
//			sessionToken = sessionTokenController.getToken();
//			lista = felControlController.listFelControl();
//		} else {
//			logger.debug("TOKEN INVALIDO");
//			sessionTokenController.crearToken();
//			sessionToken = sessionTokenController.getToken();
//			lista = felControlController.listFelControl();
//		}
		
		lista = felControlController.listFelControl();
		sessionToken = null;

		procesarDTES(lista, sessionToken);

		logger.debug("======================================");
		logger.debug("TERMINA PROCESAMIENTO DE DTES");
		logger.debug("======================================");
	}

	public void procesarDTES(List<FelControl> lista, SessionToken sessionToken) {
		for (FelControl felControl : lista) {
			Boolean contingencia = false;
			if (felControl.getuContingencia() == null) {
				contingencia = false;
			} else if (felControl.getuContingencia().equals("I")) {
				contingencia = true;
			}
			switch (felControl.getuTipo()) {
			case "FAC":
				logger.info("------------------------------------------");
				logger.info("Se procesa Factura consumidor final");
				logger.info("------------------------------------------");
				FcController fcController = new FcController();
				fcController.procesarDTE(felControl, sessionToken, contingencia);
				break;
			case "CCF":
				logger.info("------------------------------------------");
				logger.info("Se procesa Crédito fiscal");
				logger.info("------------------------------------------");
				CcfController ccfController = new CcfController();
				ccfController.procesarDTE(felControl, sessionToken, contingencia);
				break;
			case "FAE":
				logger.info("------------------------------------------");
				logger.info("Se procesa Factura de exportación");
				logger.info("------------------------------------------");
				FexController fexController = new FexController();
				fexController.procesarDTE(felControl, sessionToken, contingencia);
				break;
			case "NC":
				logger.info("------------------------------------------");
				logger.info("Se procesa Nota de Crédito");
				logger.info("------------------------------------------");
				NcController ncController = new NcController();
				ncController.procesarDTE(felControl, sessionToken, contingencia);
				break;
			case "ND":
				logger.info("------------------------------------------");
				logger.info("Se procesa Nota de Débito");
				logger.info("------------------------------------------");
				NdController ndController = new NdController();
				ndController.procesarDTE(felControl, sessionToken, contingencia);
				break;
			case "NRE":
				logger.info("------------------------------------------");
				logger.info("Se procesa Nota de Remisión");
				logger.info("------------------------------------------");
				NrController nrController = new NrController();
				nrController.procesarDTE(felControl, sessionToken, contingencia);
				break;
			case "FSE":
				logger.info("------------------------------------------");
				logger.info("Se procesa Factura de Sujeto Excluido");
				logger.info("------------------------------------------");
				FseController fseController = new FseController();
				fseController.procesarDTE(felControl, sessionToken, contingencia);
				break;
			case "ANL":
				logger.info("------------------------------------------");
				logger.info("Se procesa Anulación de documento");
				logger.info("------------------------------------------");
				AnularDTEController anularDTEController = new AnularDTEController();
				anularDTEController.procesarDTE(felControl, sessionToken, contingencia);
				break;
			default:
				break;
			}
		}
	}
	
	public void procesarContingenciasPendientes() {
		SessionTokenController sessionTokenController = new SessionTokenController();
		ContingenciaController contingenciaController = new ContingenciaController();
		
		FeContingenciaJDBC feContingenciaJDBC = new FeContingenciaJDBC();
		
		SessionToken sessionToken = new SessionToken();
		List<FeContingencia> lista = new ArrayList<FeContingencia>();
		Boolean tokenValido = false;

		tokenValido = sessionTokenController.validarToken();

		if (tokenValido) {
			logger.debug("TOKEN VALIDO");
			sessionToken = sessionTokenController.getToken();
			lista = feContingenciaJDBC.getContingenciasPendientes();
		} 
		
		for(FeContingencia feContingencia : lista) {
			contingenciaController.procesarDTE(feContingencia, sessionToken, false);
		}
		
		logger.debug("======================================");
		logger.debug("TERMINA PROCESAMIENTO DE CONTINGENCIAS");
		logger.debug("======================================");
	}
}
