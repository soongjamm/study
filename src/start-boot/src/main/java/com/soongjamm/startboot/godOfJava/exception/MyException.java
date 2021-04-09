package com.soongjamm.startboot.godOfJava.exception;

// RuntimeException 이므로 try-catch 없이 throw 해도 컴파일 가능
// 그냥 Exception 상속했으면 컴파일 불가
public class MyException extends RuntimeException {
    public MyException(String message) {
        super(message);
    }
}
