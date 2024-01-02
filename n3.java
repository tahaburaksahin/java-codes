//This Java program is a simple example that performs some time and date operations
// and measures the running time of an algorithm

//Taha Burak Sahin
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

public class n3 {
    public static void main (String[] args) {
        // measuring the running time of an algorithm
        Instant start = Instant.now();
        System.out.println("Fibo(42) = " + fibo(42));
        Instant end = Instant.now();
        Duration elapsed = Duration.between(start, end);
        long nanos   = elapsed.toNanos();
        long millis  = elapsed.toMillis();
        System.out.println("In nanos : " + nanos);
        System.out.println("In millis: " + millis);

        LocalDateTime today   = LocalDateTime.now();
        LocalDateTime marilyn = LocalDateTime.of(
                1926,Month.JUNE,1,13,0,0);
        System.out.print("\nMarilyn Monroe was born " +
                Duration.between(marilyn,today).toDays() +
                " days ago on " +
                marilyn.getDayOfWeek());


        System.out.println("\nElection days in the U.S.A");
        // first Tuesday after first Monday of November
        LocalDate date = null;
        for (int i = 2016; i <= 2040; i += 4) {
            date = LocalDate.of(i,11,1)
                    .with(TemporalAdjusters
                            .nextOrSame(DayOfWeek.MONDAY))
                    .plusDays(1);
            System.out.println("Year " + i + " " + date);
        }

        // formatting...
        for (FormatStyle style : FormatStyle.values()) {
            DateTimeFormatter fmt =
                    DateTimeFormatter
                            .ofLocalizedDate(style)
                            .withLocale(Locale.FRENCH);
            String s = fmt.format(date);
            System.out.printf("Format %6s: %s%n",
                    style, fmt.format(date));
        }
    }

    private static int fibo(int n) {
        if (n <= 1) return n;
        return fibo(n-1) + fibo(n-2);
    }
}

