package demo.unit7.example.part1;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class CopyFileExample {

    public static void main(String[] args) throws Exception {
        File sourceFile = new File("D:\\Users\\Document\\DataStudy\\Thuc_Tap\\Training Java\\con_ca.txt");
        File desFile = new File(sourceFile.getParentFile(), "sample2.txt");

        FileChannel srcChannel = null;
        FileChannel desChannel = null;

        srcChannel = new FileInputStream(sourceFile).getChannel();
        desChannel = new FileOutputStream(desFile).getChannel();
        srcChannel.transferTo(0, srcChannel.size(), desChannel);

        Desktop.getDesktop().open(sourceFile.getParentFile());
    }

}
