package secondPractice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import secondPractice.di.xml.Car;
import secondPractice.di.xml.CarAutowired;
import secondPractice.di.xml.Tire;

class CarTest {
    @Test
    void xml() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("di_tire.xml");
        Car car = ac.getBean("car", Car.class);
        Tire tire = ac.getBean("tire", Tire.class);
        car.setTire(tire);

        System.out.println(car);
    }

    @Test
    void xmlPropertyInjection() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("di_tire.xml");
        Car car = ac.getBean("car", Car.class);
        System.out.println(car);
    }

    @Test
    void autowired() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("di_tire_annotation.xml");
        CarAutowired car = ac.getBean("car", CarAutowired.class);
        System.out.println(car);
    }

    @Test
    void autowired2() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("di_tire_annotation.xml");
        CarAutowired car = ac.getBean(CarAutowired.class);
        System.out.println(car);
    }
}