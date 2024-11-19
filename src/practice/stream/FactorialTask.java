package practice.stream;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {
    private final int number;

    public FactorialTask(int number) {
        this.number = number;
    }

    @Override
    protected Long compute() {
        if (number <= 1) {
            return 1L;
        } else {
            FactorialTask subTask = new FactorialTask(number - 1);
            subTask.fork();
            return number * subTask.join();
        }
    }
}
