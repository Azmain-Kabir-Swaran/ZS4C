import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class JodaTime49 {

    public static void main(String[] args) {
        DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("yyyy-MMM-dd");
        DateTime dateTime = FORMATTER.parseDateTime("2005-nov-12");
        LocalDate localDate = dateTime.toLocalDate();
    }

}