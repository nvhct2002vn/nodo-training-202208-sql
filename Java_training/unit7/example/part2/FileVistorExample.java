package demo.unit7.example.part2;

import java.io.IOException;
import java.nio.file.*;

public class FileVistorExample {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("D:\\Users\\Document\\DataStudy\\Thuc_Tap\\Training Java\\");
        System.out.println(Files.walkFileTree(path, new PrintFiles()));
    }

    public static class PrintFiles extends SimpleFileVisitor<Path> {
        public FileVisitResult visitResult(Path path) {
            System.out.println(path + " is file!");
            return FileVisitResult.CONTINUE;
        }

        public FileVisitResult postVisitDirectry() {
            System.out.println(" is directrry");
            return FileVisitResult.CONTINUE;
        }
    }
}
