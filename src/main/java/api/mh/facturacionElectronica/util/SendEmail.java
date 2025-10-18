package api.mh.facturacionElectronica.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import api.mh.facturacionElectronica.ex.model.FeEmail;
import api.mh.facturacionElectronica.ex.model.FelControl;
import api.mh.facturacionElectronica.jdbc.FeEmailJDBC;
import net.sf.jasperreports.engine.JRException;

public class SendEmail {
	
	private static final Logger logger = LogManager.getLogger(SendEmail.class);

	Session newSession = null;
	MimeMessage mimeMessage = null;

	public String enviarComprobanteDTE(FelControl felControl, String jsonEnvio, String correoCliente) {
		String patternDay = "yyyy-MM-dd";
		SimpleDateFormat formatearFecha = new SimpleDateFormat(patternDay);
		String respuestaEmail = "NO";
		FeEmailJDBC feEmailJDBC = new FeEmailJDBC();
		FeEmail emailContent = new FeEmail();
		FeEmail ccEmails = new FeEmail();
		try {
			Properties properties = new Properties();
			Properties props = new Properties();

			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream path = loader.getResourceAsStream("resources/config/config.properties");

			properties.load(path);

			String to = "";

			if (properties.getProperty("AMBIENTE").equals("00")) {
				to = properties.getProperty("mail.smtp.email.prueba");
			} else {
				to = correoCliente;
			}

			String user = properties.getProperty("mail.smtp.user");
			String password = properties.getProperty("mail.smtp.password");
			String subject = properties.getProperty("mail.smtp.subject");
			String userEmail = properties.getProperty("mail.smtp.user.from");
			String userName = properties.getProperty("mail.smtp.user.name");

			props.put("mail.smtp.host", properties.getProperty("mail.smtp.host"));
			props.put("mail.smtp.auth", properties.getProperty("mail.smtp.auth"));
			props.put("mail.smtp.port", properties.getProperty("mail.smtp.port"));

			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(user, password);
				}
			});

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userEmail, userName));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			ccEmails = feEmailJDBC.getParameter("CC_EMAILS");
			String[] ccEmailsArray = ccEmails.getuValor().split(";");

			for (String bcc : ccEmailsArray) {
				logger.info(bcc);
				message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
			}

			message.setSubject(subject);
			BodyPart cuerpoCorreo = new MimeBodyPart();
			emailContent = feEmailJDBC.getParameter("EMAIL_CONTENT");
			cuerpoCorreo.setContent(emailContent.getuValor(), "text/html");
			MimeBodyPart adjuntoJson = new MimeBodyPart();
			MimeBodyPart adjuntoPdf = new MimeBodyPart();

			generarJson(felControl, jsonEnvio, properties);
			generarPDF(felControl);

			adjuntoJson.attachFile(new File(properties.getProperty("mail.smtp.path") + felControl.getuECodGene() + "-"
					+ formatearFecha.format(new Date()) + ".json"));
			adjuntoPdf.attachFile(new File(properties.getProperty("reportes.pdf.path") + felControl.getuECodGene() + "-"
					+ formatearFecha.format(new Date()) + ".pdf"));
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(cuerpoCorreo);
			multipart.addBodyPart(adjuntoJson);
			multipart.addBodyPart(adjuntoPdf);

			// send the message
			message.setContent(multipart);
			Transport.send(message);

			respuestaEmail = "SI";
			logger.info("Mensaje enviado de forma exitosa");

		} catch (MessagingException e) {
			respuestaEmail = "NO";
			e.printStackTrace();
			logger.error("ERROR EN EL ENVÍO DE CORREO DEL COMPROBANTE DEL DTE");
		} catch (IOException ex) {
			respuestaEmail = "NO";
			ex.printStackTrace();
		}

		return respuestaEmail;

	}

	public void generarJson(FelControl felControl, String jsonString, Properties properties) {
		String patternDay = "yyyy-MM-dd";
		SimpleDateFormat formatearFecha = new SimpleDateFormat(patternDay);
		try {
			FileWriter fw = new FileWriter(properties.getProperty("mail.smtp.path") + felControl.getuECodGene() + "-"
					+ formatearFecha.format(new Date()) + ".json");
			fw.write(jsonString);
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Ocurrió un error al generar archivo JSON");
		}
	}

	public void generarPDF(FelControl felControl) {
		GenerarDocumentoPDF generarDocumentoPDF = new GenerarDocumentoPDF();
		try {
			generarDocumentoPDF.generarPDF(felControl);
		} catch (FileNotFoundException | JRException e) {
			logger.error("ERROR AL GENERAR EL PDF");
			e.printStackTrace();
		}
	}

}
