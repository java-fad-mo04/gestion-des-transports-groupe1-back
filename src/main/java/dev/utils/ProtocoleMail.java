package dev.utils;

import javax.mail.internet.*;
import javax.mail.*;
import java.util.*;

/**
 * Classe permettant d'envoyer un mail.
 */
public class ProtocoleMail {
	private final static String MAILER_VERSION = "Java";

	public static boolean envoyerMailSMTP(boolean debug) {
		boolean result = false;
		try {
			Properties prop = System.getProperties();
			// prop.put("mail.smtp.host", "smtp.gmail.com");
			// prop.put("mail.smtp.port", "465");
			prop.put("mail.smtp.host", "192.168.1.15");
			//prop.put("mail.smtp.socketFactory.port", "465");
			//prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			//prop.put("mail.smtp.auth", "true");
			//prop.put("mail.smtp.port", "465");
			Session session = Session.getDefaultInstance(prop, null);
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("audrey.martin2686@gmail.com"));
			InternetAddress[] internetAddresses = new InternetAddress[1];
			internetAddresses[0] = new InternetAddress("audrey.martin26@gmail.com");
			message.setRecipients(Message.RecipientType.TO, internetAddresses);
			message.setSubject("Test");
			message.setText("test mail");
			message.setHeader("X-Mailer", MAILER_VERSION);
			message.setSentDate(new Date());
			session.setDebug(debug);
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