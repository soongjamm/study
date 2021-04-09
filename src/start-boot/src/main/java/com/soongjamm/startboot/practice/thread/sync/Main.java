package com.soongjamm.startboot.practice.thread.sync;

import lombok.SneakyThrows;

public class Main {
    public void run() {
        Object lock = new Object();
//        calculatorMethodSync();
//        interruptSleepingThread();
        waitAndNotify();
    }

    @SneakyThrows
    private void waitAndNotify() {
        Object lock = new Object();
        ThreadWithLock thread = new ThreadWithLock(lock);
        thread.start();
        System.out.println("right after start thread : " + thread.getState());
        Thread.sleep(200);
        System.out.println("'before notify' in main. (thread is on 'wait()' )expected-'WAITING' : " + thread.getState());
        synchronized (lock) { // lock은  main 과 thread가 공유하고 있다.
            lock.notifyAll();
        }
        System.out.println("'after notify' in main. expected-'BLOCKED' : " + thread.getState());
        Thread.sleep(200);
        System.out.println("thread is on 'sleep()' now. expected-'TIME_WAITING' : " + thread.getState());
        thread.join();
        System.out.println("after join() is done. expected-'TERMINATED' : " + thread.getState());
    }

    @SneakyThrows
    private void interruptSleepingThread() {
        System.out.println(Thread.activeCount());

        ARunnableJustSleep aRunnableJustSleep = new ARunnableJustSleep();
        Thread thread = new Thread(aRunnableJustSleep);
        thread.start();
        System.out.println(thread.getState()); // runnable
        Thread.sleep(300);
        System.out.println(thread.getState()); // sleep() -> time_waiting
//        thread.join(); //  있으면 interrupt 무시
        System.out.println("is alive? " + thread.isAlive()); // 쓰레드가 끝나야 join()이 끝나므로 flase
        thread.interrupt();
        System.out.println("isInterrupted "+thread.isInterrupted());
        Thread.sleep(300);
        System.out.println(thread.getState()); // (interrupt 예외발생 후) terminated
    }

    @SneakyThrows
    private void calculatorMethodSync() {
        Calculator calculator = new Calculator();
        ThreadMethodSync threadMethodSync1 = new ThreadMethodSync(calculator);
        ThreadMethodSync threadMethodSync2 = new ThreadMethodSync(calculator);
        System.out.println(threadMethodSync1.getState()); // new
        threadMethodSync1.start();
        System.out.println(threadMethodSync1.getState()); // runnable
        threadMethodSync2.start();
        System.out.println(threadMethodSync1.getState()); // runnable
        threadMethodSync1.join();
        System.out.println(threadMethodSync1.getState()); // terminated
        threadMethodSync2.join();
        System.out.println(calculator);
    }

    public static void main(String[] args) {
//        Thread.dumpStack();
        Main main = new Main();
        main.run();
    }

}
