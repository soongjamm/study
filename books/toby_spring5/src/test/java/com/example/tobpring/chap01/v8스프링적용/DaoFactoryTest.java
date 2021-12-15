package com.example.tobpring.chap01.v8스프링적용;

import com.example.tobpring.chap01.v6관계설정분리_주입.UserDao;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class DaoFactoryTest {

    @Test
    void ioc() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao userDao = ac.getBean("userDao", UserDao.class);
        Assertions.assertThat(userDao).isNotNull();
    }

}