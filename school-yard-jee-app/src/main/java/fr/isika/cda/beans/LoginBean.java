package fr.isika.cda.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.User;
import fr.isika.cda.repositories.UserRepository;

@ManagedBean
public class LoginBean {

	private User user = new User();
	public List<User> users = new ArrayList();

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Inject
	private UserRepository userRepository;

	public String login() {
		return "index?faces-redirect=true";
	}

	public void getUsersByLogin() {
		this.users = userRepository.getUserByLogin(user.getLogin());
	}

	public String validationLogin() {
		getUsersByLogin();
		for (User listedUser : users) {
			if (listedUser.getSecurity().getPassword().equals(user.getSecurity().getPassword())) {
				return "userDashboard";
			}
		}
		// user = new User();
		return "login";
	}

}
