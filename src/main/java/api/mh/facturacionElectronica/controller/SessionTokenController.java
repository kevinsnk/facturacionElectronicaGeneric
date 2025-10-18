package api.mh.facturacionElectronica.controller;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.mh.facturacionElectronica.ex.model.SessionToken;
import api.mh.facturacionElectronica.jdbc.SessionTokenJDBC;
import api.mh.facturacionElectronica.model.AutentificacionResponse;
import api.mh.facturacionElectronica.services.FacturaElectronicaService;

public class SessionTokenController {

	private static final Logger logger = LogManager.getLogger(SessionTokenController.class);
	
	public Boolean validarToken() {
		Boolean flag = false;
		SessionToken sessionToken = new SessionToken();
		SessionTokenJDBC sessionTokenJDBC = new SessionTokenJDBC();
		try {
			sessionToken = sessionTokenJDBC.getTokenActivo();
			if(sessionToken != null) {
				Date fechaActual = new Date();
				if(fechaActual.after(sessionToken.getFechaExpiracion())) {
					flag = false;
					sessionTokenJDBC.edit(sessionToken);
				}else {
					flag = true;
				}
			}else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	public SessionToken getToken() {
		SessionToken sessionToken = new SessionToken();
		SessionTokenJDBC sessionTokenJDBC = new SessionTokenJDBC();
		try {
			sessionToken = sessionTokenJDBC.getTokenActivo();
			logger.debug("TOKEN: " + sessionToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sessionToken;
	}

	public void crearToken() {
		SessionToken sessionToken = new SessionToken();
		SessionTokenJDBC sessionTokenJDBC = new SessionTokenJDBC();
		FacturaElectronicaService facturaElectronicaService = new FacturaElectronicaService();
		AutentificacionResponse autentificacionResponse = new AutentificacionResponse();
		try {
			autentificacionResponse = facturaElectronicaService.getTokenUsuarioService();
			sessionToken.setToken(autentificacionResponse.getBody().getToken());
			sessionToken.setUsuario(autentificacionResponse.getBody().getUser());

			sessionTokenJDBC.save(sessionToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
