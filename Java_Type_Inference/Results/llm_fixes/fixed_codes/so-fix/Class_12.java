import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public final class Class_12 {
    public static String fromCalendar(final Calendar calendar) {
        Date date = calendar.getTime();
        String formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(date);
        return formatted.substring(0, 22) + ":" + formatted.substring(22);
    }

    public static String now() {
        return fromCalendar(GregorianCalendar.getInstance());
    }

    public static Calendar toCalendar(final String iso8601string) throws ParseException {
        Calendar calendar = GregorianCalendar.getInstance();
        String s = iso8601string.replace("Z", "+00:00");
        try {
            s = s.substring(0, 22) + s.substring(23);
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException("Invalid length", 0);
        }
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(s);
        calendar.setTime(date);
        return calendar;
    }

    public static void main(String[] args) {
        System.out.println(now());
    }
}

class ISO8601DateFormatter {
    private final DateFormat DATE_FORMAT_1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    private final DateFormat DATE_FORMAT_2 = new SimpleDateFormat("yyyy-MM-dd'T'HHmmssZ");
    private static final String UTC_PLUS = "+";
    private static final String UTC_MINUS = "-";

    public Date toDate(String iso8601string) throws ParseException {
        iso8601string = iso8601string.trim();
        if (iso8601string.toUpperCase().indexOf("Z") > 0) {
            iso8601string = iso8601string.toUpperCase().replace("Z", "+0000");
        } else if (((iso8601string.indexOf(UTC_PLUS)) > 0)) {
            iso8601string = replaceColon(iso8601string, iso8601string.indexOf(UTC_PLUS));
            iso8601string = appendZeros(iso8601string, iso8601string.indexOf(UTC_PLUS), UTC_PLUS);
        } else if (((iso8601string.indexOf(UTC_MINUS)) > 0)) {
            iso8601string = replaceColon(iso8601string, iso8601string.indexOf(UTC_MINUS));
            iso8601string = appendZeros(iso8601string, iso8601string.indexOf(UTC_MINUS), UTC_MINUS);
        }

        Date date = null;
        if (iso8601string.contains(":"))
            date = DATE_FORMAT_1.parse(iso8601string);
        else {
            date = DATE_FORMAT_2.parse(iso8601string);
        }
        return date;
    }

    public String toISO8601String(Date date) {
        return DATE_FORMAT_1.format(date);
    }

    private String replaceColon(String sourceStr, int offsetIndex) {
        if (sourceStr.substring(offsetIndex).contains(":"))
            return sourceStr.substring(0, offsetIndex) + sourceStr.substring(offsetIndex).replace(":", "");
        return sourceStr;
    }

    private String appendZeros(String sourceStr, int offsetIndex, String offsetChar) {
        if ((sourceStr.length() - 1) - sourceStr.indexOf(offsetChar, offsetIndex) <= 2)
            return sourceStr + "00";
        return sourceStr;
    }
}