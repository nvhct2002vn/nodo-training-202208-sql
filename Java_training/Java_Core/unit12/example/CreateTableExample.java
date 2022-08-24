package demo.unit12.example;

public class CreateTableExample {
    public static void main(String[] args) {
        String sql = "Create table HIENNV_JAVABASIC (ID NUMBER(4) PRIMARY KEY, FULLNAME VARCHAR2(54),AGE NUMBER(4))";
//        String sqlSQ = "create sequence sq_viethien increment by 1  start with 1 nocycle";

        System.out.println(JdbcFirstExample.executeQuery(sql));
    }
}
