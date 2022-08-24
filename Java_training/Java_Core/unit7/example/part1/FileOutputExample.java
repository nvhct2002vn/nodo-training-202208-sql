package demo.unit7.example.part1;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileOutputExample {


    public static void fileOutput1() throws IOException {
        File file = new File("D:\\Users\\Document\\DataStudy\\Thuc_Tap\\Training Java\\hanoijava.txt");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            byte[] bytes = "Hello Ha Noi Java Class".getBytes(StandardCharsets.UTF_8);
            fileOutputStream.write(bytes);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
    }

    public static void fileOutput2() throws IOException {
        File file = new File("D:\\Users\\Document\\DataStudy\\Thuc_Tap\\Training Java\\hanoijava.txt");
        try (FileInputStream inputStream = new FileInputStream(file)) {
            byte[] bytes = new byte[124];
            inputStream.read(bytes);
            System.out.println(new String(bytes));
            bytes = new byte[124];
            System.out.println("[" + new String(bytes) + "]");
        }
    }

    public static void fileOutput3() throws IOException {
        File file = new File("D:\\Users\\Document\\DataStudy\\Thuc_Tap\\Training Java\\hanoijava.txt");
        try (FileInputStream inputStream = new FileInputStream(file)) {
            byte[] bytes = new byte[4 * 1024];
            int read = -1;
            StringBuilder builder =  new StringBuilder();
            while ((read = inputStream.read(bytes)) != -1) {
                builder.append(new String(bytes, 0, read));
            }
            System.out.println(builder);
        }
    }

    public static void main(String[] args) throws IOException {
        fileOutput3();
    }

}


