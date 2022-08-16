package demo.unit1.example.BaiLam;

public class MathExample {
    public static void main(String[] args) {
        example1();
    }

    public static void example1() {
        int i = 4;
        int j = -8;
        double x = 47.1;
        double y = 1.79;

        System.out.println("|" + j + "| is: " + Math.abs(j));
        System.out.println("|" + x + "| is: " + Math.abs(x));

        System.out.println(x + " is approximately " + Math.round(x));
        System.out.println("The ceiling of " + i + " is " + Math.ceil(i));
        System.out.println("The ceiling of " + y + " is " + Math.ceil(y));
        System.out.println("The floor of " + x + " is " + Math.floor(x));
        System.out.println("Min( " + x + ", " + y + " ) is " + Math.min(x, y));
    }

    public static void example2() {
        System.out.println("Pi is: " + Math.PI);

        double angle = 45.0 * 2.0 * Math.PI / 360.0;
        System.out.println("Cos(" + angle + ") is: " + Math.cos(angle));

        double value = 0.707;
        System.out.println("acos(" + value + ") is: " + Math.acos(value));

        System.out.println("Exp(0.0) is: " + Math.exp(0.0));
        System.out.println("Log(10.0) is: " + Math.log(10.0));
        System.out.println("Pow(2.0, 2.0) is: " + Math.pow(2.0, 2.0));
    }
}
