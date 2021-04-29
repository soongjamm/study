package com.soongjamm.startboot.godOfJava.exception;

public class ExceptionPractice {
    public static void main(String[] args) {
        ExceptionPractice e = new ExceptionPractice();
        e.occurException();
    }

    public void occurException() {
        throw new MyException("exception!");
    }
}
