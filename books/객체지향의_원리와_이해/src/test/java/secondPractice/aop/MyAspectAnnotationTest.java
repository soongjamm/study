package secondPractice.aop;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import secondPractice.aop.spring.Person;

class MyAspectAnnotationTest {
    @Test
    void aspect1() {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("aop02.xml");
        Person boy = ac.getBean("boy", Person.class);
        Person girl = ac.getBean("girl", Person.class);
        boy.run();
        girl.run();
    }
}