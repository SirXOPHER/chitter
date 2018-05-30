import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static org.junit.Assert.*;

/**
 * Created by xopher on 27/05/2018.
 */
public class TimestampConverterTest {

    private final Clock fixedClock = Clock.fixed(Instant.parse("2018-05-25T10:10:10.00Z"), ZoneId.systemDefault());
    private Instant instant;
    private LocalDateTime ldt;

    @Before
    public void setUp() throws Exception {
        instant = fixedClock.instant();
        // Conversion of Instant to LocalDateTime (no timezone, add default offset)
        ldt = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault());
    }

    @After
    public void tearDown() throws Exception {
        instant = null;
        ldt = null;
        assertNull(instant);
        assertNull(ldt);
    }

    @Test
    public void convertTimestamp_LessThanASecondAgo_HumanReadableOutput() throws Exception {
        // Reset passed timestamp for unit test
        LocalDateTime test_less = ldt.minusNanos(1000000000/2); // 1s = 1000000000ns
        // Printout of time representations (1h difference, when in London!)
        System.out.println("Instant       : " + instant);
        System.out.println("LDT - 0.5s    : " + test_less + "\n");
        // Test series - 1/8
        assertEquals(TimestampConverter.convertTimestamp(test_less, fixedClock), "less than a second ago");
    }

    @Test
    public void convertTimestamp_ASecondAgo_HumanReadableOutput() throws Exception {
        // Reset passed timestamp for unit test
        LocalDateTime test_1s = ldt.minusSeconds(1);
        // Printout of time representations (1h difference, when in London!)
        System.out.println("Instant       : " + instant);
        System.out.println("LDT - 1s      : " + test_1s + "\n");
        // Test series - 2/8
        assertEquals(TimestampConverter.convertTimestamp(test_1s, fixedClock), "1 second ago");
    }

    @Test
    public void convertTimestamp_10SecondsAgo_HumanReadableOutput() throws Exception {
        // Reset passed timestamp for unit test
        LocalDateTime test_10s = ldt.minusSeconds(10);
        // Printout of time representations (1h difference, when in London!)
        System.out.println("Instant       : " + instant);
        System.out.println("LDT - 10s     : " + test_10s + "\n");
        // Test series - 3/8
        assertEquals(TimestampConverter.convertTimestamp(test_10s, fixedClock), "10 seconds ago");
    }

    @Test
    public void convertTimestamp_1MinuteAgo_HumanReadableOutput() throws Exception {
        // Reset passed timestamp for unit test
        LocalDateTime test_1min = ldt.minusMinutes(1);
        // Printout of time representations (1h difference, when in London!)
        System.out.println("Instant       : " + instant);
        System.out.println("LDT - 1min    : " + test_1min + "\n");
        // Test series - 4/8
        assertEquals(TimestampConverter.convertTimestamp(test_1min, fixedClock), "1 minute ago");
    }

    @Test
    public void convertTimestamp_10MinutesAgo_HumanReadableOutput() throws Exception {
        // Reset passed timestamp for unit test
        LocalDateTime test_10min = ldt.minusMinutes(10);
        // Printout of time representations (1h difference, when in London!)
        System.out.println("Instant       : " + instant);
        System.out.println("LDT - 10min   : " + test_10min + "\n");
        // Test series - 5/8
        assertEquals(TimestampConverter.convertTimestamp(test_10min, fixedClock), "10 minutes ago");
    }

    @Test
    public void convertTimestamp_1HourAgo_HumanReadableOutput() throws Exception {
        // Reset passed timestamp for unit test
        LocalDateTime test_1h = ldt.minusHours(1);
        // Printout of time representations (1h difference, when in London!)
        System.out.println("Instant       : " + instant);
        System.out.println("LDT - 1h      : " + test_1h + "\n");
        // Test series - 6/8
        assertEquals(TimestampConverter.convertTimestamp(test_1h, fixedClock), "1 hour ago");
    }

    @Test
    public void convertTimestamp_10HoursAgo_HumanReadableOutput() throws Exception {
        // Reset passed timestamp for unit test
        LocalDateTime test_10h = ldt.minusHours(10);
        // Printout of time representations (1h difference, when in London!)
        System.out.println("Instant       : " + instant);
        System.out.println("LDT - 10h     : " + test_10h + "\n");
        // Test series - 7/8
        assertEquals(TimestampConverter.convertTimestamp(test_10h, fixedClock), "10 hours ago");
    }

    @Test
    public void convertTimestamp_ALongTimeAgo_HumanReadableOutput() throws Exception {
        // Reset passed timestamp for unit test
        LocalDateTime test_5d = ldt.minusDays(5);
        // Printout of time representations (1h difference, when in London!)
        System.out.println("Instant       : " + instant);
        System.out.println("LDT - 5days   : " + test_5d + "\n");
        // Test series - 8/8
        assertEquals(TimestampConverter.convertTimestamp(test_5d, fixedClock), "posted on Sunday, 20. May 2018 at 11.10 AM");
    }

    @Test
    public void formatTimestamp_CustomTimestampRepreseantation_HumanReadableOutput() throws Exception {
        assertEquals(TimestampConverter.formatTimestamp(ldt), "Friday, 25. May 2018 at 11.10 AM");
    }

}