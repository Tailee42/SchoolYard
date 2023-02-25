package fr.isika.cda.entities.common;

import java.util.HashMap;
import java.util.Map;

public enum SchoolTypeEnum {
	ELEMENTAIRE("Ecole élémentaire"), COLLEGE("Collège"), LYCEE("Lycée");

	private final String displayValue;

	private SchoolTypeEnum(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public static Map<String, SchoolTypeEnum> levels = new HashMap<>();

	static {
		for (SchoolTypeEnum st : values()) {
			levels.put(st.displayValue, st);
		}
	}
}
