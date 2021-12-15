package com.example.tobpring.chap03.v5strategyWithDI;

import com.example.tobpring.chap01.v1초난감dao.User;
import com.example.tobpring.chap03.v4strategyWithoutDI.StatementStrategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStrategy implements StatementStrategy {
    private User user;

    public AddStrategy(User user) {
        this.user = user;
    }

    @Override
    public PreparedStatement makeStrategy(Connection c) throws SQLException {
        PreparedStatement ps = c.prepareStatement("insert into users(id, password) values(?, ?) ");
        ps.setString(1, user.getId());
        ps.setString(2, user.getPassword());
        return ps;
    }
}
