package demo.unit12.example;

import java.sql.*;

public class TransactionExample {
    private final static String DB_NAME = "oracle.jdbc.driver.OracleDriver";
    private final static String DB_URL = "jdbc:oracle:thin:@27.118.22.14:1521:orcl";
    private final static String DB_USER = "SCOTT";
    private final static String DB_PASSWORD = "SCOTT";


    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName(DB_NAME);
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        PreparedStatement preparedStatement = null;
        connection.setAutoCommit(false);

        String sql = "insert into hiennv_javabasic values (sq_viethien.nextval,?,?)";
        for (int i = 0; i < 10; i++) {
            String name = "Le Minh Thuy" + i;
            Integer age = 10 + i;

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.executeUpdate();
        }

        connection.rollback();
        connection.setAutoCommit(true);
        ResultSet rs = preparedStatement.executeQuery("Select count(*) from hiennv_javabasic");
        if (rs.next()) {
            System.out.println("total records = " + rs.getInt(1));
            connection.close();
        }

    }
}
