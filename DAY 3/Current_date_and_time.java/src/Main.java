import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();         // Current date
        LocalTime time = LocalTime.now();         // Current time
        LocalDateTime dateTime = LocalDateTime.now(); // Current date and time

        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println("DateTime: " + dateTime);
    }
}