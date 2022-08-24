package demo.unit1.example.BaiLam;

public class MethodExample {
    public static void main(String[] args) {
        bai10_1(new String[]{"7", "8"});
        System.out.println(bai11(4, 7, 5, 12, 6, 9));
    }

    private static int add(int number1, int number2) {
        return number1 + number2;
    }

    public static void bai10_1(String[] args) {
        System.out.println("4 + 7 = " + add(4, 7));
        int value1 = Integer.parseInt(args[0]);
        int value2 = Integer.parseInt(args[1]);

        System.out.println(value1 + " + " + value2 + " = " + add(value1, value2));
    }

    private static int bai11(int... values) {
        int total = 0;
        for (int value : values) {
            total += value;
        }
        return total;
    }
}
