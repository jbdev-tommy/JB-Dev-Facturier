package fr.jbdev.facturier.utils;

import java.text.DecimalFormat;

public class CalclUtil {

    public static double calcTtc(final double prixHt, final double tauxTva) {
	return prixHt * ((tauxTva / 100) + 1);
    }

    public static String DoFormat(double myNumber) {

	return new DecimalFormat("0.00##").format(myNumber);
    }
}
