package fr.isika.cda.entities.school;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.*;

import fr.isika.cda.entities.SchoolTypeEnum;
import fr.isika.cda.entities.common.Contact;

@Entity
public class School  implements Serializable {

	private static final long serialVersionUID = -5433370329875227160L;
	@Id
	@GeneratedValue
	private Long id;

	private String schoolName;
	private String logo;
	private String synthesis;
	@Enumerated(EnumType.STRING)
	private SchoolTypeEnum schoolTypeEnum;

	@Enumerated(EnumType.STRING)
	private StatusSchool statusSchool;

	@ManyToMany(cascade = CascadeType.REMOVE)
	private List<Member> members = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL)
	private Contact contact;

	public School() {
		this.contact = new Contact();
	}

	public School(String schoolName,
				  String logo,
				  String presentation,
				  List<Member> members,
				  Contact contact,
				  SchoolTypeEnum schoolTypeEnum) {
		this.schoolName = schoolName;
		this.logo = logo;
		this.synthesis = presentation;
		this.members = members;
		this.contact = contact;
		this.statusSchool = StatusSchool.TOPUBLISH;
		this.schoolTypeEnum = schoolTypeEnum;
	}

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

	public String getSynthesis() {
		return synthesis;
	}

	public void setSynthesis(String synthesis) {
		this.synthesis = synthesis;
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

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public void setSchoolType(SchoolTypeEnum schoolTypeEnum) {
		this.schoolTypeEnum = schoolTypeEnum;
	}

	public SchoolTypeEnum getSchoolType() {
		return schoolTypeEnum;
	}

	public StatusSchool getStatusSchool() {
		return statusSchool;
	}

	public void setStatusSchool(StatusSchool statusSchool) {
		this.statusSchool = statusSchool;
	}

}
