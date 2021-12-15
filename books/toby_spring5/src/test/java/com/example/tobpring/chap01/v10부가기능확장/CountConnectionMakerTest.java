package com.example.tobpring.chap01.v10부가기능확장;

import com.example.tobpring.chap01.v6관계설정분리_주입.ConnectionMaker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CountConnectionMakerTest {

    @Test
    void extendFeature() throws SQLException, ClassNotFoundException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(DaoFactory.class);
        CountConnectionMaker connectionMaker = ac.getBean("connectionMaker", CountConnectionMaker.class);
        Assertions.assertThat(connectionMaker).isNotNull();
        connectionMaker.getConnection();
        connectionMaker.getConnection();
        connectionMaker.getConnection();
        Assertions.assertThat(connectionMaker.getCount()).isEqualTo(3);
    }

}