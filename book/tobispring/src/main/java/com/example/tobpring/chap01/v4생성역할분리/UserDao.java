package com.example.tobpring.chap01.v4생성역할분리;

import com.example.tobpring.chap01.v1초난감dao.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    // 관심사의 분리 - Connection을 생성하는 ConnectionMaker를 생성해 관심사를 분리

    SimpleConnectionMaker simpleConnectionMaker;

    // 관심사는 분리했지만, 커넥션을 바꾸려면 UserDao를 수정해야하는 상황이 다시 돌아왔다.
    // 고객이 커넥션 기능을 확장하기 어려워졌다.
    public UserDao() {
        simpleConnectionMaker = new SimpleConnectionMaker();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = simpleConnectionMaker.getConnection();
        PreparedStatement stmt = c.prepareStatement("select * from users where id = ?");
        stmt.setString(1, id);

        ResultSet rs = stmt.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("password"));

        // 연결 해제
        rs.close();
        stmt.close();
        c.close();

        return user;
    }

    public User insert(User user) throws ClassNotFoundException, SQLException {
        Connection c = simpleConnectionMaker.getConnection();
        PreparedStatement stmt = c.prepareStatement("insert into users(id, password) values (?, ?)");
        stmt.setString(1, user.getId());
        stmt.setString(2, user.getName());
        stmt.executeUpdate();

        stmt.close();
        c.close();
        return user;
    }

}
