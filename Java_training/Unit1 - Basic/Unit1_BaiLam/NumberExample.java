package demo.Unit1_BaiLam;

public class NumberExample {
    public static void main(String[] args) {
        bai4(new String[]{"8", "3"});
    }

    public static void bai4(String[] args) {
        int number1 = Integer.parseInt(args[0]);
        int number2 = Integer.parseInt(args[1]);

        System.out.println("subtraction example: " + (number1 - number2));
        System.out.println("relational example: " + "number1 > number2 = " + (number1 > number2));
    }
}
