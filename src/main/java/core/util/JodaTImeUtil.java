package core.util;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class JodaTImeUtil {

    public String customFormat = "yyyy-MM-dd'T'HH:mm:ss";;

    public String getDifferenceJoda(DateTime then) {
        int time;
        String message;
        DateTime now = DateTime.now();
        time = Minutes.minutesBetween(then, now).getMinutes();
        message = " minutes ago";
        if (time >= 60) {
            time = Hours.hoursBetween(then, now).getHours();
            message = " hours ago";
            if (time >= 24) {
                time = Days.daysBetween(then, now).getDays();
                int maximumValue = now.dayOfMonth().getMaximumValue();
                message = " days ago";
                if (time >= maximumValue) {
                    time = Months.monthsBetween(then, now).getMonths();
                    message = " months ago";
                    if (time >= 12) {
                        time = Years.yearsBetween(then, now).getYears();
                        message = " years ago";
                    }
                }
            }
        }
        return time + message;
    }

    public String parseJodaTime(String dateTime) {
        // DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss");

        DateTimeFormatter dtf = ISODateTimeFormat.dateTime();
        LocalDateTime parsedDate = dtf.parseLocalDateTime(dateTime);

        String timer = parsedDate.toString(DateTimeFormat.forPattern(customFormat));
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
        DateTime then = dateTimeFormatter.parseDateTime(timer);
        return getDifferenceJoda(then);
        // return jodatime.minusMinutes(30);
    }

    public void setCustomFormat(String customFormat) {
        this.customFormat = customFormat;
    }

    // compile group: 'joda-time', name: 'joda-time', version: '2.9.9'

    public static void main(String[] args) {
        String dateTime = "2017-08-14T02:25:41.140Z";
        JodaTImeUtil jodaTImeUtil = new JodaTImeUtil();
        String difference = jodaTImeUtil.parseJodaTime(dateTime);
        System.out.println(difference);
    }
}