package net.mailsender.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class SMTPSessionFactoryUtil {
	private static final SMTPSessionFactoryUtil smtpSessionFactory = new SMTPSessionFactoryUtil();

	private SMTPSessionFactoryUtil (){

	}

	public static SMTPSessionFactoryUtil getSMTPSessionFactory(){
		return smtpSessionFactory;
	}

	public Session getSMTPSession(String host, final String user, final String password){

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		switch (host) {
		case "gmail.com":
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");
			break;
		case "yandex.ru":
				props.put("mail.smtp.host", "smtp.yandex.ru");
				props.put("mail.smtp.port", "587");
			break;
		default:
			break;
		}


    	Authenticator authenticator = new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		  };

    	Session session = Session.getInstance(props, authenticator);

		return session;
	}
}