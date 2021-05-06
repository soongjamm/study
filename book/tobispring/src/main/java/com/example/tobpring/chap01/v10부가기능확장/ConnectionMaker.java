package com.example.tobpring.chap01.v10부가기능확장;

import java.sql.SQLException;

public interface ConnectionMaker {
    default Connection getConnection() throws ClassNotFoundException, SQLException {
        return new Connection();
    }
}
