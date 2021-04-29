package com.example.week4;

public class StackWithListNode {

    private ListNode head = new ListNode();
    private int pointer = -1;

    public void push(int data) {
        pointer++;
        ListNode.add(head, new ListNode(data), pointer);
    }

    public int pop() {
        ListNode removed = ListNode.remove(head, pointer);
        pointer--;

        return removed.data;

    }
}
