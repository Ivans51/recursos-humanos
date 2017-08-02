package core.util;

import com.jfoenix.controls.JFXDatePicker;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by WAMS-10 on 30/07/2017.
 */
public class MananejadorDateUtil {

    private static Date date = new Date();
    private static Calendar cal = Calendar.getInstance();

    public static java.sql.Date getDatePickentCurrent(JFXDatePicker datePicker) {
        return java.sql.Date.valueOf(datePicker.getValue());
    }

    public static Date getCurrentDate() throws ParseException {
        Timestamp date = new Timestamp(new java.util.Date().getTime());
        return date;
    }

    public static String getFormatDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public static String getDateFormat() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(date);
    }

    public static Date getFormatCalendarTime(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return  cal.getTime();
    }

    public static Date getCalendarFormat() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return  cal.getTime();
    }

}
