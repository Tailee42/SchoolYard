package fr.isika.cda.entities.teacher;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import fr.isika.cda.entities.LevelEnum;
import fr.isika.cda.entities.SubjectEnum;
import fr.isika.cda.entities.lesson.Activity;
import fr.isika.cda.entities.lesson.Unit;
import fr.isika.cda.entities.school.Member;

@Entity
public class Teacher {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	private Member member;

	@Enumerated(EnumType.STRING)
	private LevelEnum level;

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

	public void setMember(Member member) {
		this.member = member;
	}

	public Member getMember() {
		return member;
	}

	public LevelEnum getLevel() {
		return level;
	}

	public void setLevel(LevelEnum level) {
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

	public Long getId() {
		return id;
	}

}
