package demo.unit2.example;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamArrayExample {

    public static void main(String[] args) {
        Integer[] values = {2, 4, 5, 7, 1, 3, 5, 9, 11, 13};

        Arrays.sort(values, (Integer o1, Integer o2) -> {
            return o2 - o1;
        });

        Stream<Integer> stream = Arrays.stream(values);

        stream.forEach(value -> {
            System.out.print(value + ", ");
        });

        Consumer<Integer> consumer = (Integer value) -> {
            System.out.print(value + ", ");
        };
        stream.forEach(consumer);

        Optional<Integer> max = stream.max((o1, o2) -> {
            return o1 - o2;
        });
        System.out.println("Max: " + max.get());

        stream = Arrays.stream(values);
        Predicate<Integer> predicate = (Integer value) -> {
            return value > 5;
        };
        stream.filter(predicate).forEach(value -> {
            System.out.println(value);
        });
    }

}
