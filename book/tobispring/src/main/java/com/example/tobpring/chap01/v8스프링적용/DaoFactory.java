package com.example.tobpring.chap01.v8스프링적용;

import com.example.tobpring.chap01.v6관계설정분리_주입.ConnectionMaker;
import com.example.tobpring.chap01.v6관계설정분리_주입.KConnectionMaker;
import com.example.tobpring.chap01.v6관계설정분리_주입.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class DaoFactory {

    @Bean
    public ConnectionMaker getConnectionMaker() {
        return new KConnectionMaker();
    }

    @Bean
    public UserDao userDao() throws SQLException, ClassNotFoundException {
        return new UserDao(getConnectionMaker());
    }

    public UserDao somethingDao() throws SQLException, ClassNotFoundException {
        return new UserDao(getConnectionMaker()); // User X 다른걸로 상상
    }

    public UserDao lastDao() throws SQLException, ClassNotFoundException {
        return new UserDao(getConnectionMaker()); // User X 다른걸로 상상
    }

}
