package fr.isika.cda.entities;

import java.util.List;

import javax.persistence.*;

@Entity
public class Teacher {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	private Member member;

	@Enumerated(EnumType.STRING)
	private List<LevelEnum> levels;

	@Enumerated(EnumType.STRING)
	private TeacherStatusEnum status;

	@OneToMany
	private Activity activity;

	@OneToMany
	private Unit unit;

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<LevelEnum> getLevels() {
		return levels;
	}

	public void setLevels(List<LevelEnum> levels) {
		this.levels = levels;
	}

	public TeacherStatusEnum getStatus() {
		return status;
	}

	public void setStatus(TeacherStatusEnum status) {
		this.status = status;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Long getId() {
		return id;
	}

}
