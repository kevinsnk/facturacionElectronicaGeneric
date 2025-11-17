package api.mh.facturacionElectronica.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.mh.facturacionElectronica.controller.FacturaElectronicaController;
import api.mh.facturacionElectronica.ex.model.FelControl;

@RestController
@RequestMapping("/facturacionElectronica/dte")
public class FacElectronicaRest {

	@PostMapping("/procesarDTE")
	public Object procesarDTE(@RequestBody FelControl dteRequest) {
		FacturaElectronicaController facturaElectronicaController = new FacturaElectronicaController();

		// Se sigue el flujo de procesamiento de DTE'S
		return facturaElectronicaController.procesarDTE(dteRequest);
	}

}
