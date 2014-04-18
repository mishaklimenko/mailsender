package net.mailsender.model;

public class ExamMark {
    private int examMarkId;
    private int studentId;
    private int semesterId;
    private String subject;
    private String mark;
    private String ball;
    private String etsc;
    private String form;
    private String teacher;
    private String year;
    private String dateFill;
	/**
	 * @return the examMarkId
	 */
	public int getExamMarkId() {
		return examMarkId;
	}
	/**
	 * @param examMarkId the examMarkId to set
	 */
	public void setExamMarkId(int examMarkId) {
		this.examMarkId = examMarkId;
	}
	/**
	 * @return the studentId
	 */
	public int getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	/**
	 * @return the semesterId
	 */
	public int getSemesterId() {
		return semesterId;
	}
	/**
	 * @param semesterId the semesterId to set
	 */
	public void setSemesterId(int semesterId) {
		this.semesterId = semesterId;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the mark
	 */
	public String getMark() {
		return mark;
	}
	/**
	 * @param mark the mark to set
	 */
	public void setMark(String mark) {
		this.mark = mark;
	}
	/**
	 * @return the ball
	 */
	public String getBall() {
		return ball;
	}
	/**
	 * @param ball the ball to set
	 */
	public void setBall(String ball) {
		this.ball = ball;
	}
	/**
	 * @return the etsc
	 */
	public String getEtsc() {
		return etsc;
	}
	/**
	 * @param etsc the etsc to set
	 */
	public void setEtsc(String etsc) {
		this.etsc = etsc;
	}
	/**
	 * @return the form
	 */
	public String getForm() {
		return form;
	}
	/**
	 * @param form the form to set
	 */
	public void setForm(String form) {
		this.form = form;
	}
	/**
	 * @return the teacher
	 */
	public String getTeacher() {
		return teacher;
	}
	/**
	 * @param teacher the teacher to set
	 */
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the dateFill
	 */
	public String getDateFill() {
		return dateFill;
	}
	/**
	 * @param dateFill the dateFill to set
	 */
	public void setDateFill(String dateFill) {
		this.dateFill = dateFill;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ball == null) ? 0 : ball.hashCode());
		result = prime * result
				+ ((dateFill == null) ? 0 : dateFill.hashCode());
		result = prime * result + ((etsc == null) ? 0 : etsc.hashCode());
		result = prime * result + examMarkId;
		result = prime * result + ((form == null) ? 0 : form.hashCode());
		result = prime * result + ((mark == null) ? 0 : mark.hashCode());
		result = prime * result + semesterId;
		result = prime * result + studentId;
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((teacher == null) ? 0 : teacher.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExamMark other = (ExamMark) obj;
		if (ball == null) {
			if (other.ball != null)
				return false;
		} else if (!ball.equals(other.ball))
			return false;
		if (dateFill == null) {
			if (other.dateFill != null)
				return false;
		} else if (!dateFill.equals(other.dateFill))
			return false;
		if (etsc == null) {
			if (other.etsc != null)
				return false;
		} else if (!etsc.equals(other.etsc))
			return false;
		if (examMarkId != other.examMarkId)
			return false;
		if (form == null) {
			if (other.form != null)
				return false;
		} else if (!form.equals(other.form))
			return false;
		if (mark == null) {
			if (other.mark != null)
				return false;
		} else if (!mark.equals(other.mark))
			return false;
		if (semesterId != other.semesterId)
			return false;
		if (studentId != other.studentId)
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (teacher == null) {
			if (other.teacher != null)
				return false;
		} else if (!teacher.equals(other.teacher))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return "ExamMark [examMarkId=" + examMarkId + ", studentId="
				+ studentId + ", semesterId=" + semesterId + ", subject="
				+ subject + ", mark=" + mark + ", ball=" + ball + ", etsc="
				+ etsc + ", form=" + form + ", teacher=" + teacher + ", year="
				+ year + ", dateFill=" + dateFill + "]";
	}

}