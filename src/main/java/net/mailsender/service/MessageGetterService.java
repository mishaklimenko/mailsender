package net.mailsender.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.mailsender.dao.StudentDao;
import net.mailsender.model.ExamMark;
import net.mailsender.model.Message;
import net.mailsender.model.Student;
import net.mailsender.util.DataFilterUtil;
import net.mailsender.util.MessageGeneratorUtil;

public class MessageGetterService {

	private StudentDao studentDao;
	private MessageGeneratorUtil messageGenerator;
	private DataFilterUtil dataFilter;

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
	 * @return the dataFilter
	 */
	public DataFilterUtil getDataFilter() {
		return dataFilter;
	}

	/**
	 * @param dataFilter the dataFilter to set
	 */
	public void setDataFilter(DataFilterUtil dataFilter) {
		this.dataFilter = dataFilter;
	}

	public List<Message> getMessages() throws ClassNotFoundException, SQLException {
        List<Message> messages = new ArrayList<Message>();

        List<Student> students =  studentDao.getStudents();

        students = dataFilter.filterEmptyEmailParent(students);

        for(Student s : students){
        	List<ExamMark> examMarks = studentDao.getStudentExamMarks(s.getStudentId());

        	List<ExamMark> filteredExamMarks = dataFilter.filterExamMarks(examMarks);


        	Message message = messageGenerator.composeMessage(s, filteredExamMarks);
        			messages.add(message);
        }

		return messages;
	}

}