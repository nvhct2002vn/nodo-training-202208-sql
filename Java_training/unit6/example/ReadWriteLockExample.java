package demo.unit6.example;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Map<Object, Object> map = new HashMap<>();

        ReadWriteLock lock = new ReentrantReadWriteLock();

        executor.submit(() -> {
            System.out.println("Start writing");
            lock.writeLock().lock();
            lock.readLock().lock();
            try {
                TimeUnit.SECONDS.sleep(10);
                map.put("foo", "bar");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.writeLock().unlock();
                System.out.println("end writing");
                lock.readLock().unlock();
            }
        });

        Runnable readTask = () -> {
            System.out.println("Start reading");
            try {
                System.out.println(map.get("foo"));
            } finally {
                System.out.println("end reading");
            }
        };
        executor.submit(readTask);
        executor.submit(readTask);
    }
}
