package com.example.week4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackWithArrayTest {

    static StackWithArray stackWithArray;

    @BeforeAll
    static void initialize() {
        stackWithArray = new StackWithArray(10);
    }


    @Test
    void pushTest() {
        for (int i = 0; i<10; i++) {
            stackWithArray.push(i);
        }
        assertThrows(IndexOutOfBoundsException.class, () -> stackWithArray.push(11));
    }

    @Test
    void popTest() {
        stackWithArray.push(1);
        assertEquals(1, stackWithArray.pop());
        assertThrows(IndexOutOfBoundsException.class, () -> stackWithArray.pop());
    }
}