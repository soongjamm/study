package com.example.tobpring.chap01.v6관계설정분리_주입;

import com.example.tobpring.chap01.v1초난감dao.User;

import java.sql.SQLException;

public class Client {
    // UserDao와 ConnectionMaker 사이 관계설정을 클라이언트가 해주게 되었다.
    // 그러나 클라이언트가 갑자기 생성의 책임을 맡아버렸다.
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionMaker connectionMaker = new KConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        userDao.insert(new User());
    }
}
