package demo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LinkedListExample2 {

    public static void main(String[] args) {
        linked2(new String[]{"hello", "nguyen", "viet", "hien"});
    }

    public static void linked2(String[] args) {
        List list = new LinkedList<>(Arrays.asList(args));

        list.forEach(value -> {
            System.out.println(value);
        });
    }

}
