package fr.jbdev.facturier.utils;

public class CryptUtil {

    public static String encrypte(final String value) {
	String crypte = "";
	for (int i = 0; i < value.length(); i++) {
	    final int c = value.charAt(i) ^ 48;
	    crypte = crypte + (char) c;
	}
	System.out.println(" Crypte :" + crypte);
	return crypte;
    }

    public static String decrypt(final String value) {
	String aCrypter = "";
	for (int i = 0; i < value.length(); i++) {
	    final int c = value.charAt(i) ^ 48;
	    aCrypter = aCrypter + (char) c;
	}
	System.out.println(" Crypte :" + aCrypter);
	return aCrypter;
    }
}
