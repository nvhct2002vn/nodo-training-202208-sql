package demo;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CollectionOperation {
    public static void main(String[] args) {
        CollectionOperation5();
    }

    public static void collectionOperation1_2() {
        List<Integer> listOfIntegers = new ArrayList<Integer>(Arrays.asList(4, 7, 1, 3, 8, 9, 10));
        Comparator<Integer> comparator = Integer::compareTo;

        Collections.sort(listOfIntegers, comparator);

        listOfIntegers.forEach(x -> {
            System.out.println(x);
        });
        listOfIntegers.stream().filter(v -> v > 5).forEach(v -> {
            System.out.println(v);
        });

        Collector<Integer, ?, IntSummaryStatistics> collector = Collectors.summarizingInt(Integer::intValue);
//        List<Integer> summerStatistics = listOfIntegers.stream().collect(collector);
    }

    public static void CollectionOperation3() {
        int value;
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<Integer>();
        Consumer<Integer> myConsumer = n -> {
            System.out.println("user input = " + n);
            if (n < 5) {
                System.out.println("Invalid value!");
                return;
            }
            list.add(n);
            list.forEach(System.out::println);

        };
        while (true) {
            System.out.print("vui lon nhap: ");
            value = sc.nextInt();
            myConsumer.accept(value);
        }
    }

    public static void CollectionOperation5() {
        int value;
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<Integer>();
        Predicate<Integer> tester = v -> v > 5;

        Consumer<Integer> myConsumer = n -> list.add(n);

        while (true) {
            System.out.print("vui lon nhap: ");
            value = sc.nextInt();
            if (value < 0) break;
            if (tester.test(value)) {
                myConsumer.accept(value);
            }
        }

        list.forEach(System.out::println);
    }
}
