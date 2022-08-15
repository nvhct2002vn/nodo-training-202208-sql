package demo;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapExample {

    public static void main(String[] args) {
        TreeMap<Integer, String> map = new TreeMap<Integer, String>();

        map.put(3, "Nguyễn Viết A");
        map.put(1, "Nguyễn Viết B");
        map.put(2, "Nguyễn Viết C");
        map.put(4, "Nguyễn Viết D");

        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entity = iterator.next();
            System.out.println(entity.getKey() + " : " + entity.getValue());
        }
        map.descendingKeySet().forEach(key -> {
            System.out.println("key = " + key + " value = " + map.get(key));
        });

    }

}
