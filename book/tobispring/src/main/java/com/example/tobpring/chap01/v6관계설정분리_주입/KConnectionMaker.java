package com.example.tobpring.chap01.v6관계설정분리_주입;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KConnectionMaker implements ConnectionMaker {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.oracle.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost/toby", "kakao", "P@ssw0rd");
    }
}
