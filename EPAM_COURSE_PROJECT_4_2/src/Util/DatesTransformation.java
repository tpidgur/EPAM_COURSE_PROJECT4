package Util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Pidhurska Tetiana
 * @version 1 (created on 24.06.2016)
 */
public class DatesTransformation {
    /**
     * converts from LocalDate to java.sql.Date
     *
     * @param local -LocalDate instance
     * @return java.sql.Date
     */
    public static java.sql.Date convertLocalDateToSQL(LocalDate local) {
        Date date = null;
        try {
            date = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(local.toString()).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * converts from java.sql.Date  to LocalDate
     *
     * @param sqlDate java.sql.Date instance
     * @return LocalDate
     */
    public static LocalDate convertSQLToLocalDate(java.sql.Date sqlDate) {
        String date = sqlDate.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }
}
