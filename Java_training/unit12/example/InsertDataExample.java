package demo.unit12.example;

public class InsertDataExample {


    public static void main(String[] args) throws Exception {
        String sql = "insert into HIENNV_JAVABASIC values(sq_viethien.nextval,'Nguyen viet hien 4',20)";
        JdbcFirstExample.executeUpdate(sql);
    }

}
