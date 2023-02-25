package fr.isika.cda.entities.lesson;

import java.util.HashMap;
import java.util.Map;

public enum UnitStatusEnum {
	TOVALIDATE("A valider"), VALIDATED("Validé"), REJECTED("Refusé");

	private final String displayValue;

	private UnitStatusEnum(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public static Map<String, UnitStatusEnum> unitStatus = new HashMap<>();

	static {
		for (UnitStatusEnum u : values()) {
			unitStatus.put(u.displayValue, u);
		}
	}
}
