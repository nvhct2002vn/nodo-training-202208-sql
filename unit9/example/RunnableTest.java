package demo.unit9.example;

public class RunnableTest {
    public static void main(String[] args) {
        RunnableTest1();
        RunnableTest2();
        RunnableTest3();
    }

    public static void RunnableTest1() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "say hello java class");
            }
        };
        new Thread(runnable).start();
    }

    public static void RunnableTest2() {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "say hello java class");
        }).start();
    }

    public static void RunnableTest3() {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "say hello java class");
            System.out.println("1 + 1 = " + (1 + 1));
        }).start();
    }
}
