package fr.isika.cda.entities.users;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import fr.isika.cda.entities.common.RoleType;
import fr.isika.cda.entities.common.Security;

@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4615310978061407318L;

	@Id
	@GeneratedValue
	private Long id;

	private String login;
	private Date lastConnection;

	@Enumerated(EnumType.STRING)
	private RoleType role;

	@OneToOne(cascade = CascadeType.ALL)
	private Security security;

	@OneToOne(cascade = CascadeType.ALL)
	private Profil profil;

	public User() {
		this.security = new Security();
		this.profil = new Profil();

	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Date getLastConnection() {
		return lastConnection;
	}

	public void setLastConnection(Date lastConnection) {
		this.lastConnection = lastConnection;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}

}
