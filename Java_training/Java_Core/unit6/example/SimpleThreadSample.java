package demo.unit6.example;

import java.util.Arrays;

public class SimpleThreadSample {
    public static void main(String[] args) {
        thread1(new String[]{"Hien", "Nguyen", "Viet", "Hihi"});
    }

    public static void thread1(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Arrays.stream(args).forEach(ele -> {
                    try {
                        Thread.sleep(1000);
                        System.out.println(ele);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        });
        t.start();

        new Thread(() -> {
            Arrays.stream(args).forEach(ele -> {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(ele);
            });

        }).start();
    }
}
