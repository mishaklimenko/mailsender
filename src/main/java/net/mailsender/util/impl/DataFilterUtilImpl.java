package net.mailsender.util.impl;


import java.util.ArrayList;
import java.util.List;

import net.mailsender.model.ExamMark;
import net.mailsender.model.ExamMarkMask;
import net.mailsender.model.Student;
import net.mailsender.util.DataFilterUtil;


public class DataFilterUtilImpl implements DataFilterUtil {
    public final static String EMAIL_REGEX = "^([\\w-]*?([\\w-]+\\.)*?[\\w-]+?)@((?:[\\w-]+\\.)+)([a-zA-Z]{2,4})$";

    private int maxBall = 60;

	public int getMaxBall() {
		return maxBall;
	}

	public void setMaxBall(int maxBall) {
		this.maxBall = maxBall;
	}
///
        //Для выведения оценок от 4 баллов
            private int minBall = 75;

	public int getMinBall() {
		return minBall;
	}


	public void setMinBall(int maxBall) {
		this.minBall = maxBall;
	}
///

	@Override
	public List<ExamMark> filterExamMarks(List<ExamMark> exammarks){
        List<ExamMark> filteredList = new ArrayList<ExamMark>();

        	boolean flag = true;

        	for(ExamMark em : exammarks) {
        		//получение текстрового представления балла
        		String currentBall = em.getBall();

        		int ball = 0;

        		try {
            		//проверка что балл это число
        			ball = Integer.valueOf(currentBall).intValue();

        			//проверка того что балл это на самом деле балл
        			if (ExamMarkMask.ETSC.contains(String.valueOf(ball)) == false) flag = false;

        			//проверка что балл больше или меньше 60
        			if (ball > maxBall) {flag = false;}

        		} catch (NumberFormatException nfe) {
        			flag = false;
        		}


        		//если все проверки прошли - добавить оценку в отфильтрованный список
        		if (flag) filteredList.add(em);

        		flag = true;
        	}

        return filteredList;
    }
///
        	public List<ExamMark> filterExamMarks1(List<ExamMark> exammarks){
        List<ExamMark> filteredList = new ArrayList<ExamMark>();

        	boolean flag = true;

        	for(ExamMark em : exammarks) {
        		//получение текстрового представления балла
        		String currentBall = em.getBall();

        		int ball = 0;

        		try {
            		//проверка что балл это число
        			ball = Integer.valueOf(currentBall).intValue();

        			//проверка того что балл это на самом деле балл
        			if (ExamMarkMask.ETSC.contains(String.valueOf(ball)) == false) flag = false;

        			//проверка что балл больше или меньше 60
        			if (ball > minBall) {flag = false;}

        		} catch (NumberFormatException nfe) {
        			flag = false;
        		}


        		//если все проверки прошли - добавить оценку в отфильтрованный список
        		if (flag) filteredList.add(em);

        		flag = true;
        	}

        return filteredList;
    }
///

	public List<Student> filterEmptyEmailParent(List<Student> students) {
    	List<Student> filtered = new ArrayList<Student>();

    	for(Student s : students) {
    		if ((s.getEmailParent() != null) && (s.getEmailParent().matches(EMAIL_REGEX))) {
    			filtered.add(s);
    		}
    	}

    	return filtered;
    }

}