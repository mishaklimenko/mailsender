package net.mailsender.model;

public class Student {
	private int studentId;
	private String studyGroup;
	private int idGroup;
	private String faculty;
	private String speciality;
	private String chair;
	private String emailParent;
	private String email;
	private String userCode;
	private String course;
	private String lastName;
	private String name;
	private String middLenam;
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
	 * @return the studyGroup
	 */
	public String getStudyGroup() {
		return studyGroup;
	}
	/**
	 * @param studyGroup the studyGroup to set
	 */
	public void setStudyGroup(String studyGroup) {
		this.studyGroup = studyGroup;
	}
	/**
	 * @return the iDgroup
	 */
	public int getIdGroup() {
		return idGroup;
	}
	/**
	 * @param iDgroup the iDgroup to set
	 */
	public void setIdGroup(int iDgroup) {
		this.idGroup = iDgroup;
	}
	/**
	 * @return the faculty
	 */
	public String getFaculty() {
		return faculty;
	}
	/**
	 * @param faculty the faculty to set
	 */
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	/**
	 * @return the speciality
	 */
	public String getSpeciality() {
		return speciality;
	}
	/**
	 * @param speciality the speciality to set
	 */
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	/**
	 * @return the chair
	 */
	public String getChair() {
		return chair;
	}
	/**
	 * @param chair the chair to set
	 */
	public void setChair(String chair) {
		this.chair = chair;
	}
	/**
	 * @return the emailParent
	 */
	public String getEmailParent() {
		return emailParent;
	}
	/**
	 * @param emailParent the emailParent to set
	 */
	public void setEmailParent(String emailParent) {
		this.emailParent = emailParent;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the userCode
	 */
	public String getUserCode() {
		return userCode;
	}
	/**
	 * @param userCode the userCode to set
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	/**
	 * @return the course
	 */
	public String getCourse() {
		return course;
	}
	/**
	 * @param course the course to set
	 */
	public void setCourse(String course) {
		this.course = course;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the middLenam
	 */
	public String getMiddLenam() {
		return middLenam;
	}
	/**
	 * @param middLenam the middLenam to set
	 */
	public void setMiddLenam(String middLenam) {
		this.middLenam = middLenam;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chair == null) ? 0 : chair.hashCode());
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((emailParent == null) ? 0 : emailParent.hashCode());
		result = prime * result + ((faculty == null) ? 0 : faculty.hashCode());
		result = prime * result + idGroup;
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((middLenam == null) ? 0 : middLenam.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((speciality == null) ? 0 : speciality.hashCode());
		result = prime * result + studentId;
		result = prime * result
				+ ((studyGroup == null) ? 0 : studyGroup.hashCode());
		result = prime * result
				+ ((userCode == null) ? 0 : userCode.hashCode());
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
		Student other = (Student) obj;
		if (chair == null) {
			if (other.chair != null)
				return false;
		} else if (!chair.equals(other.chair))
			return false;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (emailParent == null) {
			if (other.emailParent != null)
				return false;
		} else if (!emailParent.equals(other.emailParent))
			return false;
		if (faculty == null) {
			if (other.faculty != null)
				return false;
		} else if (!faculty.equals(other.faculty))
			return false;
		if (idGroup != other.idGroup)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (middLenam == null) {
			if (other.middLenam != null)
				return false;
		} else if (!middLenam.equals(other.middLenam))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (speciality == null) {
			if (other.speciality != null)
				return false;
		} else if (!speciality.equals(other.speciality))
			return false;
		if (studentId != other.studentId)
			return false;
		if (studyGroup == null) {
			if (other.studyGroup != null)
				return false;
		} else if (!studyGroup.equals(other.studyGroup))
			return false;
		if (userCode == null) {
			if (other.userCode != null)
				return false;
		} else if (!userCode.equals(other.userCode))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studyGroup=" + studyGroup
				+ ", iDgroup=" + idGroup + ", faculty=" + faculty
				+ ", speciality=" + speciality + ", chair=" + chair
				+ ", emailParent=" + emailParent + ", email=" + email
				+ ", userCode=" + userCode + ", course=" + course
				+ ", lastName=" + lastName + ", name=" + name + ", middLenam="
				+ middLenam + "]";
	}

}

