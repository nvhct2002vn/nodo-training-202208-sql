package demo.unit7.example.part2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CopyingAndMovingExample {

    public static void main(String[] args) throws IOException {
        Path source = Paths.get("D:\\Users\\Document\\DataStudy\\Thuc_Tap\\Training Java\\hanoijava.txt");
        Path target = Paths.get("D:\\Users\\Document\\DataStudy\\Thuc_Tap\\Training Java\\hanoijava2.txt");
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

        Files.delete(target);

        Files.createLink(target, source);
    }

}
