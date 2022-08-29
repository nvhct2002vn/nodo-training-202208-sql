package edu.java.spring;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentJdbcDAO {

    private static final Logger LOGGER = Logger.getLogger(StudentJdbcDAO.class);
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    private String insertQuery;
    private String updateQuery;

    private void createTableIfNotExist() throws SQLException {
        DatabaseMetaData databaseMetaData = dataSource.getConnection().getMetaData();
        ResultSet rs = databaseMetaData.getTables(null, null, "STUDENT", null);
        if (rs.next()) {
            LOGGER.info("Table" + rs.getString("TABLE_NAME") + " already exists");
            return;
        }
        jdbcTemplate.execute("create table student (\n" + "\tid   bigint primary key auto_increment,\n" + "\tname varchar(1000),  \n" + "\tage  integer)");
    }
//    private String setUpdateAgeByNameSQL = "update student set age = ? where name = ? ";

    @Autowired
    private PlatformTransactionManager transactionManager;

    public void insert(String name, Integer age) {
        jdbcTemplate.update(insertQuery, name, age);
        LOGGER.info("Craeated Record Name = " + name + "Age = " + age);
    }


    public int totalRecord() {
//        return jdbcTemplate.execute(new StatementCallback<Integer>() {
//            @Override
//            public Integer doInStatement(Statement statement) throws SQLException, DataAccessException {
//                ResultSet rs = statement.executeQuery("select count(*) from student");
//                return rs.next() ? rs.getInt(1) : 0;
//            }
//        });
        return jdbcTemplate.execute((Statement statement) -> {
            ResultSet rs = statement.executeQuery("select count(*) from student");
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    public void setDataSource(DriverManagerDataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public String getInsertQuery() {
        return insertQuery;
    }

    public void setUpdateAgeByNameSQL(Integer age, String name) {
//        jdbcTemplate.update(setUpdateAgeByNameSQL, age, name);
        jdbcTemplate.update(updateQuery, age, name);
        LOGGER.info("Update Record Name = " + name + "Age = " + age);
    }

    public void setInsertQuery(String insertQuery) {
        this.insertQuery = insertQuery;
    }

    public String getUpdateQuery() {
        return updateQuery;
    }

    public void setUpdateQuery(String updateQuery) {
        this.updateQuery = updateQuery;
    }

    private final static class StudentRowMapper implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            try {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                return student;
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                return null;
            }
        }

    }

    public List loadStudents(String name) {
        return jdbcTemplate.query("select * from student where name like '%" + name + "%'", new StudentRowMapper());
    }

    public int[] add(List<Student> students) {
        List<Object[]> batch = new ArrayList<>();
        students.forEach(student -> batch.add(new Object[]{student.getName(), student.getAge()}));
        return jdbcTemplate.batchUpdate(insertQuery, batch);
    }

    public StudentJdbcDAO() {
        transactionManager = new PlatformTransactionManager() {
            @Override
            public TransactionStatus getTransaction(TransactionDefinition transactionDefinition) throws TransactionException {
                return null;
            }

            @Override
            public void commit(TransactionStatus transactionStatus) throws TransactionException {

            }

            @Override
            public void rollback(TransactionStatus transactionStatus) throws TransactionException {

            }
        };
    }

    public void save(Object name, Object age) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        String countQuery = "select count(*) from student";
        try {
            int total = jdbcTemplate.queryForObject(countQuery, Integer.class);
            LOGGER.info("before save data, total record is " + total);

            String sql = "insert into student (name,age) values (?,?)";
            jdbcTemplate.update(sql, name, age);

            total = jdbcTemplate.queryForObject(countQuery, Integer.class);
            LOGGER.info("after save data, total record is " + total);

            String countQuery2 = "select count(*) from student";
            total = jdbcTemplate.queryForObject(countQuery2, Integer.class);

            transactionManager.commit(status);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            transactionManager.rollback(status);
            int total = jdbcTemplate.queryForObject(countQuery, Integer.class);
            LOGGER.info("after rollback, total record is " + total);
        }
    }
}
