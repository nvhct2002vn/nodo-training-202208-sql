package demo.unit1.example.BaiLam;

public class StringExample2 {

    public static void main(String[] args) {
        bai13(new String[]{"hien"});
    }


    public static void bai13(String[] args) {
        char[] chars = {'h', 'e', 'l', 'l', 'o'};
        String text = new String(chars);
        System.out.println(text);

        text = text.concat(" ");
        text = text.concat(args[0]);
        System.out.println("new value is : " + text);

        System.out.println("Hello java".equals(text));
    }
}
