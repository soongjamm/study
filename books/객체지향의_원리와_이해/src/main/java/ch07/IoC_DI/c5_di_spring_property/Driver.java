package ch07.IoC_DI.c5_di_spring_property;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Driver {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("c5_di_spring.xml");
        Car car = context.getBean("car", Car.class);
//        Tire tire = context.getBean("tire", Tire.class);

//        car.setTire(tire);

        System.out.println(car.getTireBrand());
    }
}
