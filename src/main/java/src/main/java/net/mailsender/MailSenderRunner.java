package src.main.java.net.mailsender;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import net.mailsender.dao.StudentDao;
import net.mailsender.filter.ExammarksFilter;
import net.mailsender.filter.StudentsFilter;
import src.main.java.net.mailsender.filter.impl.ExamMarksFilterImpl;
import net.mailsender.filter.impl.StudentsFilterImpl;
import net.mailsender.model.ExamMarkMask;
import net.mailsender.model.Message;
import net.mailsender.service.MessageGetterService;
import net.mailsender.util.MessageGeneratorUtil;


public class MailSenderRunner {

    private static final String GMAIL_HOST = "gmail.com";
    private static final String GMAIL_USER = "sendermk1991@gmail.com";
    private static final String GMAIL_PASSWORD = "ohdude1991";

    private static final String YANDEX_MAILBOX_HOST = "yandex.ru";
    private static final String YANDEX_USER = "sendermk1991@yandex.ru";
    private static final String YANDEX_PASSWORD = "ohdude1991";

    private static final String USER_USER = "sendermk1991@yandex.ru";

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws SQLException, ClassNotFoundException{


    	/**
    	 * DB connection settings
    	 */
    	String DB_URL = "jdbc:mysql://localhost:3306/std_dep_student";
        Connection conn = null;

        Properties connectionProps = new Properties();
        connectionProps.put("user", "root");
        connectionProps.put("password", "ohdude1991");

        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL, connectionProps);
        /**
         * end
    	 * DB connection settings
    	 */

        /*=============StudentDao Demo #2*/

        StudentDao studentDao = new StudentDao();
        		   studentDao.setConn(conn);


        String dearParents = String.format("Уважаемые родители %n");

		String studentMessageTemplate =   " студент/студентка "
										+ " %token1% "
										+ " %token2% "
										+ " %token3% "
                                        + " имеет такие оценки: ";
		String badBoyGirl =
				" студент/студентка "
				+ " %token1% "
				+ " %token2% "
				+ " %token3% "
                + " учится просто отвратительно: ";


        Properties messageTemplatesLower = new Properties();
        			messageTemplatesLower.setProperty("msg.student", dearParents);
        			messageTemplatesLower.setProperty("template.student", studentMessageTemplate);

	    MessageGeneratorUtil mg = new MessageGeneratorUtil();
	    					mg.setMessageTemplates(messageTemplatesLower);

			    					

	    StudentsFilter sf = new StudentsFilterImpl();
	    ExammarksFilter emf = new ExamMarksFilterImpl();
	    				emf.setMinBall(60);

        MessageGetterService mgs = new  MessageGetterService();
        					 mgs.setStudentDao(studentDao);
        					 mgs.setMessageGenerator(mg);
        					 mgs.setStudentsFilter(sf);
        					 mgs.setExammarksFilter(emf);

        					 mgs.setUseLower(false);
        					 mgs.setUseHigher(true);


        List<Message> messages = mgs.getMessages();


        File outputFile = new File("MessagesText.txt");

        try {
            PrintWriter pw = new PrintWriter(outputFile);

            pw.println();

            for(Message m : messages){
            	pw.println(m.getEmail());
            	pw.println(m.getMessage());
            	//pw.flush();
            	System.out.printf("[M1] %s%n", m);
            }
        	pw.close();

        }catch (IOException ex) {
        	System.err.println(ex.getMessage());
        }
///1

/*
        System.out.printf("%s%n", ExamMarkMask.MARK);
        System.out.printf("%s%n", ExamMarkMask.BALL);
        System.out.printf("%s%n", ExamMarkMask.ETSC);
        System.out.printf("%s%n", ExamMarkMask.FORM);
        System.out.printf("%s%n", ExamMarkMask.DEBT_MARKS);
        System.out.printf("%s%n", ExamMarkMask.FREED_MARKS);

*/

        conn.close();

/*
    	MessageGetter messageGetter = new MessageGetter();
    		messageGetter.setConn(conn);


    	List<Message> messagesToSend = messageGetter.getMessages();

    	for (Message m : messagesToSend) {
    		System.out.printf("Recipient %s :: Message :: %s%n",
    										m.getEmail(),
    										m.getMessage());
    	}
*/

/*
    	SMTPSessionFactory smtpSessionFactory = SMTPSessionFactory.getSMTPSessionFactory();

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
    							YANDEX_USER,
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
*/
    }

}

