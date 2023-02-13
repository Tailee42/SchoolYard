package fr.isika.cda.presentation.beans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class WelcomeBean {

	public String welcome() {
		return "Welcome to SchoolYard !";
	}
	
}
