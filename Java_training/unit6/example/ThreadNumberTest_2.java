package demo.unit6.example;

public class ThreadNumberTest_2 {
    public static void main(String[] args) throws InterruptedException {
        PrintNumber number = new PrintNumber();

        Thread thread = new Thread(number);

        thread.setName("Ha noi");

        thread.setDaemon(true);

        thread.start();

//        Thread.currentThread().join();

        System.out.println("Main Thread say hello");
        System.out.println("Main Thread say goodbye");

    }

}
