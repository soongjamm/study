package com.example.tobpring.chap01.v7_IoC생성팩토리;

import com.example.tobpring.chap01.v1초난감dao.User;
import com.example.tobpring.chap01.v6관계설정분리_주입.UserDao;

import java.sql.SQLException;

public class Client {
    // 생성책임을 Factory에게 넘긴다. (디자인패턴 팩토리메소드패턴과는 다르다. 디자인패턴은 어떻게 만들지고, 이건 어떻게 사용할지의 문제)
    // (그니까 이건 단지 생성과 사용자쪽의 역할과 책임을 분리하는것 뿐이지, 패턴이 아님)
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao dao = new DaoFactory().userDao();
        User user = dao.get("1");
    }
}
