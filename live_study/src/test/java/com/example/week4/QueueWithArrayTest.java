package com.example.week4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueWithArrayTest {

    QueueWithArray queue;

    @BeforeEach
    void initialize() {
        queue = new QueueWithArray(10);
    }

    @Test
    void enqueueTest() {
        for(int i=0; i<10; i++) {
            queue.enqueue(10);
        }
        assertThrows(IndexOutOfBoundsException.class, () -> queue.enqueue(11));
    }

    @Test
    void dequeueTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> queue.dequeue());
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
    }
}