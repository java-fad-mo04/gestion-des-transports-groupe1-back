package dev.utils;

import javax.mail.internet.*;
import javax.mail.*;
import java.util.*;

/**
 * Classe permettant d'envoyer un mail.
 */
public class ProtocoleMail {
	private final static String MAILER_VERSION = "Java";

	static ResourceBundle monFichierConf = ResourceBundle.getBundle("informations");
	private static String username = monFichierConf.getString("protocole.email");
	private static String password = monFichierConf.getString("protocle.password");

	public static boolean envoyerMailSMTP(String sujet, String emailDest, String messageCorps) {
		boolean result = false;

		try {
			Properties prop = System.getProperties();
			prop.put("mail.smtp.host", "smtp.gmail.com");
			prop.put("mail.smtp.socketFactory.port", "465"); // SSL Port
			prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL Factory Class
			prop.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
			prop.put("mail.smtp.port", "465"); // SMTP Port

			Authenticator auth = new Authenticator() {
				// override the getPasswordAuthentication method
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			};

			Session session = Session.getDefaultInstance(prop, auth);
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("transportgestion.groupe1@gmail.com"));
			InternetAddress[] internetAddresses = new InternetAddress[1];
			internetAddresses[0] = new InternetAddress(emailDest);
			message.setRecipients(Message.RecipientType.TO, internetAddresses);
			message.setSubject(sujet);
			message.setHeader("Content-Type", "multipart/mixed");
			message.setSentDate(new Date());

			// This mail has 2 part, the BODY and the embedded image
			MimeMultipart multipart = new MimeMultipart("related");

			// first part (the html)
			BodyPart messageBodyPart = new MimeBodyPart();
			String signature = "<div dir=\"ltr\" class=\"gmail_signature\" data-smartmail=\"gmail_signature\"><div dir=\"ltr\"><div><div dir=\"ltr\">Cordialement,</div><div dir=\"ltr\"><br></div><div dir=\"ltr\"><img src=\"https://docs.google.com/uc?export=download&amp;id=1iScRrHy6iChum24eTeQ67vvTOz3gjeGS&amp;revid=0BzCk10LHKLDMZVY5a2VXUmlSZzlvM3Z1ZUFmcHhodXBqdjE0PQ\"><b><font color=\"#0b5394\" size=\"4\">Gestion des transport</font></b><br><div><br></div></div></div></div></div>";

			String htmlText = "<h3>Bonjour,</h3>" + "<div>" + messageCorps + "</div>" + signature;
			messageBodyPart.setContent(htmlText, "text/html");
			// add it
			multipart.addBodyPart(messageBodyPart);

			// add image to the multipart
			multipart.addBodyPart(messageBodyPart);

			// put everything together
			message.setContent(multipart);
			Transport.send(message);
			result = true;
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return result;
	}
}