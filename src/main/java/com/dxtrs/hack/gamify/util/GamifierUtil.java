package com.dxtrs.hack.gamify.util;


import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class GamifierUtil {
    public static String DEFAULT_DATE_FORMAT = "dd-mm-yyyy";

    public static Date convertDateFromString(String inputDateString) throws ParseException {
        DateFormat sf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return new Date(sf.parse(inputDateString).getTime());
    }

    public static Date getCurrentDate() {
        Calendar c = new GregorianCalendar();
        return new Date(c.getTimeInMillis());
    }
}
