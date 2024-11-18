import practice.localdatetime.PracticeLocalDateTime;
import practice.stringbuilder.PracticeStringBuilder;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        PracticeStringBuilder builder = new PracticeStringBuilder();

        builder.append("test");
        builder.append("test1");

        System.out.println("До вызова undo()");
        System.out.println(builder);

        builder.undo();
        System.out.println("После вызова undo()");
        System.out.println(builder);

        System.out.println("Работа с LocalDateTime");
        LocalDateTime date = LocalDateTime.now();
        PracticeLocalDateTime.draw(date);
    }
}
