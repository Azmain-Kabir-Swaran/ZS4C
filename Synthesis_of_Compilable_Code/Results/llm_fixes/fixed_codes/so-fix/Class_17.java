import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class Class_17 {

    public static void main(String arg[]) {

        try {

            SimpleDateFormat sourceDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date date = sourceDateFormat.parse("2011-01-18 00:00:00.0");

            SimpleDateFormat targetDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(targetDateFormat.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        String date = "2011-01-18 00:00:00.0";
        TemporalAccessor temporal = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss.S")
                .parse(date); // use parse(date, LocalDateTime::from) to get LocalDateTime
        String output = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(temporal);
    }
}