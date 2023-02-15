package fr.isika.cda.entities.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import fr.isika.cda.utils.EncodingUtil;

import java.io.Serializable;

@Entity
public class Security implements Serializable {

	private static final long serialVersionUID = 1648911084882771323L;

	@Id
	@GeneratedValue
	private Long id;
	private String password;

	public Security() {
	}

	public Security(String password) {
		this.password = password;
	}

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
