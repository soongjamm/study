package com.example.week4;

public class QueueWithListNode {
    static final int EMPTY = -1;
    ListNode head = new ListNode();
    int tail = EMPTY;


    public void enqueue(int data) {
        tail++;
        ListNode.add(head, new ListNode(data), tail);
    }

    public int dequeue() {
        ListNode removed = ListNode.remove(head, 0);
        return removed.data;
    }
}
