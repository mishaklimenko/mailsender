package src.main.java.net.mailsender.util;

import java.util.List;

import net.mailsender.model.ExamMark;
import net.mailsender.model.Student;

public interface ExammarksFilterUtil {
    public List<ExamMark> filterExamMarks(List<ExamMark> exammarks);
}


