package fr.isika.cda.entities.teacher;

import javax.persistence.*;


import fr.isika.cda.entities.common.SchoolTypeEnum;
import fr.isika.cda.entities.common.SubjectEnum;
import fr.isika.cda.entities.school.Member;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Teacher extends Member {

	@Enumerated(EnumType.STRING)
	private SchoolTypeEnum schoolType;

	@Enumerated(EnumType.STRING)
	private SubjectEnum subject;

	@Enumerated(EnumType.STRING)
	private TeacherStatusEnum status;

	public Teacher(SchoolTypeEnum schoolType, SubjectEnum subject) {
		this.schoolType = schoolType;
		this.subject = subject;
		this.status = TeacherStatusEnum.InProgress;
	}

	public Teacher() {
	}

	/**
	 * Méthodes fonctionnelles "comportement"
	 */
	public void reject() {
		this.status = TeacherStatusEnum.Rejected;
	}
	
	public void validate() {
		this.status = TeacherStatusEnum.Approved;
	}
	
	public void eraseSchool() {
		this.school = null;
	}

	public SchoolTypeEnum getSchoolType() {
		return schoolType;
	}

	public void setSchoolType(SchoolTypeEnum schoolType) {
		this.schoolType = schoolType;
	}

	public void setSubject(SubjectEnum subject) {
		this.subject = subject;
	}

	public SubjectEnum getSubject() {
		return subject;
	}

	public void setStatus(TeacherStatusEnum status) {
		this.status = status;
	}

	public TeacherStatusEnum getStatus() {
		return status;
	}




}
