package fr.isika.cda.beans;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import fr.isika.cda.entities.users.User;
import fr.isika.cda.repositories.UserRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
@SessionScoped
public class LoginBean {

	private User user = new User();

	@Inject
	private UserRepository userRepository;

	/*
	 * API
	 */

	public String login() {
		return validationLogin();
	}

	public String logout() {
		SessionUtils.resetSession();
		return "index?faces-redirect=true";
	}

	/*
	 * private methods
	 */

	private String validationLogin() {
		Optional<User> userByLogin = userRepository.getUserByLogin(user.getLogin());
		if (userByLogin.isPresent() && validatePasswords(userByLogin)) {
			userRepository.updateLastConnection(userByLogin.get(), LocalDateTime.now());
			SessionUtils.setConnectedUser(userByLogin.get());
			return "userDashboard?faces-redirect=true";
		}
		return "login";
	}

	private boolean validatePasswords(Optional<User> userByLogin) {
		if (userByLogin.isPresent()) {
			return userByLogin.get().getSecurity().getPassword().equals(user.getSecurity().getPassword());
		} else {
			return false;
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
