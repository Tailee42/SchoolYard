package fr.isika.cda.beans;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import fr.isika.cda.entities.users.User;
import fr.isika.cda.entities.users.UserStatus;
import fr.isika.cda.exceptions.UserNotFoundException;
import fr.isika.cda.repositories.UserRepository;
import fr.isika.cda.utils.SessionUtils;

@ManagedBean
@SessionScoped
public class LoginBean {

	private User user = new User();
	
	private boolean correctPassword = true;
	
	private boolean userActive = true;

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
		try {
			
			Optional<User> userByLogin = userRepository.getUserByLogin(user.getLogin());
			correctPassword = validatePasswords(userByLogin);
			userActive = isUserActive(userByLogin);
			
			if (userByLogin.isPresent() && correctPassword && userActive) {
				
				userRepository.updateLastConnection(userByLogin.get(), LocalDateTime.now());
				SessionUtils.setConnectedUser(userByLogin.get());
				
				resetData();
				
				return "userDashboard?faces-redirect=true";
			}
		} catch (UserNotFoundException e) {
			
			correctPassword = false;
			
//			UIComponent component = FacesContext.getCurrentInstance().getViewRoot().findComponent("loginId");
//			FacesContext.getCurrentInstance().addMessage(component.getClientId(),
//					new FacesMessage("Identifiant et/ou pwd incorrect"));
			
			
			return "login?faces-redirect=true";
		}
		return "login?faces-redirect=true";
	}

	private void resetData() {
		user = new User();
		correctPassword = true;
		userActive = true;
	}

	private boolean validatePasswords(Optional<User> userByLogin) {	
		return userByLogin.isPresent() 
				&& userByLogin.get().getSecurity().getPassword().equals(user.getSecurity().getPassword());
	}
	
	private boolean isUserActive(Optional<User> userByLogin) {
		return userByLogin.isPresent() && userByLogin.get().getStatus().equals(UserStatus.ACTIVE);
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean getCorrectPassword() {
		return correctPassword;
	}

	public void setCorrectPassword(boolean correctPassword) {
		this.correctPassword = correctPassword;
	}

	public boolean isUserActive() {
		return userActive;
	}

	public void setUserActive(boolean userActive) {
		this.userActive = userActive;
	}


	
}
