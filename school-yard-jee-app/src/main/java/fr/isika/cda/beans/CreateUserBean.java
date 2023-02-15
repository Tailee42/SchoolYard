package fr.isika.cda.beans;

import java.time.LocalDateTime;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.users.User;
import fr.isika.cda.repositories.UserRepository;

@ManagedBean
public class CreateUserBean {

	private User user = new User();

	@Inject
	private UserRepository userRepository;

	public String create() {
		user.setLastConnection(LocalDateTime.now());
		userRepository.save(user);
		user = new User();
		return "index?faces-redirect=true";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
