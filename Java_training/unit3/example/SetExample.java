package demo.unit3.example;

import java.util.Set;
import java.util.TreeSet;

public class SetExample {
    public static void main(String[] args) {
        Set<Integer> numbers = new TreeSet<Integer>();

        numbers.add(1);
        numbers.add(14);
        numbers.add(8);
        numbers.add(3);
        numbers.add(12);
        numbers.add(8);

        System.out.println("size of set: " + numbers.size());

        numbers.forEach(x -> {
            System.out.println(x);
        });
    }
}
