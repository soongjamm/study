package com.soongjamm.startboot.designPattern.strategy.jdbc;

public class Client {
    public static void main(String[] args) {

        MyProgram program = new MyProgram();

        program.run(new MySqlDriver());

        System.out.println();

        program.run(new OracleDriver());
    }
}
