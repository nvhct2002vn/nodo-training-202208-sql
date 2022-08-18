package demo.unit7.example.part1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class ObjectWriterExample {

    public static void ObjectWriterFile() throws Exception {
        File folder = new File("D:\\Users\\Document\\DataStudy\\Thuc_Tap\\Training Java\\");

        ObjectOutputStream outputStream = null;

        FileOutputStream fileOutput = new FileOutputStream(new File(folder, "con_ca.txt"));

        outputStream = new ObjectOutputStream(fileOutput);

        PrintMessage printMessage = new PrintMessage("Can noi noi");
        outputStream.writeObject(printMessage);

        outputStream.close();

    }

    public static void main(String[] args) throws Exception {
        ObjectWriterFile();
    }
}
