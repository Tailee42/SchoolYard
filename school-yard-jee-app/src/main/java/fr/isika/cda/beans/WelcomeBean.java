package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;

import fr.isika.cda.utils.SessionUtils;

@ManagedBean
public class WelcomeBean {

	public boolean isUserConnected() {
		return SessionUtils.isUserConnected();
	}
	
	public String welcome() {
		return "Welcome to SchoolYard !";
	}
	
	
	
}
