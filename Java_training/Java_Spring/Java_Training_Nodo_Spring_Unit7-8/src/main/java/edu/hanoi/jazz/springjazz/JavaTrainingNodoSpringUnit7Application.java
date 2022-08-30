package edu.hanoi.jazz.springjazz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:config.xml")
public class JavaTrainingNodoSpringUnit7Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JavaTrainingNodoSpringUnit7Application.class, args);
        context.start();
    }

}
