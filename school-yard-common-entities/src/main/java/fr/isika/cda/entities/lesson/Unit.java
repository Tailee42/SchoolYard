package fr.isika.cda.entities.lesson;

import fr.isika.cda.entities.common.AcademicLevel;
import fr.isika.cda.entities.common.SchoolTypeEnum;
import fr.isika.cda.entities.common.SubjectEnum;
import fr.isika.cda.entities.teacher.Teacher;

import java.io.Serializable;

import javax.persistence.*;

import org.primefaces.model.charts.optionconfig.title.Title;

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

	@Enumerated(EnumType.STRING)
	private UnitStatusEnum status;

	@Enumerated(EnumType.STRING)
	private SubjectEnum subject;

	@Enumerated(EnumType.STRING)
	private AcademicLevel level;

	@ManyToOne
	private Teacher teacher;

	public Unit() {
		this.teacher = new Teacher();
	}

	public Unit(String title, String content, UnitStatusEnum status, SubjectEnum subject, AcademicLevel level, Teacher teacher) {
		this.title = title;
		this.content = content;
		this.status = status;
		this.subject = subject;
		this.level = level;
		this.teacher = teacher;
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

	public void setStatus(UnitStatusEnum status) {
		this.status = status;
	}

	public UnitStatusEnum getStatus() {
		return status;
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

}
