package com.example.LinkedList;

import java.util.Objects;

public class ListNode {

    protected ListNode next;
    protected int data;

    public ListNode() {
    }

    public ListNode(int data) {
        this.data = data;
    }

    // before = 대상 인덱스를 가리키고 있는 노드
    static public ListNode add(ListNode head, ListNode nodeToAdd, int position) {
        validatePositionInput(position);

        ListNode before = head;
        while (position > 0) {
            validateNextNode(before.next);
            before = before.next;
            position--;
        }

        if (!Objects.isNull(before.next)) { // 삽입하려는 인덱스가 비어있지 않은 경우
            nodeToAdd.next = before.next;
        }
        before.next = nodeToAdd;

        return nodeToAdd;
    }

    public static ListNode remove(ListNode head, int positionToRemove) {
        validatePositionInput(positionToRemove);

        ListNode before = head;
        while (positionToRemove > 0) {
            validateNextNode(before.next);
            before = before.next;
            positionToRemove--;
        }

        ListNode removed =  before.next;
        before.next = before.next.next;

        return removed;
    }

    static private void validatePositionInput(int position) {
        if (position < 0) {
            throw new IllegalArgumentException("포지션은 0이상 이어야 합니다.");
        }
    }

    static private void validateNextNode(ListNode nextNode) {
        if (Objects.isNull(nextNode)) {
            throw new NullPointerException("포지션이 리스트의 길이를 초과합니다.");
        }
    }

    public static boolean contains(ListNode head, ListNode nodeToCheck) {

        ListNode before = head;
        while (!Objects.isNull(before.next)) {
            if (before.next.equals(nodeToCheck)) {
                return true;
            }
            before = before.next;
        }

        return false;
    }
}
