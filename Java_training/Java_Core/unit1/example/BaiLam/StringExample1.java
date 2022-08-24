package demo.unit1.example.BaiLam;

public class StringExample1 {
    public static void main(String[] args) {
        bai12();
    }
    public static void bai12() {
        String text = "say hello to everyone";
        System.out.println("length of text is : " + text.length());
        System.out.println("vi tri cua tu hello là: " + text.indexOf("hello"));
        System.out.println("cat tu 4 den 8 : " + text.substring(4, 8));
    }
}
