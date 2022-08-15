package demo.Unit1_BaiLam;

import java.util.Arrays;

public class StreamProgram {
    public static void main(String[] args) {
        bai3(new String[]{"say", "good by", "to", "you"});
    }

    public static void bai3(String[] args) {
        System.out.println("lengt args is : " + args.length);
        Arrays.stream(args).forEach((String value) -> {
            System.out.println("value is " + value);
        });
    }
}
