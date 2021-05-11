package com.example.tobpring.chap01.v6관계설정분리_주입;

import com.example.tobpring.chap01.v1초난감dao.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    // 관심사의 분리 - 관계 설정 책임의 분리
    // UserDao와 UserDao가 사용할 ConnectionMaker 사이의 관계를 설정해주는
    // connectionMaker = new KConnectionMaker(); 를 제거한다.
    // 오브젝트와 오브젝트 사이의 관계를 런타임에 결정하게 된다.
    // 클라이언트가 관계 설정의 책임을 두기 좋은 곳이다.

    // 그러나.. 클라이언트가 문제다.

    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.getConnection();
        PreparedStatement stmt = c.prepareStatement("select * from users where id = ?");
        stmt.setString(1, id);

        ResultSet rs = stmt.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setPassword(rs.getString("password"));

        // 연결 해제
        rs.close();
        stmt.close();
        c.close();

        return user;
    }

    public User insert(User user) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.getConnection();
        PreparedStatement stmt = c.prepareStatement("insert into users(id, password) values (?, ?)");
        stmt.setString(1, user.getId());
        stmt.setString(2, user.getPassword());
        stmt.executeUpdate();

        stmt.close();
        c.close();
        return user;
    }

}
