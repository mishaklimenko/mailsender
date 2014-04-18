import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Created by Таня on 23.12.13.
 */
public class Test {

    public static void main(String[] args) {
    	//пример для отправителя с mail.ru
    	final String username = "guntrashtest50@mail.ru";
    	final String password = "ukjyrjylhbyf";

    		Properties props = new Properties();
    		props.put("mail.smtp.host", "smtp.mail.ru");
    		props.put("mail.smtp.auth", "true");
    		props.put("mail.smtp.port", "587");

    	Session session = Session.getDefaultInstance(props,
    			new javax.mail.Authenticator()
    			{
    				@Override
    					protected PasswordAuthentication getPasswordAuthentication()
    					{ return new PasswordAuthentication(username,password);	}
    			});

    		try {

    			Message message = new MimeMessage(session);
    		message.setFrom(new InternetAddress("guntrashtest50@mail.ru"));
    		message.setRecipients(Message.RecipientType.TO,
    						InternetAddress.parse("guntrashtest50@mail.ru"));
    		message.setSubject("Subject");
    		message.setText("TEST!");

    		Transport.send(message);

    		System.out.println("Done");

    		} catch (MessagingException e) {
    			throw new RuntimeException(e);
    		}
    	}
}
