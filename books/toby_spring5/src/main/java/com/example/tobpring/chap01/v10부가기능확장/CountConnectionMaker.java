package com.example.tobpring.chap01.v10부가기능확장;


import java.sql.SQLException;

public class CountConnectionMaker implements ConnectionMaker {

    private int count = 0; // 커넥션 생성 횟수

    private ConnectionMaker realConnectionMaker;

    public CountConnectionMaker(ConnectionMaker realConnectionMaker) {
        this.realConnectionMaker = realConnectionMaker;
    }

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        count++;
        System.out.println("realConnectionMaker.getConnection()");
        return realConnectionMaker.getConnection();
    }

    public int getCount() {
        return count;
    }
}
