package com.soongjamm.startboot.designPattern.templateCallback.jdbcV2.jdbcV1;

public class Client {
    public static void main(String[] args) {

        MyProgram program = new MyProgram();

        program.run("mysql");

        System.out.println();

        program.run("oracle");
    }
}
