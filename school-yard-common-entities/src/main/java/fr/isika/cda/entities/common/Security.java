package fr.isika.cda.entities.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Security {
	
	@Id
	@GeneratedValue
	private Long id;
	private String password;
	
	public Long getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
