package com.example.tobpring.chap01.v1초난감dao;

import java.sql.*;

public class UserDao {

    // 여러 관심사가 집중되어있다.
    public User get(String id) throws ClassNotFoundException, SQLException {
        // DB 연결
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/toby", "soongjamm", "P@ssw0rd");
        PreparedStatement stmt = c.prepareStatement("select * from users where id = ?");
        stmt.setString(1, id);

        // 메인 로직
        ResultSet rs = stmt.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setPassword(rs.getString("password"));

        rs.close();
        stmt.close();
        c.close();

        return user;
    }

    // get과 동일한 코드가 반복된다.
    public User insert(User user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/toby", "soongjamm", "P@ssw0rd"
        );
        PreparedStatement stmt = c.prepareStatement("insert into users(id, password) values (?, ?)");
        stmt.setString(1, user.getId());
        stmt.setString(2, user.getPassword());
        stmt.executeUpdate();

        stmt.close();
        c.close();
        return user;
    }

}
