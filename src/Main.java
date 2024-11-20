import practice.concurrency.BankAccount;
import practice.concurrency.BlockingQueue;
import practice.concurrency.ComplexTaskExecutor;
import practice.concurrency.ConcurrentBank;

public class Main {
    public static void main(String[] args) {
//        System.out.println("\n");
//        System.out.println("Test Filter");
//        Random random = new Random();
//        Integer[] array = new Integer[random.nextInt(30 - 5) + 5];
//        for (int i = 0; i < array.length; i++) {
//            array[i] = random.nextInt(10 - 1) + 1;
//        }
//        Filter filter = new Filter() {
//            @Override
//            public Object apply(Object o) {
//                if (o instanceof Integer && o.equals(4)) {
//                    return o;
//                }
//                return null;
//            }
//        };
//        Integer[] filteredArray = PracticeCollection.filter(array, filter);
//        System.out.println("result filtered");
//        for (Integer i : filteredArray) {
//            System.out.print(i);
//        }
//        System.out.println("\nEnd task filter");
//        System.out.println("\n");
//        System.out.println("Test PracticeCollection");
//        System.out.println("result count of elements");
//        System.out.println(PracticeCollection.countElements(array));
//        System.out.println("End PracticeCollection");
//        System.out.println("\n");
//        System.out.println("Test Factorial task");
//        int number = random.nextInt(30 - 1) + 1;
//        ForkJoinPool pool = new ForkJoinPool();
//        FactorialTask task = new FactorialTask(number);
//        long result = pool.invoke(task);
//        System.out.println("Factorial number " + number + " := " + result);
//        System.out.println("End Factorial task");
//        System.out.println("\n");
//        System.out.println("Test Students");
//        List<Student> students = Arrays.asList(
//                new Student("Alice",
//                        Map.of("Math", random.nextInt(5 - 1) + 1,
//                                "English", random.nextInt(5 - 1) + 1,
//                                "History", random.nextInt(5 - 1) + 1)),
//                new Student("Bob",
//                        Map.of("Math", random.nextInt(5 - 1) + 1,
//                                "English", random.nextInt(5 - 1) + 1,
//                                "History", random.nextInt(5 - 1) + 1)),
//                new Student("Charlie",
//                        Map.of("Math", random.nextInt(5 - 1) + 1,
//                                "English", random.nextInt(5 - 1) + 1,
//                                "History", random.nextInt(5 - 1) + 1)),
//                new Student("David",
//                        Map.of("Math", random.nextInt(5 - 1) + 1,
//                                "English", random.nextInt(5 - 1) + 1,
//                                "History", random.nextInt(5 - 1) + 1))
//        );
//
//        Map<String, Double> averageGrades = students.parallelStream()
//                .flatMap(student -> student.getSubjects().entrySet().stream())
//                .collect(Collectors.groupingBy(
//                        Map.Entry::getKey,
//                        Collectors.averagingInt(Map.Entry::getValue)
//                ));
//
//        averageGrades.forEach((subject, avgGrade) ->
//                System.out.println("Предмет: " + subject + ", Средняя оценка: " + avgGrade)
//        );
//        System.out.println("End Students");
//        System.out.println("\n");
//        System.out.println("Test Orders");
//        List<Order> orders = Arrays.asList(
//                new Order("Laptop", random.nextInt(5000-100)+100),
//                new Order("Smartphone", random.nextInt(5000-100)+100),
//                new Order("Laptop", random.nextInt(5000-100)+100),
//                new Order("Smartwatch", random.nextInt(5000-100)+100),
//                new Order("Smartphone", random.nextInt(5000-100)+100),
//                new Order("Tablet", random.nextInt(5000-100)+100),
//                new Order("Tablet", random.nextInt(5000-100)+100),
//                new Order("Laptop", random.nextInt(5000-100)+100)
//        );
//
//        Map<String, Integer> totalSalesByProduct = orders.stream()
//                .collect(Collectors.groupingBy(
//                        Order::getProduct,
//                        Collectors.summingInt(Order::getPrice)
//                ));
//
//        List<Map.Entry<String, Integer>> topProducts = totalSalesByProduct.entrySet().stream()
//                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
//                .limit(3)
//                .toList();
//
//        System.out.println("Топ-3 самых дорогих продукта:");
//        topProducts.forEach(entry ->
//                System.out.println("Продукт: " + entry.getKey() + ", Общая стоимость: " + entry.getValue())
//        );
//        System.out.println("End Orders");

        System.out.println("Test BlockingQueue");
        BlockingQueue<Integer> queue = new BlockingQueue<>(5);
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Producing " + i);
                    queue.enqueue(i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Integer item = queue.dequeue();
                    System.out.println("Consuming " + item);
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        producer.start();
        consumer.start();
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End BlockingQueue");

        System.out.println("Test ConcurrentBank");
        ConcurrentBank bank = new ConcurrentBank();

        // Создание счетов
        BankAccount account1 = bank.createAccount(1000);
        BankAccount account2 = bank.createAccount(500);

        // Перевод между счетами
        Thread transferThread1 = new Thread(() -> bank.transfer(account1, account2, 200));
        Thread transferThread2 = new Thread(() -> bank.transfer(account2, account1, 100));

        transferThread1.start();
        transferThread2.start();

        try {
            transferThread1.join();
            transferThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Total balance: " + bank.getTotalBalance());
        System.out.println("End ConcurrentBank");

        System.out.println("Test ComplexTaskExecutor");
        ComplexTaskExecutor taskExecutor = new ComplexTaskExecutor(5); // Количество задач для выполнения

        Runnable testRunnable = () -> {
            System.out.println(Thread.currentThread().getName() + " started the test.");

            // Выполнение задач
            taskExecutor.executeTasks(5);

            System.out.println(Thread.currentThread().getName() + " completed the test.");
        };

        Thread thread1 = new Thread(testRunnable, "TestThread-1");
        Thread thread2 = new Thread(testRunnable, "TestThread-2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("End ComplexTaskExecutor");
    }
}
