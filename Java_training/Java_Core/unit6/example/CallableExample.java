package demo.unit6.example;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class CallableExample {

    public static void main(String[] args) throws Exception {
        PrintNumberCallable numberCallable = new PrintNumberCallable();
        Callable<Object> value = Executors.callable(numberCallable);
        System.out.println("main say hello");
        value.call();
        System.out.println("main say goodbye");
    }

    public static class PrintNumberCallable implements Runnable {
        private Integer number = new Integer(1);

        @Override
        public void run() {
            Thread current = Thread.currentThread();
            while (true) {
                number++;
                System.out.println(current.getName() + " number is \"" + number + "\"");
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (number % 10 == 0) {
                    break;
                }
            }
            System.out.println(current.getName() + " is stoped!");
        }
    }

}
