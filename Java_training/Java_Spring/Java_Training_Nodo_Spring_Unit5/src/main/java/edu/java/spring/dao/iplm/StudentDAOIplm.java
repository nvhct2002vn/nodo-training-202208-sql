package edu.java.spring.dao.iplm;

import edu.java.spring.dao.StudentDAO;
import edu.java.spring.model.Student;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class StudentDAOIplm implements StudentDAO, DisposableBean {

    private static final Logger LOGGER = Logger.getLogger(StudentDAOIplm.class);
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    private String insertQuery = "insert into student(name,age) values (?,?)";

    @Autowired
    public void setDataSource(DriverManagerDataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private void createTableIfExist() throws SQLException {
        DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
        ResultSet rs = metaData.getTables(null, null, "STUDENT", null);
        if (rs.next()) {
            LOGGER.info("Table" + rs.getString("TABLE_NAME") + " already exists");
            return;
        }
        jdbcTemplate.execute("create table student (\n" + "\tid   bigint primary key auto_increment,\n" + "\tname varchar(1000),  \n" + "\tage  integer)");
    }

    public String getInsertQuery() {
        return insertQuery;
    }

    public void setInsertQuery(String insertQuery) {
        this.insertQuery = insertQuery;
    }


    @Override
    public void insert(Student student) {
        jdbcTemplate.update(insertQuery, student.getName(), student.getAge());
        LOGGER.info("Create Record Name = " + student.getName());
    }

    public static final class StudentRowMapper implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            try {
                Student student = new Student();
                student.setId((resultSet.getInt("id")));
                student.setName((resultSet.getString("name")));
                student.setAge((resultSet.getInt("age")));
                return student;
            } catch (Exception e) {
                LOGGER.info(e.getMessage());
                return null;
            }
        }
    }

    @Override
    public List<Student> list() {
        return jdbcTemplate.query("select * from student", new StudentRowMapper());
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update("delete from student where id = ?", id);
    }

    @Override
    public Student get(Integer id) {
        return jdbcTemplate.queryForObject("select * from student where id = ?", new StudentRowMapper(), id);
    }

    @Override
    public void update(Student student) {
        jdbcTemplate.update("update student set name = ? , age = ? where id = ?", student.getName(), student.getAge(), student.getId());
    }

    @Override
    public List<Student> getUserByName(String name) {
        return jdbcTemplate.query("select * from student where name like '%" + name + "%'", new StudentRowMapper());
    }

    @Override
    public void destroy() throws Exception {
        DriverManager.getConnection("jdbc:mysql://localhost:3306/spring_test");
    }
}
