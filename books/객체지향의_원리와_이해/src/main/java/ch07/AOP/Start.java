package ch07.AOP;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("aop01.xml");

        Person boy = context.getBean("boy", Person.class);

        boy.runSomething();
    }
}
