package jodatime;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

import org.joda.time.DateTimeZone;
import org.joda.time.tz.Provider;

public class JodaTime45 {

    public class FastDateTimeZoneProvider implements Provider {
        public final Set<String> AVAILABLE_IDS = new HashSet<String>();

        public DateTimeZone getZone(String id) {
            if (id == null) {
                return DateTimeZone.UTC;
            }

            TimeZone tz = TimeZone.getTimeZone(id);
            if (tz == null) {
                return DateTimeZone.UTC;
            }

            int rawOffset = tz.getRawOffset();

            if (tz.inDaylightTime(new Date())) {
                rawOffset += tz.getDSTSavings();
            }

            return DateTimeZone.forOffsetMillis(rawOffset);
        }

        public Set<String> getAvailableIDs() {
            return AVAILABLE_IDS;
        }
    }
}