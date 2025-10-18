package api.mh.facturacionElectronica.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.mh.facturacionElectronica.ex.model.FelControl;
import api.mh.facturacionElectronica.jdbc.FelControlJDBC;

public class FelControlController {

	private static final Logger logger = LogManager.getLogger(FelControlController.class);
	
	public FelControl getFelRecord() {
		FelControl felControl = new FelControl();
		FelControlJDBC felControlJDBC = new FelControlJDBC();

		felControl = felControlJDBC.getFEL();

		return felControl;
	}

	public List<FelControl> listFelControl() {
		List<FelControl> lista = new ArrayList<FelControl>();
		FelControlJDBC felControlJDBC = new FelControlJDBC();
		try {
			lista = felControlJDBC.findFelRecords();

			logger.debug("==============================");
			logger.debug("DTES A PROCESAR: " + lista.size());
			logger.debug("==============================");

			for (FelControl felControl : lista) {
				felControlJDBC.cambiarEstado("P", felControl.getuDocEntry());
			}
		} catch (Exception e) {
			logger.error("ERROR AL OBTENER DTES A PROCESAR");
			e.printStackTrace();
		}

		return lista;
	}
}
