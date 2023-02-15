package fr.isika.cda.entities.teacher;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import fr.isika.cda.entities.*;
import fr.isika.cda.entities.lesson.Activity;
import fr.isika.cda.entities.lesson.Unit;
import fr.isika.cda.entities.school.Member;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Teacher extends Member {

	@Enumerated(EnumType.STRING)
	private SchoolType level;

	@Enumerated(EnumType.STRING)
	private SubjectEnum subject;

	@Enumerated(EnumType.STRING)
	private TeacherStatusEnum status;

	@OneToMany
	private List<Activity> activity;

	@OneToMany
	private List<Unit> unit;

	public Teacher() {
		this.activity = new ArrayList<>();
		this.unit = new ArrayList<>();
	}

	public SchoolType getLevel() {
		return level;
	}

	public void setLevel(SchoolType level) {
		this.level = level;
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

	public void setActivity(List<Activity> activity) {
		this.activity = activity;
	}

	public List<Activity> getActivity() {
		return activity;
	}

	public void setUnit(List<Unit> unit) {
		this.unit = unit;
	}

	public List<Unit> getUnit() {
		return unit;
	}

}
