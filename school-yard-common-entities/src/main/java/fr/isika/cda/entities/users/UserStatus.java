package fr.isika.cda.entities.users;

public enum UserStatus {
	
	ACTIVE("Actif"),
	INACTIVE("Inactif");

	private final String displayValue;

	private UserStatus(String displayValue){
		this.displayValue = displayValue;
	}

	public String getDisplayValue(){
		return displayValue;
	}
}