package fr.isika.cda.utils;

public final class StringHelper {

	private StringHelper() {
	}
	
	public static boolean isNullOrEmpty(final String value) {
    	return value != null && !value.isBlank();
    }
}
