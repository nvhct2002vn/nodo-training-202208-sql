package demo.unit7.example.part1;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ReadFileNIOExample {

    public static void main(String[] args) throws Exception {
        File file = new File("D:\\Users\\Document\\DataStudy\\Thuc_Tap\\Training Java\\con_ca.txt");

        FileInputStream fileInputStream = null;
        FileChannel fileChannel = null;

        try {
            fileInputStream = new FileInputStream(file);
            fileChannel = fileInputStream.getChannel();

            long size = fileChannel.size();
            ByteBuffer buffer = ByteBuffer.allocate((int) size);
            fileChannel.read(buffer);
            buffer.rewind();

            System.out.println(new String(buffer.array(), "utf-8"));

        } finally {
            if (fileChannel != null) {
                fileChannel.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }
    }

}
