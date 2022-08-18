package demo.unit7.example.part1;

import java.io.UnsupportedEncodingException;

public class DecodingExample {
    public static void main(String[] args) throws UnsupportedEncodingException {
        int[] values = {120, 105, 110, 32, 99, 104, -61, -96, 111};
        byte[] bytes = new byte[values.length];

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) values[i];
        }
        System.out.println(new String(bytes, "utf-8"));
    }
}
