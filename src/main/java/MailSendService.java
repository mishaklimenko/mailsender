/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
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


public class MailSendService {

    private static final String GMAIL_HOST = "gmail.com";
    private static final String GMAIL_USER = "sendermk1991@gmail.com";
    private static final String GMAIL_PASSWORD = "ohdude1991";

    private static final String YANDEX_MAILBOX_HOST = "yandex.ru";
    private static final String YANDEX_USER = "sendermk1991@yandex.ru";
    private static final String YANDEX_PASSWORD = "ohdude1991";

    private static final String USER_USER = "sendermk1991@yandex.ru";


    public static void main(String args[]) throws SQLException, ClassNotFoundException{



    	String DB_URL = "jdbc:mysql://localhost:3306/std_dep_student";

        Connection conn = null;

        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "ohdude1991");

        Class.forName("com.mysql.jdbc.Driver");

        conn = DriverManager.getConnection(DB_URL, connectionProps);

///////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        String sql = "SELECT * FROM std_dep_student.student";

        Statement statement = conn.createStatement();

        ResultSet rs = statement.executeQuery(sql);

        StringBuilder sb = new StringBuilder();
        while(rs.next()) {

        	sb.append(
        			String.format(" %s :: %s :: %s %n",
        			rs.getInt("studentid"),
        			rs.getString("lastName"),
        			rs.getString("emailparent")));
        }
        	System.out.println(sb);
System.out.println("11111111111111111111111111111");
///////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        String sql1 = "SELECT * FROM std_dep_student.student sss where sss.studentid=?";

        PreparedStatement pstmt = conn.prepareStatement(sql1);

        pstmt.setString(1, "17293");

        rs = pstmt.executeQuery();

        //while(rs.next()) {

        	//System.out.printf(" :: %s %n",
        		//	rs.getString("lastName"),
        			String str = rs.getString("emailparent");
          //}
        System.out.println("22222222222222222222222");

        System.out.println(str);
       // String to = rs.getString("emailparent");

////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        ///////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        String sql3 = "SELECT * FROM std_dep_student.student sss where sss.studentid=?";

        PreparedStatement pstmt1 = conn.prepareStatement(sql3);

        pstmt.setString(1, "17293");

        ResultSet rs1 = statement.executeQuery(sql);

         StringBuilder Example3 = new StringBuilder();
        while(rs.next()) {

        	sb.append(
        			String.format("%s :: %s %n",
        			//rs.getString("lastName"),
        			rs.getString("emailparent")));
        }
                System.out.println(Example3);
               String to = rs.getString("emailparent");
System.out.println("11111111111111111111111111111");

conn.close();

/********************/
/*

    	SMTPSessionFactory smtpSessionFactory = SMTPSessionFactory.getSMTPSessionFactory();

    	//SMTPSessionFactory smtpSessionFactory1 = new SMTPSessionFactory();

    	Session session = smtpSessionFactory.getSMTPSession(
    														GMAIL_HOST,
    														GMAIL_USER,
    														GMAIL_PASSWORD);
    	    	session.setDebug(true);


    	MailSender mailSender = new MailSender();
    	mailSender.setSession(session);

    	try {
    		String subject = "Test1";
    		Date date = new Date();
    		String encoding = "us-ascii";
    		String message = "Hello";

    		mailSender.sendMail(GMAIL_HOST,
    							to,
    							subject,
    							date,
    							encoding,
    							message);

    	} catch (MessagingException mex) {
			mex.printStackTrace();
			Exception ex = null;
			if ((ex = mex.getNextException()) != null) {
				ex.printStackTrace();
			}
		}

    }

}

class MailSender {
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

class SMTPSessionFactory {
	private static final SMTPSessionFactory smtpSessionFactory = new SMTPSessionFactory();

	private SMTPSessionFactory (){

	}

	public static SMTPSessionFactory getSMTPSessionFactory(){
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

/*StringBuilder method (StringBuilder sb) {

    return sb;

}*/
