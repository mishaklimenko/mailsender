package net.mailsender.util;

import java.util.List;
import java.util.Properties;

import net.mailsender.model.ExamMark;
import net.mailsender.model.Message;
import net.mailsender.model.Student;

public class MessageGeneratorUtil {
	public String templateText = " Студент %token1% "
								+ "%token2% "
								+ "%token3%"
                                                                +" имеет такие оценки ";

	private Properties messageTemplates;

	public void setMessageTemplates(Properties messageTemplates) {
		this.messageTemplates = messageTemplates;
	}


	public Message composeMessage(Student student,
									List<ExamMark> lowerExamMarks,
									List<ExamMark> higherExamMarks,
									boolean useLower,
									boolean useHigher){

		Message message = new Message();

		StringBuilder sb = new StringBuilder();

		sb.append(formatStudentData(student)+"\n");

		if (useLower) sb.append(formatLowerExamMarksData(lowerExamMarks)+"\n");
		if (useHigher) sb.append(formatHigherExamMarksData(higherExamMarks)+"\n");

		message.setEmail(student.getEmailParent());
		message.setMessage(sb.toString());

		return message;
    }

	private String formatStudentData(Student student){

		String finalTextString = messageTemplates.getProperty("msg.student");

		finalTextString = finalTextString + messageTemplates.getProperty("template.student")
				.replace("%token1%", (student.getLastName() != null) ? student.getLastName() : "" )
                                .replace("%token2%", (student.getName() != null) ? student.getName() : "" )
                                .replace("%token3%", (student.getMiddLenam() != null) ? student.getMiddLenam() : "" );

		return finalTextString;
	}

	private String formatLowerExamMarksData(List<ExamMark> examMarks){
                StringBuilder sb = new StringBuilder();
                    for (ExamMark e : examMarks) {
                        sb.append(String.format("[LOWER] Предмет :: %s %s по пятибальной системе %s %n", e.getSubject(),
                                                                                e.getBall(),
                                                                                e.getMark()));
                    }

		return sb.toString();
	}

	private String formatHigherExamMarksData(List<ExamMark> examMarks){
		StringBuilder sb = new StringBuilder();
		for (ExamMark e : examMarks) {
			sb.append(String.format("[HIGHER] Предмет :: %s %s по пятибальной системе %s %n", e.getSubject(),
					e.getBall(),
					e.getMark()));
		}
		return sb.toString();
	}
}