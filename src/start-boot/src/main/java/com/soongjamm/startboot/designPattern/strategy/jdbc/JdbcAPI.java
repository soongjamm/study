package com.soongjamm.startboot.designPattern.strategy.jdbc;

public interface JdbcAPI {

    void connect();

    void disconnect();
}
