package demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class ArrayListExample {

    public static void main(String[] args) {
        arrayDemo(new String[]{"hello", "nguyen", "viet", "hien"});
    }

    public static void arrayDemo(String[] args) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, args);

        for (int i = 0; i < list.size(); i++) {
            System.out.println("element at " + i + " is " + list.get(i));
        }

        IntStream.range(0, list.size()).forEach(i -> {
            System.out.println("element at " + i + " is " + list.get(i));
        });
    }

}
