package demo.unit12.example;

import java.sql.*;

public class JdbcFirstExample {
    private final static String DB_NAME = "oracle.jdbc.driver.OracleDriver";
    private final static String DB_URL = "jdbc:oracle:thin:@27.118.22.14:1521:orcl";
    private final static String DB_USER = "SCOTT";
    private final static String DB_PASSWORD = "SCOTT";

    static {
        try {
            Class.forName(DB_NAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static PreparedStatement preparedStatement(String sql, Object... args) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        PreparedStatement pst = null;
        if (sql.trim().startsWith("{")) {
            pst = connection.prepareCall(sql);
        } else {
            pst = connection.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            pst.setObject(i + 1, args[i]);
        }
        return pst;
    }

    public static void executeUpdate(String sql, Object... args) {
        try {
            PreparedStatement pst = preparedStatement(sql, args);
            try {
                pst.executeUpdate();
            } finally {
                pst.getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet executeQuery(String sql, Object... args) {
        try {
            PreparedStatement pst = preparedStatement(sql, args);
            return pst.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
