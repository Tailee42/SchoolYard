package fr.isika.cda.entities.school;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import fr.isika.cda.entities.users.User;

@Entity 
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Member {

	@Id
	@GeneratedValue
	protected Long id;

	@ManyToOne
	protected User user;

	@OneToOne
	protected School school;

	public void member() {
		this.user = new User();
		this.school = new School();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Long getId() {
		return id;
	}
	
}
