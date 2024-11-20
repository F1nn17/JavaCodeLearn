package practice.concurrency;

import java.util.concurrent.*;

public class ComplexTaskExecutor {
    private final int numberOfTasks;

    public ComplexTaskExecutor(int numberOfTasks) {
        this.numberOfTasks = numberOfTasks;
    }

    public void executeTasks(int numberOfTasks) {
        ExecutorService executor = Executors.newFixedThreadPool(numberOfTasks);
        CyclicBarrier barrier = new CyclicBarrier(numberOfTasks, () -> {
            System.out.println("All tasks have completed. Proceeding to the next step.");
        });
        for (int i = 0; i < numberOfTasks; i++) {
            executor.submit(new ComplexTask(barrier));
        }
        executor.shutdown();
    }
}

