package demo.unit12.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FuntionExample {

    public static void getAge(String name, int[] ages) throws Exception {
        Connection connection = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@27.118.22.14:1521:orcl", "SCOTT", "SCOTT");

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT MAX(AGE) FROM HIENNV_JAVABASIC WHERE FULLNAME LIKE '%" + name + "%'");
            ages[0] = rs.next() ? rs.getInt(1) : -1;
        } finally {
            connection.close();
        }

    }

    public static void main(String[] args) throws Exception {
        int[] ages = new int[1];
        getAge("viet", ages);
        System.out.println(ages[0]);
    }

}
