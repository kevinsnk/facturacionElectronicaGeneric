package api.mh.facturacionElectronica.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import api.mh.facturacionElectronica.model.AnulacionDTERequest;
import api.mh.facturacionElectronica.model.AnulacionDTEResponse;
import api.mh.facturacionElectronica.model.AutentificacionResponse;
import api.mh.facturacionElectronica.model.ContingenciaRequest;
import api.mh.facturacionElectronica.model.ContingenciaResponse;
import api.mh.facturacionElectronica.model.FirmardocumentoResponse;
import api.mh.facturacionElectronica.model.RecepcionDTERequest;
import api.mh.facturacionElectronica.model.RecepcionDTEResponse;

public class FacturaElectronicaService {
	
	private static final Logger logger = LogManager.getLogger(FacturaElectronicaService.class);

	public AutentificacionResponse getTokenUsuarioService() {
		Properties properties = new Properties();
		AutentificacionResponse autentificacionResponse = null;
		try {

			properties = getProperties();
			String url = "";
			String ambiente = properties.getProperty("AMBIENTE");
			if(ambiente.equals("00")) {
				url = properties.getProperty("AUTENTIFICACION_TEST");
			}else {
				url = properties.getProperty("AUTENTIFICACION_PROD");
			}
			logger.info(url);
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(url);

			ArrayList<BasicHeader> parametrosBody = new ArrayList<>();
			parametrosBody.add(new BasicHeader("user", properties.getProperty("USER_AUTH")));
			parametrosBody.add(new BasicHeader("pwd", properties.getProperty("PSW_TOKEN")));

			UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(parametrosBody, "utf-8");
			post.setEntity(urlEncodedFormEntity);
			post.setHeader("Content-type", "application/x-www-form-urlencoded");
			HttpResponse response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();

			String responseString = EntityUtils.toString(entity, "UTF-8");
			logger.info(responseString);
			autentificacionResponse = new Gson().fromJson(responseString, AutentificacionResponse.class);

		} catch (Exception e) {
			autentificacionResponse = null;
			e.printStackTrace();
		}

		return autentificacionResponse;
	}
	
	public FirmardocumentoResponse certificarDTE(String dte) {
		Properties properties = new Properties();
		FirmardocumentoResponse dteCertificado = new FirmardocumentoResponse();
		try {
			properties = getProperties();
			logger.info(properties.getProperty("FIRMA_DOCUMENTO"));
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(properties.getProperty("FIRMA_DOCUMENTO"));
			
			post.setHeader("Content-type", "application/json");
			HttpEntity parametrosRequest = new ByteArrayEntity(dte.getBytes("UTF-8"));
			post.setEntity(parametrosRequest);
			HttpResponse response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();

			String responseString = EntityUtils.toString(entity, "UTF-8");
			try {
				dteCertificado = new Gson().fromJson(responseString, FirmardocumentoResponse.class);
			} catch (Exception e) {
				logger.info(responseString);
			}
		} catch (Exception e) {
			dteCertificado = null;
			e.printStackTrace();
		}
		
		return dteCertificado;
	}

	public RecepcionDTEResponse recepcionDTE(RecepcionDTERequest recepcion, String token) {
		Properties properties = new Properties();
		RecepcionDTEResponse recepcionDTE = new RecepcionDTEResponse();
		try {
			properties = getProperties();
			String url = "";
			String ambiente = properties.getProperty("AMBIENTE");
			logger.info("Ambiente: " + ambiente);
			if(ambiente.equals("00")) {
				url = properties.getProperty("RECEPCION_DTE_TEST");
				logger.info("AMBIENTE DESARROLLO");
			}else {
				logger.info("AMBIENTE PRODUCTIVO");
				url = properties.getProperty("RECEPCION_DTE_PROD");
			}
			logger.info(url);
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(url);
			
			Gson gson = new Gson();
			String json = gson.toJson(recepcion);
			logger.info(json);

			post.setHeader("Authorization", token);
			post.setHeader("Content-type", "application/json");
			HttpEntity parametrosRequest = new ByteArrayEntity(json.getBytes("UTF-8"));
			post.setEntity(parametrosRequest);
			HttpResponse response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();

			String responseString = EntityUtils.toString(entity, "UTF-8");
			try {
				recepcionDTE = new Gson().fromJson(responseString, RecepcionDTEResponse.class);
			} catch (Exception e) {
				logger.info(responseString);
			}

		} catch (Exception e) {
			recepcionDTE = null;
			e.printStackTrace();
		}
		return recepcionDTE;
	}
	
	public AnulacionDTEResponse anularDTE(AnulacionDTERequest anulacion, String token) {
		Properties properties = new Properties();
		AnulacionDTEResponse recepcionDTE = new AnulacionDTEResponse();
		try {
			properties = getProperties();
			String url = "";
			String ambiente = properties.getProperty("AMBIENTE");
			if(ambiente.equals("00")) {
				url = properties.getProperty("ANULAR_DTE_TEST");
			}else {
				url = properties.getProperty("ANULAR_DTE_PROD");
			}
			logger.info(url);
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(url);
			
			Gson gson = new Gson();
			String json = gson.toJson(anulacion);
			logger.info(json);

			post.setHeader("Authorization", token);
			post.setHeader("User-Agent", "ADMIN");
			post.setHeader("Content-type", "application/json");
			HttpEntity parametrosRequest = new ByteArrayEntity(json.getBytes("UTF-8"));
			post.setEntity(parametrosRequest);
			HttpResponse response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();

			String responseString = EntityUtils.toString(entity, "UTF-8");
			try {
				recepcionDTE = new Gson().fromJson(responseString, AnulacionDTEResponse.class);
			} catch (Exception e) {
				logger.info(responseString);
			}

		} catch (Exception e) {
			recepcionDTE = null;
			e.printStackTrace();
		}
		return recepcionDTE;
	}
	
	public ContingenciaResponse contingenciaDte(ContingenciaRequest contingencia, String token) {
		Properties properties = new Properties();
		ContingenciaResponse contingenciaResponse = new ContingenciaResponse();
		try {
			properties = getProperties();
			String url = "";
			String ambiente = properties.getProperty("AMBIENTE");
			if(ambiente.equals("00")) {
				url = properties.getProperty("CONTIGENCIA_TEST");
			}else {
				url = properties.getProperty("CONTINGENCIA_PROD");
			}
			logger.info(url);
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(url);
			
			Gson gson = new Gson();
			String json = gson.toJson(contingencia);
			logger.info(json);

			post.setHeader("Authorization", token);
			post.setHeader("User-Agent", "ADMIN");
			post.setHeader("Content-type", "application/json");
			HttpEntity parametrosRequest = new ByteArrayEntity(json.getBytes("UTF-8"));
			post.setEntity(parametrosRequest);
			HttpResponse response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();

			String responseString = EntityUtils.toString(entity, "UTF-8");
			try {
				contingenciaResponse = new Gson().fromJson(responseString, ContingenciaResponse.class);
			} catch (Exception e) {
				logger.info(responseString);
			}

		} catch (Exception e) {
			contingenciaResponse = null;
			e.printStackTrace();
		}
		return contingenciaResponse;
	}

	private Properties getProperties() throws IOException {
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream path = loader.getResourceAsStream("resources/config/config.properties");
		prop.load(path);

		return prop;
	}
}
