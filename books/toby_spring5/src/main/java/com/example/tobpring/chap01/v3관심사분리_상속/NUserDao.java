package com.example.tobpring.chap01.v3관심사분리_상속;

import java.sql.*;

public class NUserDao extends UserDao{
    @Override
    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost/toby", "naver", "P@ssw0rd");
    }
}
