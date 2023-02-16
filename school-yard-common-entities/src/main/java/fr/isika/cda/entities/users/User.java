package fr.isika.cda.entities.users;

import fr.isika.cda.entities.common.RoleTypeEnum;
import fr.isika.cda.entities.common.Security;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4615310978061407318L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true, length = 100)
	private String login;

	private LocalDateTime lastConnection;

	@Enumerated(EnumType.STRING)
	private RoleTypeEnum role;

	@OneToOne(cascade = CascadeType.ALL)
	private Security security;

	@OneToOne(cascade = CascadeType.ALL)
	private Profil profil;

	public User() {
		this.security = new Security();
		this.profil = new Profil();

	}

	public User(String login, LocalDateTime lastConnection, RoleTypeEnum role, Security security, Profil profil) {
		this.login = login;
		this.lastConnection = lastConnection;
		this.role = role;
		this.security = security;
		this.profil = profil;
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

	public LocalDateTime getLastConnection() {
		return lastConnection;
	}

	public void setLastConnection(LocalDateTime localDateTime) {
		this.lastConnection = localDateTime;
	}

	public RoleTypeEnum getRole() {
		return role;
	}

	public void setRole(RoleTypeEnum role) {
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
