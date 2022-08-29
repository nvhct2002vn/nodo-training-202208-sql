package edu.java.spring;

import org.springframework.beans.factory.DisposableBean;

import java.util.List;

public class HelloClass implements DisposableBean {
    private String message;

    private List<JavaClass> classes;

    private void release() {
        message = null;
        System.out.println("Message is null");
    }

    public HelloClass(int person) {
        message = "Form Constructor: Say hello to " + person + " person(s)!";
    }

    public HelloClass() {
        message = "Form Constructor: Say hello to everyone";
    }

    public HelloClass(HelloClass clazz) {
        message = clazz.message;
    }

    private void initMessage() {
        System.out.println("Calling init method...");
        message = "From init method: Say hello bean!";
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void printMessage() {
        System.out.println("Your Message: " + message);
    }

    @Override
    public void destroy() throws Exception {
        message = null;
        System.out.println("Message is null");
    }

    public String getMessage() {
        return message;
    }

    public List<JavaClass> getClasses() {
        return classes;
    }

    public void setClasses(List<JavaClass> classes) {
        this.classes = classes;
    }
}
