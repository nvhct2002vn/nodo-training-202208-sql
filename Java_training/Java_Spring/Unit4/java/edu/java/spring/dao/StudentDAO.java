package edu.java.spring.dao;

import edu.java.spring.model.Student;

import java.util.List;

public interface StudentDAO {
    public void insert(Student student);

    public List<Student> list();

    public void delete(Integer id);

    public Student get(Integer id);

    public void update(Student student);

    public List<Student> getUserByName(String name);
}
