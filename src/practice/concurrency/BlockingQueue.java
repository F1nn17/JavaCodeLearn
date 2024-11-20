package practice.concurrency;

public class BlockingQueue<T> {
    private final Object lock = new Object();
    private final int capacity;
    private final java.util.Queue<T> queue;
    private final Object notEmpty = new Object();
    private final Object notFull = new Object();

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new java.util.LinkedList<>();
    }

    public void enqueue(T item) throws InterruptedException {
        synchronized (notFull) {
            while (queue.size() >= capacity) {
                notFull.wait();
            }
            synchronized (lock) {
                queue.offer(item);
            }
            synchronized (notEmpty) {
                notEmpty.notify();
            }
        }
    }

    public T dequeue() throws InterruptedException {
        synchronized (notEmpty) {
            while (queue.isEmpty()) {
                notEmpty.wait();
            }
            T item;
            synchronized (lock) {
                item = queue.poll();
            }
            synchronized (notFull) {
                notFull.notify();
            }
            return item;
        }
    }

    public int size() {
        synchronized (lock) {
            return queue.size();
        }
    }
}