package com.example.tobpring.chap01.v3관심사분리_상속;

import java.sql.*;

public class KUserDao extends UserDao {
    @Override
    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.oracle.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost/toby", "kakao", "P@ssw0rd");
    }
}
