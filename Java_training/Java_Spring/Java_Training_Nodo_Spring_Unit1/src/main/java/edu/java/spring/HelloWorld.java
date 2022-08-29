package edu.java.spring;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

public class HelloWorld {

    @Autowired(required = true)
    @Qualifier("helloJavaClazz2")
    private HelloClass clazz;

    public HelloWorld() {
    }

    public HelloWorld(String name, HelloClass clazz) {
        message = "From HelloWorld constructor:" + name + " : " + clazz.getMessage();
    }

    private final static Logger LOGGER = Logger.getLogger(HelloWorld.class);
    private String message;

    public void sayHello() {
        clazz.printMessage();
        LOGGER.info("From HelloWorld: " + message);
    }

    @Required
    public void setMessage(String message) {
        this.message = "Call From Setter: " + message;
    }

    public String getMessage() {
        return message;
    }

    public void setClazz(HelloClass clazz) {
        this.clazz = clazz;
    }

    public HelloClass getClazz() {
        return clazz;
    }
}
