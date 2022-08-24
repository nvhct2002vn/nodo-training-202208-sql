package demo.unit7.example.part2;

import java.io.IOException;
import java.nio.file.*;

public class FileFinderExample {

    public static class Finder extends SimpleFileVisitor {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.{txt}");

        public FileVisitResult visitResult(Path path) {
            if (matcher.matches(path.getFileName())) {
                System.out.println("found:  " + path);
            }
            return FileVisitResult.CONTINUE;
        }
    }

    public static void main(String[] args) throws IOException {
        Finder finder = new Finder();
        Files.walkFileTree(Path.of("D:\\Users\\Document\\DataStudy\\Thuc_Tap\\Training Java\\"), finder);
    }

}
