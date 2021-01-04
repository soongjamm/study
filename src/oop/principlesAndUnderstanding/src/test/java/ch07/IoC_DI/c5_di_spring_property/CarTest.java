package ch07.IoC_DI.c5_di_spring_property;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "c5_di_spring.xml")
class CarTest {
    //    @Autowired
    //    Car car;
    ApplicationContext context = new ClassPathXmlApplicationContext("c5_di_spring.xml");
    Car car = context.getBean("car", Car.class);

    @Test
    public void 자동차_코리아타이어_장착_타이어브랜드_테스트() {
        assertEquals("장착된 타이어: 코리아 타이어", car.getTireBrand());
    }
}