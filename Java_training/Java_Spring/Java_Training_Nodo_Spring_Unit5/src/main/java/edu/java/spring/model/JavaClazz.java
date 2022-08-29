package edu.java.spring.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "clazz")
public class JavaClazz {

    private List<Student> studentList;

    public JavaClazz(List<Student> studentList) {
        this.studentList = studentList;
    }

    public JavaClazz() {
    }

    @XmlElements(@XmlElement(name = "student", type = Student.class))
    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
