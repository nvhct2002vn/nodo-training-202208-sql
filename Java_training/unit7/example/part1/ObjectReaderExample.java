package demo.unit7.example.part1;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectReaderExample {

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        File folder = new File("D:\\Users\\Document\\DataStudy\\Thuc_Tap\\Training Java\\");

        FileInputStream fileInputStream = new FileInputStream(new File(folder, "con_ca.txt"));

        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Object obj = objectInputStream.readObject();

        Method method = obj.getClass().getMethod("run", new Class[0]);

        method.invoke(obj, new Object[0]);

        objectInputStream.close();
        fileInputStream.close();

    }

}
