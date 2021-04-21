package com.soongjamm.startboot.designPattern.templateCallback.jdbcV1;

public class Client {
    public static void main(String[] args) {

        MyProgram program = new MyProgram();

        program.run(new JdbcAPI() {
            @Override
            public void connect() {
                System.out.println("conenct to mysql");
            }
        });

        System.out.println();

        program.run(new JdbcAPI() {
            @Override
            public void connect() {
                System.out.println("connect to oracle");
            }
        });
    }
}
