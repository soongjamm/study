package ch07.IoC_DI.c6_di_spring_autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Driver {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("c6_di_spring.xml");
        Car car = context.getBean("car", Car.class);
        System.out.println(car.getTireBrand());
    }
}
