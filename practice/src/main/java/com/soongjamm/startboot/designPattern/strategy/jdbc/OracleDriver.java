package com.soongjamm.startboot.designPattern.strategy.jdbc;

public class OracleDriver implements JdbcAPI {
    @Override
    public void connect() {
        System.out.println("oracle connected!");
    }

    @Override
    public void disconnect() {
        System.out.println("oracle down");
    }
}
