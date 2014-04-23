package net.mailsender.util;

import java.util.List;

import net.mailsender.model.ExamMark;
import net.mailsender.model.Message;
import net.mailsender.model.Student;

public class MessageGeneratorUtil {
	public String templateText = " Студент %token1% "
								+ "%token2% "
								+ "%token3%"
                                                                +" имеет такие оценки ";

	public Message composeMessage(Student student, List<ExamMark> examMarks){
		Message message = new Message();

		String messageBody = formatStudentData(student) + "\n"+ formatExamMarksData(examMarks);

		message.setEmail(student.getEmailParent());
		message.setMessage(messageBody);

		return message;
    }

	private String formatStudentData(Student student){
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("Some text :: %s %s %s %n", student.getLastName(),
				student.getMiddLenam(),
				student.getName()));

		String finalText = templateText.replace("%token1%", student.getLastName())
                                                .replace("%token2%", student.getName())
                                                .replace("%token3%", student.getMiddLenam());
		//return sb.toString();
		return finalText;
	}

	private String formatExamMarksData(List<ExamMark> examMarks){
                StringBuilder sb = new StringBuilder();
                    for (ExamMark e : examMarks) {
                        sb.append(String.format("Предмет :: %s %s по пятибальной системе %s %n", e.getSubject(),
                                                                                e.getBall(),
                                                                                e.getMark()));
                    }

				//exammark.getName()));


                                                //.replace("%token5%", student.getName())
                                                //.replace("%token6%", student.getMiddLenam());
		return sb.toString();
	}
}