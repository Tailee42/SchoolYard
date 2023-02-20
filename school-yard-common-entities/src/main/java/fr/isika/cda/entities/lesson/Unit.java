package fr.isika.cda.entities.lesson;

import fr.isika.cda.entities.common.SchoolTypeEnum;
import fr.isika.cda.entities.common.SubjectEnum;
import fr.isika.cda.entities.teacher.Teacher;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Unit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1715197209875615625L;

	@Id
	@GeneratedValue
	private Long id;

	private String title;

	private String content;

	private boolean visibility;

	@Enumerated(EnumType.STRING)
	private SubjectEnum subject;

	@Enumerated(EnumType.STRING)
	private SchoolTypeEnum level;

	@ManyToOne
	private Teacher teacher;

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

	public SchoolTypeEnum getLevel() {
		return level;
	}

	public void setLevel(SchoolTypeEnum level) {
		this.level = level;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
}
