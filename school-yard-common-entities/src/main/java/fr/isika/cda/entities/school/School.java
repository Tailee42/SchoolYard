package fr.isika.cda.entities.school;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.*;

import fr.isika.cda.entities.common.SchoolTypeEnum;
import fr.isika.cda.entities.common.Contact;

@Entity

public class School implements Serializable {

	private static final long serialVersionUID = 6759793081150651074L;

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

	@OneToOne(cascade = CascadeType.ALL)
	private Membership membership;
	
	@OneToOne(cascade = CascadeType.ALL)
	private SchoolPage schoolPage;

	public School() {
		this.contact = new Contact();
		this.schoolPage = new SchoolPage();
	}

	public School(String schoolName, String logo, String presentation, List<Member> members, Contact contact,
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

	public void setSchoolTypeEnum(SchoolTypeEnum schoolTypeEnum) {
		this.schoolTypeEnum = schoolTypeEnum;
	}

	public SchoolTypeEnum getSchoolTypeEnum() {
		return schoolTypeEnum;
	}

	public StatusSchool getStatusSchool() {
		return statusSchool;
	}

	public void setStatusSchool(StatusSchool statusSchool) {
		this.statusSchool = statusSchool;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	public Membership getMembership() {
		return membership;
	}


	public SchoolPage getSchoolPage() {
		return schoolPage;
	}


	public void setSchoolPage(SchoolPage schoolPage) {
		this.schoolPage = schoolPage;
	}
	

}
