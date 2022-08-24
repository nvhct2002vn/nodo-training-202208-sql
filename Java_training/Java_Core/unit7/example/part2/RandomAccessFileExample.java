package demo.unit7.example.part2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RandomAccessFileExample {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("D:\\Users\\Document\\DataStudy\\Thuc_Tap\\Training Java\\sample2.txt");

        ByteBuffer buffer = ByteBuffer.allocate(15);

        try (FileChannel fc = FileChannel.open(path)) {
            fc.read(buffer);
            System.out.println(new String(buffer.array()));
            fc.position(0);
            byte[] bytes = "Trần".getBytes(StandardCharsets.UTF_8);
//            fc.write(ByteBuffer.wrap(bytes));
        }

    }

}
