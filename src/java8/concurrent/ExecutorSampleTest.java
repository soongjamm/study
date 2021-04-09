package java8.concurrent;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExecutorSampleTest {

    ExecutorSample sample = new ExecutorSample();

    @Test
    public void singleThreadExecutorTest() {
        boolean b = sample.singleThreadExecutor();
        assertEquals(b, true);
    }

    @Test
    public void newFixedThreadPool_And_awaitTermination_Test() {
        boolean b = sample.threadPool();
        assertEquals(b, true);
    }

    @Test
    public void scheduled_test() {
        sample.schedule();
    }

}