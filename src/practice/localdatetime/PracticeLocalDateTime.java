package practice.localdatetime;

import java.time.LocalDateTime;

public class PracticeLocalDateTime {

    public static void draw(LocalDateTime date){
        long millisecond = date.getSecond() * 1000;
        System.out.printf("%d:%s:%d##%d:%d:%d:%d",
                date.getYear(),
                date.getMonth(),
                date.getDayOfMonth(),
                date.getHour(),
                date.getMinute(),
                date.getSecond(),
                millisecond
        );
    }
}
