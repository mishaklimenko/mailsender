package net.mailsender.util;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.ListModel;

import net.mailsender.model.ExamMark;
import net.mailsender.model.ExamMarkMask;
import net.mailsender.model.Student;

        //Для выведения оценок от 4 баллов
public class DataFilter2Util {
///
            private int minBall = 75;

	public int getMinBall() {
		return minBall;
	}

	public void setMinBall(int maxBall) {
		this.minBall = maxBall;
	}
///        
///
        	public List<ExamMark> filterExamMarks2(List<ExamMark> exammarks){
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

        			//проверка что балл больше или меньше 75
        			if (ball < minBall) {flag = false;}

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
}