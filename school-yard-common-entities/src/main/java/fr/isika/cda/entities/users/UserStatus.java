package fr.isika.cda.entities.users;

import java.util.HashMap;
import java.util.Map;

public enum UserStatus {

	ACTIVE("Actif"), INACTIVE("Inactif");

	private final String displayValue;

	private UserStatus(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public static Map<String, UserStatus> userStatus = new HashMap<>();

	static {
		for (UserStatus u : values()) {
			userStatus.put(u.displayValue, u);
		}
	}
}