package fr.isika.cda.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import fr.isika.cda.utils.EncodingUtil;

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
		this.password = EncodingUtil.encode(password);
	}

}
