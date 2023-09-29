import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.Date;

public class JodaTime35 {

    private final static PeriodFormatter DURATION_FORMATTER = new PeriodFormatterBuilder().appendYears()
            .appendSuffix("year", "years").appendSeparator(" ").appendMonths().appendSuffix("month", "months")
            .appendSeparator(" ").appendDays().appendSuffix("day", "days").appendSeparator(" ").appendHours()
            .appendSuffix("hour", "hours").appendSeparator(" ").appendMinutes().appendSuffix("minute", "minutes")
            .appendSeparator(" ").appendSeconds().appendSuffix("second", "seconds").toFormatter();

    public static String format(Date start) {
        StringBuffer result = new StringBuffer();
        DURATION_FORMATTER.printTo(result, new Period(new DateTime(start), new DateTime()));
        return result.toString();
    }

    public static String format(Date start, Date end) {
        StringBuffer result = new StringBuffer();
        DURATION_FORMATTER.printTo(result,
                new Period(new DateTime(start), end == null ? new DateTime() : new DateTime(end)));
        return result.toString();
    }

}