package com.example.week4;

public class StackWithArray {
    int[] datas;
    int pointer = -1; // empty

    public StackWithArray(int size) {
        datas = new int[size];
    }

    public void push(int data) {
        pointer++;
        datas[pointer] = data;
    }

    public int pop() {
        int data = datas[pointer];
        pointer--;
        return data;
    }
}
