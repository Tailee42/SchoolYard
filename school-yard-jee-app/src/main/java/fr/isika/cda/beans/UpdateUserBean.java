package fr.isika.cda.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import fr.isika.cda.entities.users.User;
import fr.isika.cda.repositories.UserRepository;
import fr.isika.cda.utils.SessionUtils;


@ManagedBean
public class UpdateUserBean {

	
	private User user = new User();
	
	@Inject
	private UserRepository userRepository;

	@PostConstruct
	private void init() {
		getCurrentUser();
	}
	
	public String updateUserProfile() {
		userRepository.update(user);
		user = new User();
		return "userDashboard";		
	}
	
	private void getCurrentUser( ) {
		user = SessionUtils.getConnectedUser();
	}
	

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
