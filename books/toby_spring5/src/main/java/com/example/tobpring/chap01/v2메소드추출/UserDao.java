package com.example.tobpring.chap01.v2메소드추출;

import com.example.tobpring.chap01.v1초난감dao.User;

import java.sql.*;

public class UserDao {

    // 관심사의 분리 1. 공통 부분을 메소드로 추출.
    // 커넥션 정보에 변화가 발생할 수 있었는데, 그 경우 여기만 수정하면 된다.
    // 그러나 UserDao를 수정할 수 없는 경우 또는 너무 빈번한 경우 매번 수정해줘야 한다.
    // --> 변경에 유연하지 못하다.
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost/toby", "soongjamm", "P@ssw0rd");
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();
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
        Connection c = getConnection();
        PreparedStatement stmt = c.prepareStatement("insert into users(id, password) values (?, ?)");
        stmt.setString(1, user.getId());
        stmt.setString(2, user.getPassword());
        stmt.executeUpdate();

        stmt.close();
        c.close();
        return user;
    }

}
