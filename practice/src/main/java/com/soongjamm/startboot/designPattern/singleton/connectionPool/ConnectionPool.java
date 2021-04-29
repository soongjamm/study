package com.soongjamm.startboot.designPattern.singleton.connectionPool;

public class ConnectionPool {
    private static ConnectionPool pool;

    private ConnectionPool() {

    }

    public static ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        System.out.println("return connection pool : " + pool);
        return pool;
    }
}
