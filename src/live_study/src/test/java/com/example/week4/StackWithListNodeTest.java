package com.example.week4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackWithListNodeTest {

    static StackWithListNode stack = new StackWithListNode();

    @Test
    void pushTest() {
        for (int i = 0; i < 10; i++) {
            stack.push(i);
            System.out.println(i + " 삽입");
        }
    }

    @Test
    void popTest() {
        stack.push(1);
        assertEquals(1, stack.pop());
        assertThrows(NullPointerException.class, () -> stack.pop());
    }
}