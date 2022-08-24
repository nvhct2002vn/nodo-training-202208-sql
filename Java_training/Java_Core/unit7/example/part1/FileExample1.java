package demo.unit7.example.part1;

import java.io.File;

public class FileExample1 {
    public static void main(String[] args) {
        File file = new File("D:\\Users\\Document\\DataStudy\\Thuc_Tap\\Training Java");
        if (file.isFile()) {
            System.out.println("this is file");
        } else {
            System.out.println("this is folder");
        }

        System.out.println("This is " + (file.isFile() ? "file" : "folder"));

        File[] files = file.listFiles();

        for (File f : files) {
            System.out.println(f.getName());
        }

        System.out.println(files.length);

        for (File f : files) {
            System.out.println(f.getName() + " : " + f.length() + " bytes");
        }
    }
}
