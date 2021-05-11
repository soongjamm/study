package com.example.tobpring.chap03.v8seperateCallbackSql;

import com.example.tobpring.chap03.v4strategyWithoutDI.StatementStrategy;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcContext {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void executeSql(String sql) {
        workingWithStrategy(new StatementStrategy() {
            @Override
            public PreparedStatement makeStrategy(Connection c) throws SQLException {
                return c.prepareStatement(sql);
            }
        });
    }

    private void workingWithStrategy(StatementStrategy strategy) {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = dataSource.getConnection();

            ps = strategy.makeStrategy(c);
            ps.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
