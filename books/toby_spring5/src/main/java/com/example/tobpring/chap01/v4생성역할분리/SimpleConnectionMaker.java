package com.example.tobpring.chap01.v4생성역할분리;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimpleConnectionMaker {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.oracle.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost/toby", "kakao", "P@ssw0rd");
    }
}
