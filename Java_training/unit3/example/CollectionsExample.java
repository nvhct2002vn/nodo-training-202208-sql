package demo.unit3.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CollectionsExample {

    public static void main(String[] args) {
        CollectionsExample2();
    }

    public static void CollectionsExample1() {
        Short[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Short> list = new ArrayList<Short>();

        Collections.addAll(list, values);

        Collections.reverse(list);

        list.toArray(values);

        for (int i = 0; i < list.size(); i++) {
            System.out.print(values[i] + " , ");
        }

        list.forEach(x -> {
            System.out.print(x + " , ");
        });

        int min = list.get(0);
        int max = 0;
        for (int i = 0; i < list.size(); i++) {
            if (min > list.get(i)) {
                min = list.get(i);
            }
        }
        System.out.println("min: " + min);

        for (int i = 0; i < list.size(); i++) {
            if (max < list.get(i)) {
                max = list.get(i);
            }
        }
        System.out.println("max: " + max);
    }

    public static void CollectionsExample2() {
        List<String> list = new ArrayList<String>();
        Collections.addAll(list, "Hiên", "Thuỵ", "Hoà", "Phương", "Vinh", "Sơn");
        Collections.sort(list);
        list.forEach(x -> {
            System.out.print(x + ", ");
        });
        System.out.println("Vi tri thu: " + Collections.binarySearch(list, "Hoà"));

    }

}
