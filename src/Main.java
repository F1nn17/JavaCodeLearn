import practice.collection.Filter;
import practice.collection.PracticeCollection;
import practice.stream.FactorialTask;
import practice.stream.Order;
import practice.stream.Student;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        System.out.println("\n");
        System.out.println("Test Filter");
        Random random = new Random();
        Integer[] array = new Integer[random.nextInt(30 - 5) + 5];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(10 - 1) + 1;
        }
        Filter filter = new Filter() {
            @Override
            public Object apply(Object o) {
                if (o instanceof Integer && o.equals(4)) {
                    return o;
                }
                return null;
            }
        };
        Integer[] filteredArray = PracticeCollection.filter(array, filter);
        System.out.println("result filtered");
        for (Integer i : filteredArray) {
            System.out.print(i);
        }
        System.out.println("\nEnd task filter");
        System.out.println("\n");
        System.out.println("Test PracticeCollection");
        System.out.println("result count of elements");
        System.out.println(PracticeCollection.countElements(array));
        System.out.println("End PracticeCollection");
        System.out.println("\n");
        System.out.println("Test Factorial task");
        int number = random.nextInt(30 - 1) + 1;
        ForkJoinPool pool = new ForkJoinPool();
        FactorialTask task = new FactorialTask(number);
        long result = pool.invoke(task);
        System.out.println("Factorial number " + number + " := " + result);
        System.out.println("End Factorial task");
        System.out.println("\n");
        System.out.println("Test Students");
        List<Student> students = Arrays.asList(
                new Student("Alice",
                        Map.of("Math", random.nextInt(5 - 1) + 1,
                                "English", random.nextInt(5 - 1) + 1,
                                "History", random.nextInt(5 - 1) + 1)),
                new Student("Bob",
                        Map.of("Math", random.nextInt(5 - 1) + 1,
                                "English", random.nextInt(5 - 1) + 1,
                                "History", random.nextInt(5 - 1) + 1)),
                new Student("Charlie",
                        Map.of("Math", random.nextInt(5 - 1) + 1,
                                "English", random.nextInt(5 - 1) + 1,
                                "History", random.nextInt(5 - 1) + 1)),
                new Student("David",
                        Map.of("Math", random.nextInt(5 - 1) + 1,
                                "English", random.nextInt(5 - 1) + 1,
                                "History", random.nextInt(5 - 1) + 1))
        );

        Map<String, Double> averageGrades = students.parallelStream()
                .flatMap(student -> student.getSubjects().entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.averagingInt(Map.Entry::getValue)
                ));

        averageGrades.forEach((subject, avgGrade) ->
                System.out.println("Предмет: " + subject + ", Средняя оценка: " + avgGrade)
        );
        System.out.println("End Students");
        System.out.println("\n");
        System.out.println("Test Orders");
        List<Order> orders = Arrays.asList(
                new Order("Laptop", random.nextInt(5000-100)+100),
                new Order("Smartphone", random.nextInt(5000-100)+100),
                new Order("Laptop", random.nextInt(5000-100)+100),
                new Order("Smartwatch", random.nextInt(5000-100)+100),
                new Order("Smartphone", random.nextInt(5000-100)+100),
                new Order("Tablet", random.nextInt(5000-100)+100),
                new Order("Tablet", random.nextInt(5000-100)+100),
                new Order("Laptop", random.nextInt(5000-100)+100)
        );

        Map<String, Integer> totalSalesByProduct = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getProduct,
                        Collectors.summingInt(Order::getPrice)
                ));

        List<Map.Entry<String, Integer>> topProducts = totalSalesByProduct.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .limit(3)
                .toList();

        System.out.println("Топ-3 самых дорогих продукта:");
        topProducts.forEach(entry ->
                System.out.println("Продукт: " + entry.getKey() + ", Общая стоимость: " + entry.getValue())
        );
        System.out.println("End Orders");
    }
}
