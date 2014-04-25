package net.mailsender.filter.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import net.mailsender.filter.ExammarksFilter;
import net.mailsender.model.ExamMark;
import net.mailsender.model.ExamMarkMask;

public class ExamMarksFilterImpl implements ExammarksFilter{
	final static Logger logger = Logger.getLogger(ExamMarksFilterImpl.class);

	private List<ExamMark> lower;
	private List<ExamMark> higher;

	private int minBall = 60;

	public void setMinBall(int minBall) {
		this.minBall = minBall;
	}

	@Override
	public void filterExamMarks(List<ExamMark> examMarks) {

    	this.lower = new ArrayList<ExamMark>();
    	this.higher = new ArrayList<ExamMark>();

        /*flag = -1  - передаёт значение в lower
         *flag = 0  - не передавать значание ни в какой список вообще
		 *flag = 1  - передаёт значение higher
         */
    	int flag = 0;

    	//некое текущее значение для балла
		int ball = 0;

		//прокручиваем все оценки для какого-то студента
    	for(ExamMark em : examMarks) {

    		//проверка по баллам
    		//получение текстрового представления балла
    		String currentBall = em.getBall();


    		if (logger.isDebugEnabled()) logger.debug(String.format("filterExamMarks(..) :: CurrentBall %s %n", currentBall));

    		//тест на то что балл это какое-то значение
    		//из допустимого диапазона неявок и всего такого
    		if (ExamMarkMask.DEBT_MARKS.contains(currentBall)) {
    			flag = -1;
    			listSelector(em, flag);
    			continue;
    		}

    		//тест на то что ball == int
    		try {
    			ball = Integer.valueOf(currentBall).intValue();
    		} catch (NumberFormatException nfe) {
    			flag = 0;
    			continue;
    		}

    		//тест на то что балл это на самом деле балл
    		if (!ExamMarkMask.BALL.contains(String.valueOf(ball))) {
    			flag = 0;
    			continue;
    		}

    		//тест на то что балл подходит или нет по заданному критерию
    		//т.е. что балл < или > чего-то там
    		if (ball <= minBall) flag = -1;
    		if (ball > minBall) flag = 1;

    		//решаем в какой список розместить эту оценку
    		listSelector(em, flag);
    	}
    }

    @Override
	public List<ExamMark> getLower() {
    	return this.lower;
    }
    @Override
	public List<ExamMark> getHigher() {
    	return this.higher;
    }

    private void listSelector(ExamMark em, int flag) {
		//System.out.prin)tf();
		if (logger.isDebugEnabled()) logger.debug(String.format("listSelector(..) :: flag=%s -- ball=%s %n", flag, em.getBall()));
		switch (flag) {
			case -1 : lower.add(em); break;
			case  1 : higher.add(em); break;
		}
    }

}
