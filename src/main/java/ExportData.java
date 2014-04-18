import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Таня on 23.12.13.
 */
public class ExportData {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@10.0.0.1:1521:xe";

    //  Database credentials
    static String USER = "USER";
    static String PASS = "PASSWORD";

    static String USER2 = "STD_DEP_STUDENT";
    static String PASS2 = "studentPassOracle";

    public static Connection conn2 = null;
    public static Statement stmt2 = null;

    static ArrayList<String> sqlInserts = new ArrayList<String>();
    public static Connection conn = null;
    public static Statement stmt = null;

    public static void selecInserts(String table){
        String sql;
        if (table.equals("CT_STUDENT")){
           sql = "SELECT CT_STUDENT.ID AS studentId, ct_group.group_number AS studyGroup, ct_faculty.name AS faculty, ct_speciality_upd.name AS speciality,\n" +
                    "ct_chair.chair_number AS chair, ct_student.e_mail AS email, \n" +
                    "ct_course.course_number AS course, ct_person.last_name_ukr AS lastName, \n" +
                    "ct_person.name_ukr AS name, ct_person.second_name_ukr AS middleName, ct_group.id AS idGroup, ct_student.e_mail_parent AS eMailParent FROM \n" +
                    "  CT_STUDENT, \n" +
                    "  ct_group, \n" +
                    "  CT_FACULTY, \n" +
                    "  ct_speciality_upd, \n" +
                    "  CT_CHAIR, \n" +
                    "  CT_PERSON, \n" +
                    "  ct_course WHERE \n" +
                    "ct_student.id_group = ct_group.id AND\n" +
                    "ct_student.id_person = ct_person.id AND\n" +
                    "ct_group.id_course = ct_course.id AND\n" +
                    "ct_group.id_faculty = ct_faculty.id AND\n" +
                    "ct_group.id_chair = ct_chair.id AND\n" +
                    "ct_group.id_speciality_upd = ct_speciality_upd.id AND\n" +
                    "(ct_student.e_mail_parent is not null or ct_student.e_mail is not null)";
                    runStudentDB(sql, table, "std");

        }
        else if (table.equals("CT_JOURNAL")){
            sql = "select ct_journal_student.id as ID, ct_pair_date.day as DATEFILL, ct_discipline.name as DISCIPLINENAME, \n" +
                    "ct_journal_student.mark as MARK, \n" +
                    "ct_journal_student.id_vizit as VISIT, ct_academic_year.name as YEAR, ct_student.id as STUDENTID, \n" +
                    "ct_term.numb AS SEMESTER\n" +
                    "from ct_journal_student, ct_student, ct_discipline, ct_term, ct_academic_year, ct_pair_date where \n" +
                    "ct_pair_date.id = ct_journal_student.id_pair_date and\n" +
                    "ct_discipline.id = ct_journal_student.id_discipline and\n" +
                    "ct_term.id = ct_journal_student.id_term and\n" +
                    "ct_academic_year.id = ct_journal_student.id_academic_year and\n" +
                    "ct_journal_student.id_student = ct_student.id and\n" +
                    "(ct_student.e_mail_parent is not null or ct_student.e_mail is not null)";
            runStudentDB(sql, table, "std");
        }

        else{
            sql = "select ct_student_vedomost.id as EXAMMARKID, \n" +
                    "ct_student_vedomost.id_student as STUDENTID, \n" +
                    "ct_discipline.short_name as subject, \n" +
                    "ct_term.id as semesterId,\n" +
                    "ct_academic_year.name as year,\n" +
                    "ct_student_vedomost.mark_date as datefill, \n" +
                    "ct_mark_b.mark as mark, \n" +
                    "ct_mark_b.etsc as etsc, \n" +
                    "ct_mark_b.name as ball, \n" +
                    "ct_term.numb,\n" +
                    "ct_check_form.check_form as form,\n" +
                    "ct_academic_year.name as year\n" +
                    "from ct_student_vedomost, ct_student, ct_discipline, ct_academic_year, ct_mark_b, ct_term, ct_check_form where\n" +
                    "ct_check_form.id = ct_student_vedomost.id_check_form and\n" +
                    "ct_term.id = ct_student_vedomost.id_term and\n" +
                    "ct_mark_b.id = ct_student_vedomost.id_mark_b and\n" +
                    "ct_academic_year.id = ct_student_vedomost.id_academic_year and\n" +
                    "ct_student_vedomost.id_student = ct_student.id and\n" +
                    "ct_discipline.id = ct_student_vedomost.id_discipline and\n" +
                    "(ct_student.e_mail_parent is not null or ct_student.e_mail is not null)";
                    runStudentDB(sql, table, "std");

        }

    }


