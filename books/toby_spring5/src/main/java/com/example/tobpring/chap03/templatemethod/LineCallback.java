package com.example.tobpring.chap03.templatemethod;

public interface LineCallback<T> {
    T doSomethingWithLine(String path, T value);
}
