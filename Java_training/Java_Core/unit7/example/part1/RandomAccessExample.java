package demo.unit7.example.part1;

import java.awt.*;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class RandomAccessExample {

    public static void main(String[] args) throws Exception {
        File file = new File("D:\\Users\\Document\\DataStudy\\Thuc_Tap\\Training Java\\hanoijava.txt");

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            randomAccessFile.seek(9);
            byte[] bytes = new byte[4 * 1024];
            int read = randomAccessFile.read(bytes);
            System.out.println(new String(bytes, 0, read, "utf-8"));

            randomAccessFile.seek(file.length());
            randomAccessFile.write("\r\n".getBytes(StandardCharsets.UTF_8));
            randomAccessFile.writeChars("Hello Co Can");

            Desktop.getDesktop().open(file);

        }
    }


}
