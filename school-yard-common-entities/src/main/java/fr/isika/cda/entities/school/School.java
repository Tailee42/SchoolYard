package fr.isika.cda.entities.school;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class School {

	@Id
	@GeneratedValue
	private Long id;

	private String schoolName;
	private String logo;
	private String presentation;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<Member> members = new ArrayList<>();
	
//	Question pour Mohamed:
//  private List<Member> members;
//	members = new ArrayList<>();
	
	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	public Long getId() {
		return id;
	}
	
	public List<Member> getMembers() {
		return Collections.unmodifiableList(members);
	}
	
	public void addMember(Member member) {
		this.members.add(member);
	}

}
