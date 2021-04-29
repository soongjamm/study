package com.example.week4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueWithListNodeTest {
    QueueWithListNode queue;

    @BeforeEach
    void initialize() {
        queue = new QueueWithListNode();
    }

    @Test
    void enqueueTest() {
        for(int i=0; i<10; i++) {
            queue.enqueue(10);
        }
        assertDoesNotThrow(() -> queue.enqueue(11));
    }

    @Test
    void dequeueTest() {
        assertThrows(NullPointerException.class, () -> queue.dequeue());
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
    }
}