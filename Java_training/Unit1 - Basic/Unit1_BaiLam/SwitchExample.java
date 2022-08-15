package demo.Unit1_BaiLam;

public class SwitchExample {
    public static void main(String[] args) {
        bai7(new String[]{"1"});
    }

    public static void bai7(String[] args) {
        int value = Integer.parseInt(args[0]);
        switch (value) {
            case 0:
                System.out.println("bad");
                break;
            case 1:
                System.out.println("OK");
                break;
            default:
                System.out.println("invalid");
                break;
        }
    }
}
