package fr.isika.cda.entities.common;

public enum SchoolTypeEnum {
	ELEMENTAIRE("Niveau élémentaire"),
	COLLEGE("Niveau collège"),
	LYCEE("Niveau lycée");

	private final String displayValue;

	private SchoolTypeEnum(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
}
