package com.coplaning;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.coplaning.dao.DAO;
import com.coplaning.dao.FlightContainer;
import com.coplaning.dao.PassengerContainer;
import com.coplaning.dao.PilotContainer;

public class Mail {
	
	public Mail() {
		super();
	}

	private String username = "coplaning.noreply@gmail.com";
	private String password = "Genielogiciel123!";

	public void envoyer(String Destinataire, String Objet, String Msg) {
// Etape 1 : Creation de la session
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
// Etape 2 : Creation de l'objet Message
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("coplaning.noreply@gmail.com"));
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

//Etape 4 : Tester la methode
	public void mail() throws Exception{
		DAO.getFlightDao();
		DAO.getPilotDao();
		DAO.getPassengerDao();
		final Mail test = new Mail();
		Calendar c = Calendar.getInstance();
		// Year, Month, Day
		//c.set(2021, 5, 5);
		c.set(Calendar.HOUR_OF_DAY, 06);
		c.set(Calendar.MINUTE, 57);
		c.set(Calendar.SECOND, 42);
		System.out.println("a");
		int period = 100000;
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				System.out.println("b");
				String obj_pilote = "Pilote Email";
				String msg_pilote = "Mon gars tu dois pilote demain";
				String obj_passenger = "passenger Email";
				String msg_passenger = "Mon gars t'a un vol demain";
				long millis=System.currentTimeMillis()+86400000;  
				java.sql.Date date=new java.sql.Date(millis);  
				String currentdate = date.toString();
				List<FlightContainer> flights = DAO.getFlightDao().Searchspecial("date", currentdate);
				
				for(int i = 0 ; i< flights.size() ; i++) {
					System.out.println("i"+i);
					PilotContainer container = DAO.getPilotDao().getPilotContainer(flights.get(i).getFlight().getId_pilot());
					String pilote = container.getPilot().getEmail();
					test.envoyer(pilote, obj_pilote, msg_pilote);
					for(int j = 0 ; i< flights.get(i).getFlight().getPassengers().size() ; i++) {
						System.out.println("j"+j);
						PassengerContainer pcontainer = DAO.getPassengerDao().getPassengerContainer(flights.get(i).getFlight().getPassengers().get(j));
						String passenger = pcontainer.getPassenger().getEmail();
						System.out.println(passenger);
						test.envoyer(passenger, obj_passenger, msg_passenger);						
					}
				}
				// Call your method here
				// setEmail(emailContent, subject);
				
				return;

			}
		}, c.getTime(), period);
	}
}
