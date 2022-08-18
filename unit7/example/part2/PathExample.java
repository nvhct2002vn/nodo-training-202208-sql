package demo.unit7.example.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample {
    // lấy ra các tên file trong folder
    public static void main(String[] args) throws IOException {
        Path1();
    }

    public static void Path1() throws IOException {
        Path path = Paths.get("D:\\Users\\Document\\DataStudy\\Thuc_Tap\\Training Java\\");

        System.out.println("This is " + (Files.isDirectory(path) ? "file" : "folder") + "!");

        System.out.println(Files.exists(path));

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
            for (Path p : directoryStream) {
                System.out.println(p.getFileName());
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path entry) throws IOException {
                return Files.isDirectory(entry);
            }
        };
        System.out.println("=================================================");

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path, filter)) {
            for (Path p : directoryStream) {
                System.out.println(p.getFileName());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Files.newDirectoryStream(path, "*.{java.txt}");

        System.out.println("=================================================");

        Path path1 = Paths.get("D:\\Users\\Document\\DataStudy\\Thuc_Tap\\Training Java\\");
        Path path2 = path1.resolve("hanoijava.txt");

        try (BufferedReader reader = Files.newBufferedReader(path2, StandardCharsets.UTF_8)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
