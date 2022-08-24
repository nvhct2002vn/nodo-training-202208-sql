package demo.unit2.example;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class CharaterExample {

    public static void main(String[] args) {
//        args = new String[]{"hien", "viet", "nguyen"};
        System.out.println("there are " + countDigit2("Nguyen viet hien 1232"));
    }

    private static int countDigit(String value) {
        int i = 0;
        int counter = 0;

        //isDigit kiem tra xem co so ko
        while (i < value.length()) {
            char c = value.charAt(i);
            if (Character.isDigit(c)) {
                counter++;
            }
            i++;
        }
        return counter;
    }

    private static int countDigit2(String value) {
        AtomicInteger counter = new AtomicInteger(0);

        IntStream stream = value.chars();
        stream.forEach(c -> {
            if (Character.isDigit(c)) {
                counter.incrementAndGet();
            }
        });
        return counter.get();
    }
}
