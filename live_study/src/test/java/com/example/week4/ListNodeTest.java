package com.example.week4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListNodeTest {

    @Test
    @DisplayName("노드를 추가한다.")
    void addNode() {
        ListNode head = new ListNode();
        ListNode nodeToAdd = new ListNode(1);
        ListNode nodeToAdd2 = new ListNode(2);

        // 0번째 인덱스는 head가 가리키는 노드다.
        ListNode.add(head, nodeToAdd, 0);
        assertEquals(nodeToAdd, head.next);

        // 0번째에 새로운 노드를 삽입한 경우, 새로운 노드는 기존 노드를 가리키고 있어야 한다.
        // head -> nodeToAdd2 -> nodeToAdd
        ListNode.add(head, nodeToAdd2, 0);
        assertEquals(head.next, nodeToAdd2);
        assertEquals(nodeToAdd2.next, nodeToAdd);

    }

    @Test
    @DisplayName("노드를 삭제한다.")
    void removeNode() {
        ListNode head = new ListNode();
        ListNode nodeToAdd = new ListNode(1);

        ListNode.add(head, nodeToAdd, 0);
        assertEquals(head.next, nodeToAdd);

        // remove하면 위에서 add했던 노드가 나와야 한다.
        assertEquals(nodeToAdd, ListNode.remove(head, 0));
        assertEquals(head.next, null);
    }

    @Test
    @DisplayName("노드가 존재하는지 검사한다.")
    void containsNode() {
        ListNode head = new ListNode();
        ListNode nodeToAdd = new ListNode(1);
        ListNode nodeToCheck = nodeToAdd;

        ListNode.add(head, nodeToAdd, 0);

        // 위에서 추가한 노드는 존재해야 하고, 막 new로 생성한 노드는 존재하지 않아야 함
        assertTrue(ListNode.contains(head, nodeToCheck));
        assertTrue(!ListNode.contains(head, new ListNode()));
    }

    @Test
    @DisplayName("예외처리를 테스트한다.")
    void exception() {
        // 인덱스로 리스트 범위를 벗어나는 값을 입력한
        ListNode head = new ListNode();
        ListNode nodeToAdd = new ListNode(1);

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> ListNode.add(head, nodeToAdd, -100)),
                () -> assertThrows(NullPointerException.class, () -> ListNode.add(head, nodeToAdd, 100)),
                () -> assertThrows(IllegalArgumentException.class, () -> ListNode.remove(head, -100)),
                () -> assertThrows(NullPointerException.class, () -> ListNode.remove(head, 100))
        );
    }
}