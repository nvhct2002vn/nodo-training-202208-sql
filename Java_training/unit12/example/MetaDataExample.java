package demo.unit12.example;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MetaDataExample {

    public static void main(String[] args) throws SQLException {
        Connection connection = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@27.118.22.14:1521:orcl", "SCOTT", "SCOTT");
            DatabaseMetaData metaData = connection.getMetaData();

            System.out.println("db version " + metaData.getDatabaseProductVersion());
            System.out.println("driver name " + metaData.getDriverName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
    }

}
