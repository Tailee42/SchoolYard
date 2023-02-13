package fr.isika.cda.beans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class WelcomeBean {

	public String welcome() {
		return "Welcome to SchoolYard !";
	}
	
}
