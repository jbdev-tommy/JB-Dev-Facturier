package fr.jbdev.facturier.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilitaire {

    public static Date currentDateSql() {

	final java.util.Calendar cal = java.util.Calendar.getInstance();
	final java.util.Date utilDate = cal.getTime();
	// java.sql.Date sqlDate = new Date(utilDate.getTime());
	return utilDate;
    }

    public Date castStringToDate(final String date) throws ParseException {
	final SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
	return df.parse(date);
    }
}
