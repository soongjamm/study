package com.soongjamm.startboot.designPattern.singleton.connectionPool;

public class Client {
    public static void main(String[] args) {

        // new ConnectionPool();

        ConnectionPool instance = ConnectionPool.getInstance();
        ConnectionPool instance2 = ConnectionPool.getInstance();
        ConnectionPool instance3 = ConnectionPool.getInstance();

        System.out.println(instance == instance2);
        System.out.println(instance2 == instance3);
        System.out.println(instance == instance3);

        System.out.println(instance);
    }
}
