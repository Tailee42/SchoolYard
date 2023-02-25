package fr.isika.cda.entities.common;

public enum SubjectEnum {
	MATHS("Mathématiques"),
	FRANCAIS("Français"),
	HISTOIRE("Histoire"),
	PHILOSOPHIE("Philosophie"),
	PHYSIQUE("Physique"),
	CHIMIE("Chimie"),
	LITTERATURE("Littérature"),
	BIOLOGIE("Biologie");

	private final String displayValue;

	private SubjectEnum(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}

}
