package net.mailsender.util;


import java.util.List;

import net.mailsender.model.ExamMark;
import net.mailsender.model.Student;


public interface DataFilterUtil {

	public List<ExamMark> filterExamMarks(List<ExamMark> exammarks);

}