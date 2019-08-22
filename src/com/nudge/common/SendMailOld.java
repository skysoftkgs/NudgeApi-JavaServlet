package com.nudge.common;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class SendMailOld {
	
	
	public static String  SendMailUser(String send_message,String send_to,String subject, String email)
	{
		String response="";
		
		// String to = MagazineConfig.MAIL_TO;//change accordingly  
	      String to = send_to;//change accordingly  
	      String from = email;
	      String host = "localhost";//or IP address  
	      
	    //Get the session object  
	      Properties properties = System.getProperties();  
	      properties.setProperty("mail.smtp.host", host);  
	      Session session = Session.getDefaultInstance(properties);
	      
	      try{  
	          MimeMessage message = new MimeMessage(session);  
	          
	          
	          message.setFrom(new InternetAddress(from));  
	          
	          message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
	          
	          message.setSubject(subject);  
	         // message.setText("Hello, this is example of sending email  "); 
	          
	          //message.setText(send_message);
	          
	          message.setContent(send_message,"text/html");
	   
	          // Send message  
	          Transport.send(message);  
	          //System.out.println("message sent successfully...."); 
	          
	          response = "success";
	   
	       }
	      catch (MessagingException mex)
	      {
	    	  mex.printStackTrace();
	    	  response = "fail";
	    
	     }  

		return response;
	}
	
	
	public static String  SendMailUserNew(String send_message,String send_to,String subject, final String email)
	{
		String response="";
		
		// String to = MagazineConfig.MAIL_TO;//change accordingly  
	      String to = send_to;//change accordingly  
	      String from = Config.MAIL_FROM;
	      String host = "smtp.gmail.com";//or IP address  
	      
	      Properties props = new Properties();
	     
	      props.setProperty("mail.smtp.auth", "true");
	      props.setProperty("mail.smtp.starttls.enable", "true");
	      props.setProperty("mail.smtp.host", host);
	      props.setProperty("mail.smtp.port", "587");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(email, "@dvantal987");
	            }
	         });


	      
	      try{  
	          MimeMessage message = new MimeMessage(session);  
	          
	          
	          message.setFrom(new InternetAddress(from));  
	          
	          message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
	          
	          message.setSubject(subject);  
	         // message.setText("Hello, this is example of sending email  "); 
	          
	          //message.setText(send_message);
	          
	          message.setContent(send_message,"text/html");
	   
	          // Send message  
	          Transport.send(message);  
	          System.out.println("message sent successfully....");  
	   
	       }catch (MessagingException mex) {mex.printStackTrace();}  

		return response;
	}

}
