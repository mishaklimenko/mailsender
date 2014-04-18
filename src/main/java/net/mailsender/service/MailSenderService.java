package net.mailsender.service;

import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSenderService {
	private Session session;

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public void sendMail(String from,
						String to,
						String subject,
						Date sentDate,
						String messageCharset,
						String messageText
						) throws MessagingException {

		MimeMessage msg = new MimeMessage(this.session);

		msg.setFrom(new InternetAddress(from));
		//headers

		InternetAddress[] address = { new InternetAddress(to) };
		msg.setRecipients(Message.RecipientType.TO, address);

		msg.setSubject(subject);
		msg.setSentDate(sentDate);


		//body mime parts
		// create and fill the first message part
		MimeBodyPart mbp1 = new MimeBodyPart();
		mbp1.setText(messageText, messageCharset);
		mbp1.setHeader("Content-Type", "text/plain");

		MimeBodyPart mbp2 = new MimeBodyPart();
		mbp2.setText(messageText, messageCharset);
		mbp2.setHeader("Content-Type", "text/html");


		//assembling all body parts together
		Multipart mp = new MimeMultipart();
		mp.addBodyPart(mbp1);
		mp.addBodyPart(mbp2);


		// add body to the message
		msg.setContent(mp);

		// send the message
		Transport.send(msg);

	}
}