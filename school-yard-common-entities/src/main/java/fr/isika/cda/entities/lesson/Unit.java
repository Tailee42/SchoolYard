package fr.isika.cda.entities.lesson;

import fr.isika.cda.entities.common.AcademicLevel;
import fr.isika.cda.entities.common.SchoolTypeEnum;
import fr.isika.cda.entities.common.SubjectEnum;
import fr.isika.cda.entities.teacher.Teacher;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Unit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1715197209875615625L;

	@Id
	@GeneratedValue
	private Long id;

	private String title;
	
	@Lob
	private String content;

	private boolean visibility;

	@Enumerated(EnumType.STRING)
	private UnitStatusEnum status;

	@Enumerated(EnumType.STRING)
	private SubjectEnum subject;

	@Enumerated(EnumType.STRING)
	private AcademicLevel level;

	@ManyToOne
	private Teacher teacher;

	public Unit() {

	}

	public Unit(String title, String content, Teacher teacher, AcademicLevel level) {
		this.title = title;
		this.content = content;
		this.teacher = teacher;
		this.level = level;
		this.visibility = false;
		this.status = UnitStatusEnum.TOVALIDATE;
		this.subject = teacher.getSubject();
	}

	public void validate() {
		this.status = UnitStatusEnum.VALIDATED;
		this.visibility = true;
	}

	public void reject() {
		this.status = UnitStatusEnum.REJECTED;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	public SubjectEnum getSubject() {
		return subject;
	}

	public void setSubject(SubjectEnum subject) {
		this.subject = subject;
	}

	public AcademicLevel getLevel() {
		return level;
	}

	public void setLevel(AcademicLevel level) {
		this.level = level;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public void setStatus(UnitStatusEnum status) {
		this.status = status;
	}

	public UnitStatusEnum getStatus() {
		return status;
	}
}
