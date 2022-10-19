package date.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class DateTimeEx {

    public static void main(String[] args) {
        // Local date
        LocalDate today = LocalDate.now();
        System.out.println(today);
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        System.out.println(tomorrow);
        today.plusDays(1);
        today.plusMonths(2);
        today.plusWeeks(3);
        today.plusYears(4);
        today.minus(2, ChronoUnit.DAYS);
        // Day of week
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        System.out.println(dayOfWeek);
        // Day of month
        int dayOfMonth = today.getDayOfMonth();
        System.out.println(dayOfMonth);
        // isBefore
        boolean isBefore = today.isBefore(tomorrow);
        System.out.println(isBefore);
        // isAfter
        boolean isAfter = today.isAfter(today);
        System.out.println(isAfter);
        
        // Local time
        LocalTime time = LocalTime.now();
        System.out.println(time);
        time.plus(10, ChronoUnit.HOURS);
        time.plusHours(10);
        time.minus(20, ChronoUnit.HOURS);
        time.minusHours(20);
        System.out.println(time);
        
        // Local date time
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime.of(2022, 05, 04, 14, 9, 50);
        dateTime = LocalDateTime.parse("2022-05-04T14:09:50");
        System.out.println(dateTime);
    }

}
