package com.soongjamm.startboot.designPattern.templateCallback.jdbcV2.jdbcV1;

public class MyProgram {
    public void run(String driver) {
        System.out.println("프로그램 시작... 이것저것 do something");
        JdbcAPI jdbcAPI = loadDriver(driver);
        jdbcAPI.connect();
    }

    private JdbcAPI loadDriver(String driver) {
        return () -> System.out.println("connect to " + driver);
//                return new JdbcAPI() {
//            @Override
//            public void connect() {
//                System.out.println("connect to "+ driver);
//            }
//        };
    }
}
