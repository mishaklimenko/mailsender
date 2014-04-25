package net.mailsender;
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
import net.mailsender.model.ExamMarkMask;
import net.mailsender.model.Message;
import net.mailsender.service.MessageGetterService;
import net.mailsender.util.MessageGeneratorUtil;
import net.mailsender.util.DataFilterUtil;
import net.mailsender.util.impl.DataFilter2UtilImpl;
import net.mailsender.util.impl.DataFilterUtilImpl;




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


        /*=============StudentDao Demo #1*/
        /*
        StudentDao studentDao = new StudentDao();

        studentDao.setConn(conn);


        List<Student> students =  studentDao.getStudents();
        for(Student s : students){
        	//System.out.printf("[S] %s%n", s.getStudentId());

        	List<ExamMark> examMarks = studentDao.getStudentExamMarks(s.getStudentId());

            MessageGenerator mg = new MessageGenerator();
            Message message = mg.composeMessage(s, examMarks);
            System.out.printf("[M] %s", message);

        	for(ExamMark em : examMarks) {
        		//System.out.printf("   [E] %s%n", em.getExamMarkId());
        	}

        }
        */

        /*=============StudentDao Demo #2*/

        StudentDao studentDao = new StudentDao();
        		   studentDao.setConn(conn);

	    MessageGeneratorUtil mg = new MessageGeneratorUtil();


	    DataFilterUtil df = new DataFilterUtilImpl();

                                  // df.setMinBall(75);

        MessageGetterService mgs = new  MessageGetterService();
        					 mgs.setStudentDao(studentDao);
        					 mgs.setMessageGenerator(mg);
        					 mgs.setDataFilter(df);

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


        System.out.printf("%s%n", ExamMarkMask.MARK);
        System.out.printf("%s%n", ExamMarkMask.BALL);
        System.out.printf("%s%n", ExamMarkMask.ETSC);
        System.out.printf("%s%n", ExamMarkMask.FORM);
        System.out.printf("%s%n", ExamMarkMask.DEBT_MARKS);
        System.out.printf("%s%n", ExamMarkMask.FREED_MARKS);



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

