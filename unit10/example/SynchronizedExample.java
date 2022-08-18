package demo.unit10.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class SynchronizedExample {

    public static void addData(List<Integer> list) {
        IntStream.range(0, 1000).forEach(index -> {
            try {
                list.add(index);
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {
        List<List<Integer>> values = new ArrayList<List<Integer>>();
        for (int i = 0; i < 10; i++) {
            List<Integer> list = new ArrayList<>();
            new Thread(() -> addData(list)).start();
            new Thread(() -> addData(list)).start();
            values.add(list);
        }
        Thread.sleep(5 * 1000);
        values.forEach(list -> System.out.println("numbers items of list -------> " + list.size()));
    }
}
