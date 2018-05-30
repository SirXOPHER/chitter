import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Auxiliary class for timestamp -> (passed time) conversion
 *
 * Created by xopher on 26/05/2018.
 */
public final class TimestampConverter {

    private TimestampConverter() {
        // just an auxiliary class
    }

    public static String convertTimestamp(LocalDateTime timestamp, Clock passedClock) {

        LocalDateTime current = LocalDateTime.now(passedClock);

        long durationMilliseconds = timestamp.until(current, ChronoUnit.MILLIS);
        long durationSeconds = durationMilliseconds / 1000;
        long durationMinutes = durationMilliseconds / (1000 * 60);
        long durationHours = durationMilliseconds / (1000 * 60 * 60);
        long durationDays = durationMilliseconds / (1000 * 60 * 60 * 24);

        if (durationSeconds < 1) {
            return "less than a second ago";
        } else if (durationSeconds == 1) {
            return "1 second ago";
        } else if (durationMinutes < 1) {
            return durationSeconds + " seconds ago";
        } else if (durationMinutes == 1) {
            return "1 minute ago";
        } else if (durationHours < 1) {
            return durationMinutes + " minutes ago";
        } else if (durationHours == 1) {
            return "1 hour ago";
        } else if (durationDays < 1) {
            return durationHours + " hours ago";
        } else return "posted on " + formatTimestamp(timestamp);
    }

    public static String formatTimestamp (LocalDateTime timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd. MMMM yyyy' at 'hh.mm a");
        String customTimestamp = timestamp.format(formatter);
        return customTimestamp;
    }
}
