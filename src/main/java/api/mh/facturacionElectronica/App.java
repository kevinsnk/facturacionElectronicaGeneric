package api.mh.facturacionElectronica;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.mh.facturacionElectronica.controller.FacturaElectronicaController;

public class App {
	
	private static final Logger logger = LogManager.getLogger(App.class);
	
	public static void main(String[] args) throws SQLException {

		Properties properties = new Properties();
		int tiempoEjecucion = 30; // POR DEFECTO SE DEJA CADA 30 SEGUNDOS
		int tiempoEjecucionContingencia = 30; // POR DEFECTO SE DEJA CADA 30 MINUTOS
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream path = loader.getResourceAsStream("resources/config/config.properties");
			properties.load(path);
			tiempoEjecucion = Integer.valueOf(properties.getProperty("LOOP_TIME"));
			tiempoEjecucionContingencia = Integer.valueOf(properties.getProperty("LOOP_TIME_CONTINGENCIA"));
		} catch (Exception e) {
			logger.error("ERROR EN OBTENER PARAMETROS DE TIEMPO; SE PROCEDE A DEJAR VALORES POR DEFECTO");
			tiempoEjecucion = 30; // POR DEFECTO SE DEJA CADA 30 SEGUNDOS
			tiempoEjecucionContingencia = 30; // POR DEFECTO SE DEJA CADA 30 MINUTOS
		}

//		SendEmail email = new SendEmail();
//		FelControl felControl = new FelControl();
//		FelControlJDBC felControlJDBC = new FelControlJDBC();
//		felControl = felControlJDBC.getFELByDocEntry("1097");
//		email.enviarComprobanteDTE(felControl, felControl.getuJsonEnviado(), "kevin.santos.solorzano@gmail.com");

		ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
		exec.scheduleAtFixedRate(procesoFacturacionElectronica, 0, tiempoEjecucion, TimeUnit.SECONDS);

		//ScheduledExecutorService exec2 = Executors.newScheduledThreadPool(1);
		//exec2.scheduleAtFixedRate(procesoContigencia, 0, tiempoEjecucionContingencia, TimeUnit.MINUTES);
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
