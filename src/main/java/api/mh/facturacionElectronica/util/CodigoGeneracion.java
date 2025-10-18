package api.mh.facturacionElectronica.util;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CodigoGeneracion {

	private static final Logger logger = LogManager.getLogger(CodigoGeneracion.class);
	
	public String getCodigoGeneracion() {
		String codigoGeneracion = "";
		String cod1;//8
		String cod2;//4
		String cod3;//4
		String cod4;//4
		String cod5;//12
		//341CA743-70F1-4CFE-88BC-7E4AE72E60CB
		try {
			cod1 = getRandomString(8);
			cod2 = getRandomString(4);
			cod3 = getRandomString(4);
			cod4 = getRandomString(4);
			cod5 = getRandomString(12);
			
			codigoGeneracion = cod1 + "-" + cod2 + "-" + cod3 + "-" + cod4 + "-" + cod5;
		} catch (Exception e) {
			logger.error("Error al generar codigo");
			e.printStackTrace();
		}
		return codigoGeneracion;
	}
	
	protected String getRandomString(int size) {
        String SALTCHARS = "ABCDEF1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < size) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        
        return saltStr;
    }
}
