package com.coplaning.ws;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	private String username = "coplaning.noreply@gmail.com";
	private String password = "Genielogiciel123!";

	public void envoyer(String Destinataire, String Objet, String Msg) {
// Etape 1 : Création de la session
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
// Etape 2 : Création de l'objet Message
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("coplaning@outlook.fr"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Destinataire));
			message.setSubject(Objet);
			message.setText(Msg);
// Etape 3 : Envoyer le message
			Transport.send(message);
			System.out.println("Message_envoye");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

//Etape 4 : Tester la méthode
	public static void main(String[] args) {
		String des = "fabriceguignard93@hotmail.fr";
		String obj = "Test Email";
		String msg = "Le message est bien envoyé";
		Mail test = new Mail();
		test.envoyer(des, obj, msg);
	}
}
