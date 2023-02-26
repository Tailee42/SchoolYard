package fr.isika.cda.entities.school;

import java.util.HashMap;
import java.util.Map;

public enum StatusSchool {
	TOPUBLISH("A publier"), PUBLISHED("En ligne"), TOUPDATE("A mettre à jour"), EXPIRED("Abonnement échu"),
	TODELETE("A supprimer"), INACTIVE("Fermée");

	private final String displayValue;

	private StatusSchool(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public static Map<String, StatusSchool> statusSchool = new HashMap<>();

	static {
		for (StatusSchool s : values()) {
			statusSchool.put(s.displayValue, s);
		}
	}
}
