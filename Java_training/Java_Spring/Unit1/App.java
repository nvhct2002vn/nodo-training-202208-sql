import edu.java.spring.HelloClass;
import edu.java.spring.HelloWorld;
import edu.java.spring.JavaClass;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private final static Logger LOGGER = Logger.getLogger(HelloWorld.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//        HelloClass obj = (HelloClass) context.getBean("helloJavaClazz");
//        obj.printMessage();

//        HelloClass obj2 = (HelloClass) context.getBean("helloJavaClazz2");
//        obj2.printMessage();

//        ClassPathXmlApplicationContext context2 = new ClassPathXmlApplicationContext("beans.xml");
//        context2.registerShutdownHook();

//        HelloWorld helloWorld = (HelloWorld) context.getBean("helloWorld");
//        helloWorld.sayHello();

//        JavaClass clazz = (JavaClass) context.getBean("jee01");
//        LOGGER.info("Map Implement is " + clazz.getStudents().getClass());
//        LOGGER.info("There are " + clazz.getStudents().size() + " in the class");
//
//        HelloClass clazz1 = (HelloClass) context.getBean("helloJavaClazz2");
//        clazz1.printMessage();

        context.start();
    }

}
