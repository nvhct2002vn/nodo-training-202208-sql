package demo.unit7.example.part1;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReaderWriteExample {
    public static void main(String[] args) throws IOException {
        writeFile1();

        readerFile2();

    }

    public static void writeFile1() {
        File file = new File("D:\\Users\\Document\\DataStudy\\Thuc_Tap\\Training Java\\hanoijava.txt");

        try (FileWriter writer = new FileWriter(file)) {
            writer.append("\r\n");
            writer.append("Nguyen Viet Hien");
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readerFile2() throws IOException {

        File file = new File("D:\\Users\\Document\\DataStudy\\Thuc_Tap\\Training Java\\hanoijava.txt");

        FileReader reader = null;

        reader = new FileReader(file);

        char[] buffer = new char[4 * 1024];

        int read = -1;

        StringBuilder builder = new StringBuilder();
        try {
            while ((read = reader.read(buffer)) != -1) {
                builder.append(buffer, 0, read);
            }
            System.out.println(builder);
        } finally {
            reader.close();
        }

    }
}