        public static void runStudentDB(String sql, String table, String user){
        try{

            if (!user.equals("std")){
                USER = "STD_DEP_STUDENT";
                PASS = "studentPassOracle";
            }else{
                USER = "STDDEP";
                PASS = "eadwebdb";
            }

            //STEP 2: Register JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            conn2 = DriverManager.getConnection(DB_URL, USER2, PASS2);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            stmt2 = conn2.createStatement();

            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            if (table.equals("CT_STUDENT")){
                while(rs.next()){
                    String lastName=rs.getString("lastName"), name=rs.getString("name"), middleName =rs.getString("middleName");
                    String speciality = rs.getString("speciality");
                    if (rs.getString("lastName")!=null)
                        lastName = rs.getString("lastName").replaceAll("'","");
                    if (rs.getString("name")!=null)
                        name = rs.getString("name").replaceAll("'","");
                    if (rs.getString("middleName")!=null)
                        middleName = rs.getString("middleName").replaceAll("'","");
                    if (rs.getString("speciality")!=null)
                        speciality = rs.getString("speciality").replaceAll("'","");

                    sql = "SELECT * FROM student WHERE studentid = " + "" + rs.getInt("studentId");
                    System.out.println(sql);
                    ResultSet USER = stmt2.executeQuery(sql);
                    String str = "";
                    if(!USER.next())
                        str = "INSERT INTO STUDENT (studentid, studygroup, faculty, speciality, chair, email, usercode, course, lastName, name, middleName, idGroup, eMailParent) VALUES ("+rs.getInt("studentId")+",'"+rs.getString("studyGroup")+"','"+rs.getString("faculty")+"','"+speciality+"','"+rs.getString("chair")+"','"+rs.getString("email")+"','','"+rs.getString("course")+"','"
                            +lastName+"','"+name+"','"+middleName+"',"+rs.getInt("idGroup")+",'"+rs.getString("eMailParent")+"')";
                    else{
                        if (rs.getString("lastName")!=null)
                            lastName = rs.getString("lastName").replaceAll("'","");
                        str = "UPDATE STUDENT SET studygroup = '"+rs.getString("studyGroup")+"', " +
                                "faculty = '"+rs.getString("faculty")+"', speciality = '"+
                                speciality+"', chair = '"+
                                rs.getString("chair")+"', email = '"+
                                rs.getString("email")+"', course = '"+
                                rs.getString("course")+"', " +
                                "lastName = '"+lastName+"', name = '"+name+"', middleName = '"+middleName+"', " +
                                "idGroup = "+rs.getInt("idGroup")+", eMailParent = '"+rs.getString("eMailParent")+
                                "' where studentId = "+rs.getInt("studentId");
                        str = str.replaceAll("null", "");
                    }

                    //Retrieve by column name
                    sqlInserts.add(str);
                    System.out.println(str);
                    //Display values
                }
            }
            else if (table.equals("CT_JOURNAL")){
                while(rs.next()){
                    String DISCIPLINENAME=rs.getString("DISCIPLINENAME");
                    if (rs.getString("DISCIPLINENAME")!=null)
                        DISCIPLINENAME = rs.getString("DISCIPLINENAME").replaceAll("'","");
                    //System.out.println(rs.getInt("id"));
                    System.out.println(rs.getString("DATEFILL"));
                    System.out.println(rs.getString("MARK"));
                    System.out.println(rs.getString("SEMESTER"));
                    System.out.println(rs.getString("VISIT"));
                    System.out.println(rs.getString("YEAR"));
                    System.out.println(rs.getInt("STUDENTID"));

                    String str = "INSERT INTO JOURNALSTUDENTMARK (id, DATEFILL, DISCIPLINENAME, MARK, SEMESTER, VISIT, YEAR, STUDENTID) " +
                            "VALUES ('"+rs.getInt("ID")+"', '"+rs.getString("DATEFILL")+"', '"+DISCIPLINENAME+"', '"+rs.getString("MARK")+"', '"+rs.getString("SEMESTER")
                            +"', '"+rs.getString("VISIT")+"', '"+rs.getString("YEAR")+"', '"+rs.getInt("STUDENTID")+"')";
                    //Retrieve by column name
                    str = str.replaceAll("null", "");
                    System.out.println(str);
                    sqlInserts.add(str);
                    System.out.println(str);
                    //Display values
                }            }

            else{
                while(rs.next()){
                    String subject=rs.getString("subject");
                    if (rs.getString("subject")!=null)
                        subject = rs.getString("subject").replaceAll("'","");
                    String str = "INSERT INTO EXAMMARK (EXAMMARKID, STUDENTID, SEMESTERID, SUBJECT, MARK, BALL, FORM, TEACHER, YEAR, DATEFILL, ETSC) VALUES " +
                            "('"+rs.getInt("EXAMMARKID")+"', '"+rs.getInt("STUDENTID")+"', '"+rs.getInt("SEMESTERID")+"', '"+subject+"', '"+rs.getString("MARK")+
                            "', '"+rs.getString("BALL")+"', '"+rs.getString("FORM")+"', '', '"+rs.getString("YEAR")+"', '', '"+rs.getString("ETSC")+"')";
                    //Retrieve by column name
                    //SSystem.out.println(str);
                    str = str.replaceAll("null", "");
                    sqlInserts.add(str);
                    //Display values
                }
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }



    public static void runStudentMarksExportDB(String deleteStudentSQL){
        try{

            USER = "STD_DEP_STUDENT";
            PASS = "studentPassOracle";
            //STEP 2: Register JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            conn.setAutoCommit(false);
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
          stmt = conn.createStatement();
            try{
                stmt.execute(deleteStudentSQL);
            }
            catch (SQLException s){
                System.out.println("SQL statement is not executed!");
            }

            //conn.commit();

    //STEP 5: Extract data from result set
            System.out.println(sqlInserts.size());
            Statement ps = conn.createStatement();

            System.out.println("Всего оценко в журнале = "+sqlInserts.size());
            //int count = 1;
            for (String sql: sqlInserts){
                System.out.println(sql+";");
                //count++;
                //tmt.executeUpdate(sql);
                ps.addBatch(sql);
            }

            ps.executeBatch();
            conn.commit();
            ps.close();
            //STEP 6: Clean-up environment
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }
    public static void main(String[] args) {

/*        selecInserts("CT_STUDENT");
        runStudentMarksExportDB("DELETE FROM student");
        sqlInserts.clear();*/

        selecInserts("CT_VEDOMOST");
        runStudentMarksExportDB("DELETE FROM EXAMMARK");
        sqlInserts.clear();

        selecInserts("CT_JOURNAL");
        runStudentMarksExportDB("DELETE FROM JOURNALSTUDENTMARK");
        sqlInserts.clear();

    }//end main
}
