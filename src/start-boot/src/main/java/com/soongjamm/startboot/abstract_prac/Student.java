package com.soongjamm.startboot.abstract_prac;

public class Student extends Person {
    @Override
    public void sleep() {
        System.out.println("책상에 엎드려 잔다.");
    }

    @Override
    public void sleep(String msg) {
        System.out.println(msg);
    }
}
