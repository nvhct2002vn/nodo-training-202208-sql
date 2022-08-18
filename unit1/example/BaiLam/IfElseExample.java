package demo.unit1.example.BaiLam;

public class IfElseExample {
    public static void main(String[] args) {
        bai6(new String[]{"8"});
    }

    public static void bai6(String[] args) {
        int value = Integer.parseInt(args[0]);
        if (value < 5) {
            System.out.println("bad");
        } else {
            System.out.println("OK");
        }
    }
}
