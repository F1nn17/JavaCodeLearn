package practice.stream;

import java.util.Map;

public class Student {
    private final String name;
    private final Map<String, Integer> subjects;

    public Student(String name, Map<String, Integer> subjects) {
        this.name = name;
        this.subjects = subjects;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getSubjects() {
        return subjects;
    }
}
