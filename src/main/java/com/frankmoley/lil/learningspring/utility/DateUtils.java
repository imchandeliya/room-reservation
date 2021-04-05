package com.frankmoley.lil.learningspring.utility;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate createDateFromDateString (String dateStr) {
        LocalDate date = null;


        if (!StringUtils.isEmpty(dateStr)) {
            try {
                DateTimeFormatter formatter = DATE_FORMAT.withLocale(Locale.getDefault());
                date = LocalDate.parse(dateStr, formatter);
            } catch (Exception e) {
                date = LocalDate.now();
            }
        } else {
            date = LocalDate.now();
        }

        return date;
    }

}
