package practice.concurrency;

import java.util.Queue;
import java.util.ArrayDeque;

public class BlockingQueue<T> {
    private final int capacity;
    private final Queue<T> queue = new ArrayDeque<>();

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }


    public synchronized void enqueue(T item) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }
        queue.add(item);
        notify();
    }

    public synchronized T dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        notify();
        return queue.poll();
    }

    public synchronized int size() {
        return queue.size();
    }


}