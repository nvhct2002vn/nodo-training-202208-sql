package demo.unit6.example;

import java.util.concurrent.TimeUnit;

public class ThreadNumberTest {
    public static void main(String[] args) throws InterruptedException {

        PrintNumber number = new PrintNumber();
        Thread thread = new Thread(number);

        thread.setName("Ha noi");

        thread.start();

        Thread.currentThread().join();

        while (thread.isAlive()) {
            if (number.getNumber() % 10 == 0) {
                number.setAlive(false);
                Thread.sleep(1000);
                TimeUnit.SECONDS.sleep(1);
            }
        }
    }
}
