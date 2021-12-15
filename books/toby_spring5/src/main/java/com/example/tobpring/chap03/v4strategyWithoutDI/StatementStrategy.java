package com.example.tobpring.chap03.v4strategyWithoutDI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
    PreparedStatement makeStrategy(Connection c) throws SQLException;
}
