package com.example.tobpring.chap01.v7_IoC생성팩토리;

import com.example.tobpring.chap01.v6관계설정분리_주입.ConnectionMaker;
import com.example.tobpring.chap01.v6관계설정분리_주입.KConnectionMaker;
import com.example.tobpring.chap01.v6관계설정분리_주입.UserDao;

import java.sql.SQLException;

public class DaoFactory {
    // 메소드추출
    private ConnectionMaker getConnectionMaker() {
        return new KConnectionMaker();
    }

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
