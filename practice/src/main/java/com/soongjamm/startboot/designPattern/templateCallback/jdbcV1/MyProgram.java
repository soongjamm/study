package com.soongjamm.startboot.designPattern.templateCallback.jdbcV1;

public class MyProgram {
    public void run(JdbcAPI driver) {
        System.out.println("프로그램 시작... 이것저것 do something");
        driver.connect();
    }
}
