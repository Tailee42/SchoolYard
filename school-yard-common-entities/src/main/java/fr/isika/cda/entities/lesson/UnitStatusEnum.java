package fr.isika.cda.entities.lesson;

public enum UnitStatusEnum {
TOVALIDATE("A valider"),
VALIDATED("Validé"),
REJECTED("Refusé");
	
	private final String displayValue;
	
	private UnitStatusEnum(String displayValue) {
		this.displayValue = displayValue;
	}
	
	public String getDisplayValue() {
		return displayValue;
	}
}
