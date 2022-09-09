package edu.hanoi.spring.springwebsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.hanoi.spring.springwebsocket",
        "edu.hanoi.spring.springwebsocket.controller"})
public class JavaTrainingNodoSpringUnit13Application {

    public static void main(String[] args) {
        SpringApplication.run(JavaTrainingNodoSpringUnit13Application.class, args);
    }

}
