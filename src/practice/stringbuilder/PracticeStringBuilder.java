package practice.stringbuilder;

import java.util.Arrays;
import java.util.Stack;

public final class PracticeStringBuilder implements java.io.Serializable {

    private char[] value;
    private int count;
    private final Stack<Memento> history;

    PracticeStringBuilder(){
        this.history = new Stack<>();
        this.value = new char[16];
        this.count = 0;
    }

    PracticeStringBuilder(int capacity){
        this.value = new char[capacity];
        this.history = new Stack<>();
        this.count = 0;
    }

    PracticeStringBuilder(String str){
        this.history = new Stack<>();
        append(str);
    }


    public PracticeStringBuilder append(String str){
        saveSnapshot();
        int len = str.length();
        ensureCapacity(count + len);
        str.getChars(0, len, value, count);
        count += len;
        return this;
    }

    private void ensureCapacity(int newCapacity) {
        if (newCapacity > value.length) {
            value = Arrays.copyOf(value, newCapacity);
        }
    }

    private void saveSnapshot() {
        Memento snapshot = new Memento(Arrays.copyOf(value, count), count);
        history.push(snapshot);
    }

    public PracticeStringBuilder undo(){
        if (!history.isEmpty()) {
            Memento memento = history.pop();
            this.value = memento.value();
            this.count = memento.count();
        }
        return this;
    }


    private record Memento(char[] value, int count) {
    }
}
