package com.example.tobpring.chap01.v9의존관계검색;

import com.example.tobpring.chap01.v6관계설정분리_주입.ConnectionMaker;
import com.example.tobpring.chap01.v6관계설정분리_주입.KConnectionMaker;
import com.example.tobpring.chap01.v6관계설정분리_주입.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class DaoFactory {

    @Bean
    public ConnectionMaker connectionMaker() {
        return new KConnectionMaker();
    }

    @Bean
    public UserDao userDao() throws SQLException, ClassNotFoundException {
        return new UserDao(connectionMaker());
    }

    public UserDao somethingDao() throws SQLException, ClassNotFoundException {
        return new UserDao(connectionMaker()); // User X 다른걸로 상상
    }

    public UserDao lastDao() throws SQLException, ClassNotFoundException {
        return new UserDao(connectionMaker()); // User X 다른걸로 상상
    }

}
