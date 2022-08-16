package demo.unit6.example;

import static java.util.Arrays.stream;

public class PrintMessage implements Runnable {

    public static void main(String[] args) {
       /*PrintMessage mess = new PrintMessage("say hello thread java");
        new Thread(mess).start();
        new Thread(mess).start();*/

        new Thread(new PrintMessage("Nguyen viet hien hello java")).start();
        new Thread(new PrintMessage("Nguyen viet hien hello java")).start();
    }

    private String message;

    public PrintMessage(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        String[] elements = message.split(" ");
        stream(elements).forEach(ele -> {
            /*try {
                System.out.println(Thread.currentThread().getName() + " print " + ele);
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            try {
                System.out.println(Thread.currentThread().getName() + " print " + ele);
                Thread.sleep((int) (Math.random() * 3) * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public synchronized void run2() {
        String[] elements = message.split(" ");
        stream(elements).forEach(ele -> {
            System.out.println(Thread.currentThread().getName() + " print " + ele);
            try {
                Thread.sleep((int) (Math.random() * 3) * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
