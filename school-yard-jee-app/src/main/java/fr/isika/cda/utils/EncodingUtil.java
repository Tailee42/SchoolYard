package fr.isika.cda.utils;

import java.util.Base64;

public class EncodingUtil {

	public static String encode(String pwd) {

		return Base64.getEncoder().encodeToString(pwd.getBytes());

	}

	public static String decode(String encoded) {
		byte[] decodedPwd = Base64.getDecoder().decode(encoded);
		return new String(decodedPwd);
	}

}
