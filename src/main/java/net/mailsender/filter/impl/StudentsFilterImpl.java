package net.mailsender.filter.impl;


import java.util.ArrayList;
import java.util.List;

import net.mailsender.filter.StudentsFilter;
import net.mailsender.model.ExamMark;
import net.mailsender.model.ExamMarkMask;
import net.mailsender.model.Student;


public class StudentsFilterImpl implements StudentsFilter {
    public final static String EMAIL_REGEX = "^([\\w-]*?([\\w-]+\\.)*?[\\w-]+?)@((?:[\\w-]+\\.)+)([a-zA-Z]{2,4})$";

    @Override
	public List<Student> filterStudents(List<Student> students) {
    	List<Student> filtered = new ArrayList<Student>();

    	for(Student s : students) {
    		if ((s.getEmailParent() != null) && (s.getEmailParent().matches(EMAIL_REGEX))) {
    			filtered.add(s);
    		}
    	}

    	return filtered;
    }

}