package com.example.tobpring.chap01.v5인터페이스사용;

import com.example.tobpring.chap01.v1초난감dao.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    // 관심사의 분리 - 인터페이스 활용 (ConnectionMaker)

    ConnectionMaker connectionMaker;

    // 그런데 여기에 구체적인 클래스 이름이 나온다.
    // 인터페이스를 둬서 해당 타입의 레퍼런스는 다 올 수 있기때문에 기능확장은 가능해졌지만
    // 이전처럼 Dao를 수정하지 않고는 커넥션을 갈아낄 수 없다.
    public UserDao() {
        connectionMaker = new KConnectionMaker();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.getConnection();
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
        Connection c = connectionMaker.getConnection();
        PreparedStatement stmt = c.prepareStatement("insert into users(id, password) values (?, ?)");
        stmt.setString(1, user.getId());
        stmt.setString(2, user.getName());
        stmt.executeUpdate();

        stmt.close();
        c.close();
        return user;
    }

}
