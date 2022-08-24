package demo.unit3.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ConcurrencyTest {
    public static void main(String[] args) {
        concurrencyTest3();
    }

    public static void concurrencyTest1() {
        List<Integer> list = new ArrayList<Integer>();

        Collections.addAll(list, 1, 2, 3, 4, 5, 6, 7, 8);

        System.out.println("before remove: size of list: " + list.size());

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 3) {
                list.remove(i);
            }
        }
        System.out.println("After remove: size of list: " + list.size());
    }

    public static void concurrencyTest2() {
        List<Integer> list = new ArrayList<Integer>();

        Collections.addAll(list, 1, 3, 4, 2, 3, 6, 3, 3, 8);

        System.out.println("before2 remove: size of list: " + list.size());

        list.forEach(x -> {
            if (x == 3) {
                list.remove((Object) x);
            }
        });
        System.out.println("After2 remove: size of list: " + list.size());
    }

    public static void concurrencyTest3() {
        List<Integer> list = new ArrayList<Integer>();

        Collections.addAll(list, 1, 3, 2, 4, 6, 2, 4, 8, 9, 1);

        Iterator<Integer> iterator = list.iterator();

        list.removeIf(item -> item == 3);

        list.forEach(System.out::print);

        list.removeAll(Collections.singleton(3));

        System.out.println();

        list.forEach(System.out::print);
    }
}
