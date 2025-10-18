package api.mh.facturacionElectronica.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.mh.facturacionElectronica.ex.model.FelControl;
import api.mh.facturacionElectronica.jdbc.DteJDBC;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class GenerarDocumentoPDF {

	private static final Logger logger = LogManager.getLogger(GenerarDocumentoPDF.class);
	
	public void generarPDF(FelControl felControl) throws JRException, FileNotFoundException {
		SqlConn sconn = new SqlConn();
		Connection conn = sconn.getConnection();
		String pathReporte = "";
		InputStream path = null;
		InputStream pathSubReport = null;
		Properties properties = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			InputStream pathProperties = loader.getResourceAsStream("resources/config/config.properties");
			properties.load(pathProperties);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter = obtenerParametrosReporte(felControl, properties);
			pathReporte = obtenerUrlReporte(felControl);
			try {
				path = loader.getResourceAsStream(pathReporte);
			} catch (Exception e) {
				logger.error("ERROR EN EL INPUTSTREAM");
			}

			JasperReport jasperDesign = JasperCompileManager.compileReport(path);
			if (felControl.getuTipo().equals("NC")) {
				pathSubReport = loader.getResourceAsStream("resources/reports/documentosRelacionados.jrxml");
				JasperReport jasperSubReport = JasperCompileManager.compileReport(pathSubReport);
				parameter.put("jasperSubReport", jasperSubReport);
			}
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperDesign, parameter, conn);

			String patternDay = "yyyy-MM-dd";
			SimpleDateFormat formatearFecha = new SimpleDateFormat(patternDay);

			File file = new File(properties.getProperty("reportes.pdf.path") + felControl.getuECodGene() + "-"
					+ formatearFecha.format(new Date()) + ".pdf");
			OutputStream outputSteam = new FileOutputStream(file);
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputSteam);

			logger.info("Reporte Generado!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public String obtenerUrlReporte(FelControl felControl) {

		String url = "";
		switch (felControl.getuTipo()) {
		case "FAC":
			url = "resources/reports/FEL_FC.jrxml";
			break;
		case "CCF":
			url = "resources/reports/FEL_CCF.jrxml";
			break;
		case "FAE":
			url = "resources/reports/FEL_FEX.jrxml";
			break;
		case "NC":
			url = "resources/reports/FEL_NC.jrxml";
			break;
		case "ND":
			url = "";
			break;
		case "NRE":
			url = "resources/reports/FEL_NRE.jrxml";
			break;
		case "FSE":
			url = "resources/reports/FEL_FSE.jrxml";
			break;
		case "ANL":
			url = "resources/reports/FEL_ANULACION.jrxml";
			break;
		default:
			url = "";
			break;
		}

		return url;
	}

	public Map<String, Object> obtenerParametrosReporte(FelControl felControl, Properties properties) {

		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("docEntry", felControl.getuDocEntry());
		parameter.put("ambiente", properties.getProperty("AMBIENTE"));
		parameter.put("LOGO_EMPRESA", properties.getProperty("reportes.pdf.img.logo"));
		parameter.put("P_EMPRESA", properties.getProperty("P_EMPRESA"));

		if (felControl.getuTipo().equals("ANL")) {
			DteJDBC dteJDBC = new DteJDBC();
			String tituloDocumento;
			logger.info(felControl.getuTipoDocAnul());
			switch (felControl.getuTipoDocAnul()) {
			case "ORIN":
				tituloDocumento = "COMPROBANTE DE NOTA DE CRÉDITO";
				break;
			case "ODLN":
				tituloDocumento = "COMPROBANTE DE NOTA DE REMISIÓN";
				break;
			case "OPCH":
				tituloDocumento = "COMPROBANTE DE SUJETO EXCLUIDO";
				break;
			case "OINV":
				String tipoDocu = dteJDBC.getTipoDoc(felControl.getuDocEntry());
				logger.info(tipoDocu);
				switch (tipoDocu) {
				case "CCF":
					tituloDocumento = "COMPROBANTE DE CREDITO FISCAL";
					break;
				case "FAC":
					tituloDocumento = "COMPROBANTE DE CONSUMIDOR FINAL";
					break;
				case "FAE":
					tituloDocumento = "COMPROBANTE DE FACTURA DE EXPORTACIÓN";
					break;
				default:
					tituloDocumento = "";
					break;
				}

				break;

			default:
				tituloDocumento = "";
				break;
			}
			parameter.put("TIPO_COMPROBANTE", tituloDocumento);
		}

		return parameter;
	}
}
