package demo.unit12.example;

public class UpdateDataExample {

    public static void main(String[] args) {
        String sql = "update HIENNV_JAVABASIC set  fullname = ?,age = ? where id = ?";

        JdbcFirstExample.executeQuery(sql, "Nguyen van toan", 20, 3);

    }
}
