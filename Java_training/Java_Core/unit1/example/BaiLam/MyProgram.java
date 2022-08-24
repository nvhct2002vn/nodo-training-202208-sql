package demo.unit1.example.BaiLam;

public class MyProgram {
    public static void main(String[] args) {
        bai1();
        bai2(new String[]{"say", "good by", "to", "you"});
    }

    public static void bai1() {
        System.out.println("Say hello to java!");
        System.out.println("Total: " + 5 + 7);
    }

    public static void bai2(String[] args) {
        System.out.println("The number of arguments is" + args.length);
        for (int i = 0; i < args.length; i++) {
            System.out.println("value at " + i + " is " + args[i]);
        }
    }
}
