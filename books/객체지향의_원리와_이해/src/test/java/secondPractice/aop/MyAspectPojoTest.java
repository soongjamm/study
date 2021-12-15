package secondPractice.aop;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import secondPractice.aop.pojowithXML.Person;

class MyAspectPojoTest {

    @Test
    void aspectPojo() {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("aop03.xml");
        Person boy = ac.getBean("boy", Person.class);
        Person girl = ac.getBean("girl", Person.class);
        boy.run();
        girl.run();
    }
}