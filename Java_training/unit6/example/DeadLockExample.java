package demo.unit6.example;

public class DeadLockExample extends Thread {
    private Object lock1;
    private Object lock2;

    public DeadLockExample(Object lock1, Object lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + " : Holding" + lock1 + "...");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " : Holding" + lock1 + " : " + lock2 + "...");
        }
        synchronized (lock2) {
            System.out.println(Thread.currentThread().getName() + "------------------->" + lock1 + " : " + lock2 + "...");
        }

    }

    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();

        DeadLockExample thread1 = new DeadLockExample(obj1, obj2);
        DeadLockExample thread2 = new DeadLockExample(obj2, obj1);

        thread1.start();
        thread2.start();
    }
}
