package demo.unit9.example;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorSortExample {

    public static void main(String[] args) {
        Integer[] values = {12, 31, 3, 5, 1, 7, 12};

        Arrays.sort(values, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        for (Integer value : values) {
            System.out.print(value + ", ");
        }
    }

}
