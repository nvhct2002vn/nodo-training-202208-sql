package demo.unit3.example;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LinkedListExample {
    public static void main(String[] args) {
        linkedDemo(new String[]{
                "hello", "nguyen", "viet", "hien"
        });
    }

    public static void linkedDemo(String[] args) {
        List<String> list = new LinkedList<>();

        for (String ele : args) {
            list.add(ele);
        }

        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            System.out.println("===> " + iterator.next());
        }
    }
}
