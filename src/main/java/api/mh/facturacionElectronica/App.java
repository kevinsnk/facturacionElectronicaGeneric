package api.mh.facturacionElectronica;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import api.mh.facturacionElectronica.controller.FacturaElectronicaController;

@SpringBootApplication
public class App {
	
	public static void main(String[] args) throws SQLException {
		SpringApplication.run(App.class, args);
	}

	static Runnable procesoFacturacionElectronica = new Runnable() {
		public void run() {
			FacturaElectronicaController facturaElectronicaController = new FacturaElectronicaController();

			// Se sigue el flujo de procesamiento de DTE'S
			facturaElectronicaController.procesoFel();
		}
	};

	static Runnable procesoContigencia = new Runnable() {
		public void run() {
			FacturaElectronicaController facturaElectronicaController = new FacturaElectronicaController();

			// Se sigue el flujo de procesamiento de contingencias.
			facturaElectronicaController.procesarContingenciasPendientes();
		}
	};
}
