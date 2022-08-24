package demo.unit2.example;

import java.util.Arrays;

public class SortArrayExample {
    public static void main(String[] args) {
        sortNow(new String[]{"1", "2", "7", "3", "5", "6", "9", "5"});
    }

    public static void sortNow(String[] args) {
//        args = new String[]{"1", "2", "7", "3", "5"};
        Arrays.sort(args);
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
        System.out.println("========================");
        Arrays.sort(args, ((o1, o2) -> o2.compareTo(o1)));

        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
    }
}
