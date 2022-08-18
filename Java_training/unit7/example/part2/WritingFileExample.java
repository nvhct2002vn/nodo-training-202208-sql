package demo.unit7.example.part2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WritingFileExample {

    public static void main(String[] args) {
        Path path = Paths.get("D:\\Users\\Document\\DataStudy\\Thuc_Tap\\Training Java\\hanoijava.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            for (int i = 0; i < 10; i++) {
                writer.write("Nguyen viet " + i + "\r\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
