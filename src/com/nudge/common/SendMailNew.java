package com.nudge.common;

import java.util.Properties;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class SendMailNew {


	public static String  SendMailUser(String send_message,String send_to,String subject, final String MAIL_FROM, final String PASSWORD) throws UnsupportedEncodingException
	{ 

		String response="";

		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		// Get a Properties object
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "false");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");


		final String username = MAIL_FROM;//
		final String password = PASSWORD;
		try{
			Session session = Session.getDefaultInstance(props, 
					new Authenticator(){
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}});



			// -- Create a new message --
			Message msg = new MimeMessage(session);

			// -- Set the FROM and TO fields --
			msg.setFrom(new InternetAddress(MAIL_FROM,"The Gifting App"));
			msg.setRecipients(Message.RecipientType.TO, 
					InternetAddress.parse(send_to,false));
			msg.setSubject(subject);
			//msg.setText("How are you");
			// Send the actual HTML message, as big as you like
			msg.setContent(send_message, "text/html");

			msg.setSentDate(new Date());
			Transport.send(msg);
			System.out.println("Message sent.");
			response = "success";
		}catch (MessagingException e)
		{ System.out.println("Exception: " + e);
		response= "fail";
		}

		return response;
	}

}
