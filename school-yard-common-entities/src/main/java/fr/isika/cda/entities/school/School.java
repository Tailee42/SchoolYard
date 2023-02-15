package fr.isika.cda.entities.school;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import fr.isika.cda.entities.common.Address;
import fr.isika.cda.entities.common.Contact;

@Entity
public class School {

	@Id
	@GeneratedValue
	private Long id;

	private String schoolName;
	private String logo;
	private String presentation;

	@ManyToMany(cascade = CascadeType.REMOVE )
	private List<Member> members = new ArrayList<>();
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private Contact contact;
	
	
	public School() {
		this.contact = new Contact();
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

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	
}
