package edu.java.spring;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class JdbcApp {
    public static final Logger LOGGER = Logger.getLogger(JdbcApp.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        StudentJdbcDAO jdbc = (StudentJdbcDAO) context.getBean("studentjdbcDAO");
//        LOGGER.info(" created bean " + jdbc);

//        jdbc.insert("Nguyen Viet Hien", 20);

//        LOGGER.info("Total students is " + jdbc.totalRecord());

//        jdbc.setUpdateAgeByNameSQL(98, "Nguyen Viet Hien");
//
//        jdbc.loadStudents("hien").forEach(std -> {
//            System.out.println(std);
//        });

//        List<Student> students = new ArrayList<>();
//
//        students.add(new Student("Nguyen viet A", 20));
//        students.add(new Student("Nguyen viet B", 22));
//        students.add(new Student("Nguyen viet C", 23));
//
//        int[] values = jdbc.add(students);
//        for (int i = 0; i < values.length; i++) {
//            LOGGER.info("add record " + i + " : " + (values[i] == 0 ? "failed" : "success"));
//        }
//        LOGGER.info("Total students is " + jdbc.totalRecord());

        jdbc.save("Tran Thi A", "23");
    }
}
