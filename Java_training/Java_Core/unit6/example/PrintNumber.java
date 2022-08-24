package demo.unit6.example;

public class PrintNumber implements Runnable {

    public static void main(String[] args) {
        PrintNumber number = new PrintNumber();

        Thread thread = new Thread(number);
        thread.setName("Hanoi_Thread");
        thread.start();

        try {
            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while (thread.isAlive()) {
            if (number.getNumber() % 10 == 0) {
                number.setAlive(false);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private int number = 1;
    private boolean alive = true;

    public int getNumber() {
        return number;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public void run() {
        Thread current = Thread.currentThread();
        while (number < 10) {
            number++;
            System.out.println(current.getName() + " number is \"" + number + "\"");
            try {
                Thread.sleep(800);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(current.getName() + " is stoped!");
    }
}
