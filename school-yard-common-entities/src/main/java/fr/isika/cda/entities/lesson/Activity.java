package fr.isika.cda.entities.lesson;

import fr.isika.cda.entities.common.AcademicLevel;
import fr.isika.cda.entities.common.SubjectEnum;
import fr.isika.cda.entities.teacher.Teacher;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Activity implements Serializable {

	private static final long serialVersionUID = -9156916020990785127L;
	@Id
	@GeneratedValue
	protected Long id;
	
	@Enumerated(EnumType.STRING)
	protected SubjectEnum subject;
	
	@Enumerated(EnumType.STRING)
	protected AcademicLevel level;

	@ManyToOne
	protected Teacher teacher;
	private String title;

	protected Activity(SubjectEnum subject, AcademicLevel level, Teacher teacher, String title) {
		this.subject = subject;
		this.level = level;
		this.teacher = teacher;
		this.title = title;
	}

	protected Activity() {
	}

	public Long getId() {
		return id;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
