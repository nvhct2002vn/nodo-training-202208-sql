package demo.unit7.example.part2;

import java.io.BufferedReader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LinkFileExample {

    public static void main(String[] args) throws Exception {
        Path source = Paths.get("D:\\Users\\Document\\DataStudy\\Thuc_Tap\\Training Java\\hanoijava.txt");
        Path target = Paths.get("D:\\Users\\Document\\DataStudy\\Thuc_Tap\\Training Java\\hanoijava2.txt");

        Charset charset = StandardCharsets.UTF_8;

        try (Writer writer = Files.newBufferedWriter(source, charset)) {
            writer.write("Java NIO 2 example \r\n");
        }
        try (BufferedReader reader = Files.newBufferedReader(target, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

}

