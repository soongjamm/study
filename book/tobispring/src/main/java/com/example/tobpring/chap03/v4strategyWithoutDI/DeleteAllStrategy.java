package com.example.tobpring.chap03.v4strategyWithoutDI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAllStrategy implements StatementStrategy {

    @Override
    public PreparedStatement makeStrategy(Connection c) throws SQLException {
        return c.prepareStatement("select * from users where id = ?");
    }
}
