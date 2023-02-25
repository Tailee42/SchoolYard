package fr.isika.cda.entities.users;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import fr.isika.cda.utils.EncodingUtil;

@Entity
public class SuperAdmin {

	@Id
	@GeneratedValue
	private Long id;

	private String login;

	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Profil profil;

	public SuperAdmin() {
		this.profil = new Profil();
	}

	public SuperAdmin(String login, String password, Profil profil) {
		this.login = login;
		this.password = EncodingUtil.encode(password);
		this.profil = profil;

	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setPassword(String password) {
		this.password = EncodingUtil.encode(password);
	}

	public String getPassword() {
		return password;
	}

}
