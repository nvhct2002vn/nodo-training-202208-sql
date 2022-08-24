package demo.unit7.example.part1;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.stream;

public class PrintMessage implements Runnable, Serializable {
    private String message;

    public PrintMessage(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        String[] elements = message.split(" ");
        stream(elements).forEach(ele -> {
            try {
                System.out.println(Thread.currentThread().getName() + " print " + ele);
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
