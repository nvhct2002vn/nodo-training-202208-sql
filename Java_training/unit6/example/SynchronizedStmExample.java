package demo.unit6.example;

public class SynchronizedStmExample {

    public static void main(String[] args) {
        PrintNumberScStm numberScStm = new PrintNumberScStm();
        Thread thread1 = new Thread(numberScStm);
        thread1.setName("Nguyen viet hien");
        thread1.start();

        Thread thread2 = new Thread(numberScStm);
        thread2.setName("nguyen viet hien 2");
        thread2.start();
    }

    public static class PrintNumberScStm implements Runnable {
        private Integer number = new Integer(1);

        @Override
        public void run() {
            Thread current = Thread.currentThread();
            synchronized (number) {
                while (number < 30) {
                    number++;
                    System.out.println(current.getName() + " number is \"" + number + "\"");
                    try {
                        Thread.sleep(800);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(current.getName() + " is stoped!");
        }
    }

}
