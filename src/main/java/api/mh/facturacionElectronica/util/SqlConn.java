package api.mh.facturacionElectronica.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SqlConn {

	private static final Logger logger = LogManager.getLogger(SqlConn.class);
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			Properties properties = new Properties();
			properties = getProperties();
			
			String driver = properties.getProperty("DB_DRIVER");
			String url = properties.getProperty("DB_URL");
			String user = properties.getProperty("DB_USER");
			String password = properties.getProperty("DB_PSW");
			
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
		} catch (Exception e) {
			logger.error("ERROR EN CONEXIÓN A BASE DE DATOS");
			e.printStackTrace();
		}
		return conn;
	}
	
	private Properties getProperties() throws IOException {
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream path = loader.getResourceAsStream("resources/config/config.properties");
		prop.load(path);

		return prop;
	}
}
