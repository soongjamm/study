package com.example.week4;

public class Stack {
    int[] datas;
    int pointer = -1; // empty

    public Stack(int size) {
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
