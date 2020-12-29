package com.example.week4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    static Stack stack;

    @BeforeAll
    static void initialize() {
        stack = new Stack(10);
    }


    @Test
    void pushTest() {
        for (int i = 0; i<10; i++) {
            stack.push(i);
        }
        assertThrows(IndexOutOfBoundsException.class, () -> stack.push(11));
    }

    @Test
    void popTest() {
        stack.push(1);
        assertEquals(1, stack.pop());
        assertThrows(IndexOutOfBoundsException.class, () -> stack.pop());
    }
}