package fr.isika.cda.entities.school;

public enum FontEnum {
	
	PLAYFAIRDISPLAY("Playfair Display"),
	QUICKSAND("Quicksand"),
	ROBOTO("Roboto"),
	UBUNTU("Ubuntu"),
	ZEYADA("Zeyada");

	private final String displayValue;

	private FontEnum(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}


}
