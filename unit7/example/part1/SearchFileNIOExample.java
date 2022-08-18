package demo.unit7.example.part1;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchFileNIOExample {

    public static void main(String[] args) throws Exception {
        Pattern pattern = Pattern.compile("s\\S", Pattern.CASE_INSENSITIVE);

        File file = new File("D:\\Users\\Document\\DataStudy\\Thuc_Tap\\Training Java\\con_ca.txt");

        FileInputStream inputStream = new FileInputStream(file);
        FileChannel channel = inputStream.getChannel();

        ByteBuffer bytes = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
        CharBuffer chars = Charset.defaultCharset().decode(bytes);

        Matcher matcher = pattern.matcher(chars);

        if (matcher.find()) {
            System.out.println("found at: " + Integer.toString(matcher.start()));
            System.out.println("value " + chars.subSequence(0, 100) + "'");
        }else {
            System.out.println("not found");
        }

    }
}
