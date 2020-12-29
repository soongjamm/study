package com.example.week4;

public class QueueWithArray {
    static final int EMPTY = -1;
    int[] datas;
    int tail = EMPTY;

    public QueueWithArray(int size) {
        datas = new int[size];
    }

    public void enqueue(int data) {
        tail++;
        datas[tail] = data;
    }

    public int dequeue() {
        if (tail == EMPTY) {
            throw new IndexOutOfBoundsException("큐에 데이터가 없습니다.");
        }
        int data = datas[0];
        for(int i=1; i<=tail; i++) {
            datas[i-1] = datas[i];
        }
        return data;
    }
}
