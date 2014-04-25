package net.mailsender.filter;

import java.util.List;

import net.mailsender.model.ExamMark;
import net.mailsender.model.Student;

public interface ExammarksFilter {
    public void filterExamMarks(List<ExamMark> examMarks);
    public List<ExamMark> getLower();
    public List<ExamMark> getHigher();

    public void setMinBall(int minBall);

}