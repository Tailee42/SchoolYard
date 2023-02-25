package fr.isika.cda.entities.school;

import java.util.HashMap;
import java.util.Map;

public enum FontEnum {

	PLAYFAIRDISPLAY("Playfair Display"), QUICKSAND("Quicksand"), ROBOTO("Roboto"), UBUNTU("Ubuntu"), ZEYADA("Zeyada");

	private final String displayValue;

	private FontEnum(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public static Map<String, FontEnum> fonts = new HashMap<>();

	static {
		for (FontEnum f : values()) {
			fonts.put(f.displayValue, f);
		}
	}

}
