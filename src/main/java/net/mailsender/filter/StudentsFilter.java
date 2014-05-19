/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.mailsender.filter;

import java.util.List;

import net.mailsender.model.ExamMark;
import net.mailsender.model.Student;

public interface StudentsFilter {
    public List<Student> filterStudents(List<Student> students);
}
