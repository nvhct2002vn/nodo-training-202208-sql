package demo.unit10.example;

import java.util.ArrayList;
import java.util.List;

public class ConcurrencyExample {

    public static void addData(List<Integer> doubleList) throws InterruptedException {
        while (true) {
            int random = (int) (Math.random() * 100);
            doubleList.add(random);
            System.out.println("add new data : " + random);
            Thread.sleep(1000);
        }
    }

    public static void printData(List<Integer> doubleList) {
        while (true) {
            try {
                System.out.println("=========================");
                doubleList.forEach(value -> System.out.println("value: " + value));
                Thread.sleep(800);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> doubleList = new ArrayList<>();
        new Thread(() -> {
            try {
                addData(doubleList);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        new Thread(() -> {
            printData(doubleList);
        }).start();
    }
}
