package demo.unit3.example;

import java.util.Hashtable;
import java.util.Map;

public class MapExample {

    public static void main(String[] args) {
        Map<Integer, String> map = new Hashtable<Integer, String>();
        map.put(3, "Nguyễn Viết A");
        map.put(1, "Nguyễn Viết B");
        map.put(2, "Nguyễn Viết C");
        map.put(4, "Nguyễn Viết D");

        System.out.println(map.get(3));

        map.put(3, "Nguyễn Viết Hiên");

        System.out.println(map.get(3));

        map.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(value);
        });
    }

}
