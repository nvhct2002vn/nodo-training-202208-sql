package demo.unit6.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ThreadPool {

    public static void main(String[] args) {
        PrintNumber number = new PrintNumber();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        IntStream.range(0, 6).forEach(i -> executorService.submit(number));

    }

}
