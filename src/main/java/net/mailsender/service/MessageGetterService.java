package net.mailsender.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.mailsender.dao.StudentDao;
import net.mailsender.filter.ExammarksFilter;
import net.mailsender.filter.StudentsFilter;
import net.mailsender.model.ExamMark;
import net.mailsender.model.Message;
import net.mailsender.model.Student;
import net.mailsender.util.MessageGeneratorUtil;

public class MessageGetterService {

	private boolean useLower = true;
	private boolean useHigher = true;

	private StudentDao studentDao;
	private MessageGeneratorUtil messageGenerator;
	private StudentsFilter studentFilter;
	private ExammarksFilter exammarksFilter;



	/**
	 * @param useLower the useLower to set
	 */
	public void setUseLower(boolean useLower) {
		this.useLower = useLower;
	}

	/**
	 * @param useHigher the useHigher to set
	 */
	public void setUseHigher(boolean useHigher) {
		this.useHigher = useHigher;
	}

	/**
	 * @param studentDao the studentDao to set
	 */
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	/**
	 * @param messageGenerator the messageGenerator to set
	 */
	public void setMessageGenerator(MessageGeneratorUtil messageGenerator) {
		this.messageGenerator = messageGenerator;
	}

	/**
	 * @return the studentFilter
	 */
	public StudentsFilter getStudentFilter() {
		return studentFilter;
	}

	/**
	 * @param studentFilter the studentFilter to set
	 */
	public void setStudentsFilter(StudentsFilter studentFilter) {
		this.studentFilter = studentFilter;
	}

	/**
	 * @return the exammarksFilter
	 */
	public ExammarksFilter getExammarksFilter() {
		return exammarksFilter;
	}

	/**
	 * @param exammarksFilter the exammarksFilter to set
	 */
	public void setExammarksFilter(ExammarksFilter exammarksFilter) {
		this.exammarksFilter = exammarksFilter;
	}

	public List<Message> getMessages() throws ClassNotFoundException, SQLException {

        List<Message> messages = new ArrayList<Message>();

        List<Student> students = studentDao.getStudents();
        	//отфильтровать всех студентов у которых отсутствует e-mail родителя
        	students = studentFilter.filterStudents(students);

        for(Student s : students){
        	List<ExamMark> examMarks = studentDao.getStudentExamMarks(s.getStudentId());

        		exammarksFilter.filterExamMarks(examMarks);

        		List<ExamMark> lowerMarks = exammarksFilter.getLower();
        		List<ExamMark> higherMarks = exammarksFilter.getHigher();

         	Message message = messageGenerator.composeMessage(s, lowerMarks, higherMarks, useLower, useHigher);
        			messages.add(message);
        }

		return messages;
	}

}
