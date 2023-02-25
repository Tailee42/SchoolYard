package fr.isika.cda.entities.common;

import java.util.HashMap;
import java.util.Map;

public enum SubjectEnum {
	MATHS("Mathématiques"), FRANCAIS("Français"), HISTOIRE("Histoire"), PHILOSOPHIE("Philosophie"),
	PHYSIQUE("Physique"), CHIMIE("Chimie"), LITTERATURE("Littérature"), BIOLOGIE("Biologie");

	private final String displayValue;

	private SubjectEnum(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public static Map<String, SubjectEnum> subjects = new HashMap<>();

	static {
		for (SubjectEnum s : values()) {
			subjects.put(s.displayValue, s);
		}
	}
}
