package com.soongjamm.startboot.designPattern.strategy.jdbc;

public class MySqlDriver implements JdbcAPI {
    @Override
    public void connect() {
        System.out.println("mysql connected!");
    }

    @Override
    public void disconnect() {
        System.out.println("mysql disconnected");
    }
}
