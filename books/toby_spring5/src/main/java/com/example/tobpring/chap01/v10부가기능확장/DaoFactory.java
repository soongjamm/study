package com.example.tobpring.chap01.v10부가기능확장;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {

    @Bean
    public ConnectionMaker connectionMaker() {
        return new CountConnectionMaker(realConnectionMaker());
    }

    @Bean
    public ConnectionMaker realConnectionMaker() {
        return new KConnectionMaker();
    }

}
