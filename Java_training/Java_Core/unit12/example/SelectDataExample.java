package demo.unit12.example;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectDataExample {

    public static void main(String[] args) throws SQLException {
        String sql = "Select * from HIENNV_JAVABASIC";

        ResultSet resultSet = JdbcFirstExample.executeQuery(sql);

        while (resultSet.next()) {
            System.out.println("Id: " + resultSet.getInt("id") + " Full Name: " + resultSet.getString("fullname") + " Age: " + resultSet.getInt("age"));
        }
    }
}
