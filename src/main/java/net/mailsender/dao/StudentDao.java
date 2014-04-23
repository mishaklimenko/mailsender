package net.mailsender.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.mailsender.model.ExamMark;
import net.mailsender.model.Student;

public class StudentDao {
	private static final String STUDENTS_SQL = "SELECT * FROM student";
	private static final String STUDENT_EXAMMARKS_SQL = "SELECT * FROM exammark where STUDENTID=?";

	private Connection conn = null;

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}


	public List<Student> getStudents() throws ClassNotFoundException, SQLException {

		List<Student> students = new ArrayList<Student>();

		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(STUDENTS_SQL);

		while(rs.next()) {

			Student student = new Student();

				student.setStudentId(rs.getInt("STUDENTID"));
				student.setStudyGroup(rs.getString("STUDYGROUP"));
				student.setIdGroup(rs.getInt("IDGROUP"));
				student.setFaculty(rs.getString("FACULTY"));
				student.setSpeciality(rs.getString("SPECIALITY"));
				student.setChair(rs.getString("CHAIR"));
				student.setEmailParent(rs.getString("EMAILPARENT"));
				student.setEmail(rs.getString("EMAIL"));
				student.setUserCode(rs.getString("USERCODE"));
				student.setCourse(rs.getString("COURSE"));
				student.setLastName(rs.getString("LASTNAME"));
				student.setName(rs.getString("NAME"));
				student.setMiddLenam(rs.getString("MIDDLENAME"));

			students.add(student);
		}

		return students;
	}

	public List<ExamMark> getStudentExamMarks(int studentId) throws ClassNotFoundException, SQLException{
		List<ExamMark> examMarks = new ArrayList<ExamMark>();

		PreparedStatement ps = conn.prepareStatement(STUDENT_EXAMMARKS_SQL);

		ps.setString(1, String.valueOf(studentId));

		ResultSet rs = ps.executeQuery();

		while(rs.next()) {
			ExamMark examMark = new ExamMark();

				examMark.setExamMarkId(rs.getInt("EXAMMARKID"));
				examMark.setStudentId(rs.getInt("STUDENTID"));
				examMark.setSemesterId(rs.getInt("SEMESTERID"));
				examMark.setSubject(rs.getString("SUBJECT"));
				examMark.setMark(rs.getString("MARK"));
				examMark.setBall(rs.getString("BALL"));
				examMark.setEtsc(rs.getString("ETSC"));
				examMark.setForm(rs.getString("FORM"));
				examMark.setTeacher(rs.getString("TEACHER"));
				examMark.setYear(rs.getString("YEAR"));
				examMark.setDateFill(rs.getString("DATEFILL"));

			examMarks.add(examMark);
		}

		return examMarks;
	}
}