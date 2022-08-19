package demo.unit6.example;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class FeatureExample {

    public static class CallableSample implements Callable<Integer> {

        public static void main(String[] args) throws Exception {
            CallableSample callableSample = new CallableSample();

            ExecutorService executors = Executors.newFixedThreadPool(1);

            Future<Integer> future = executors.submit(callableSample);
            System.out.println("Future Done ?  " + future.isDone());

            Integer result = (Integer) future.get(3, TimeUnit.SECONDS);

            System.out.println("Future Done ?" + future.isDone() + " - result = " + result);
        }

        private ReentrantLock lock = new ReentrantLock();

        @Override
        public Integer call() throws Exception {
            lock.lock();
            AtomicInteger total = new AtomicInteger(0);
            IntStream.range(0, 10).forEach(number -> {

                System.out.println(Thread.currentThread().getName() + " -object" + this + " is running " + total.addAndGet(number));

                System.out.println(Thread.currentThread().getName());

                Random random = new Random();
                LongStream longStream = random.longs(100, 1000);
                try {
                    Thread.sleep(longStream.findFirst().getAsLong());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            lock.unlock();
            return total.get();
        }
    }
}
