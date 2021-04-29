package com.soongjamm.startboot.practice.thread.group;

import com.soongjamm.startboot.godOfJava.thread.SleepThread;
import lombok.SneakyThrows;

public class ThreadGroupExample {
    public static void main(String[] args) {
        ThreadGroupExample threadGroupExample = new ThreadGroupExample();
        threadGroupExample.run();
    }

    @SneakyThrows
    // 실행중인 쓰레드만 그룹에 포함된다.
    private void run() {
        SleepThread sleepThread1 = new SleepThread(1000);
        SleepThread sleepThread2 = new SleepThread(2000);
        SleepThread sleepThread3 = new SleepThread(3000);

        ThreadGroup myGroup = new ThreadGroup("my");
        Thread thread1 = new Thread(myGroup, sleepThread1);
        Thread thread2 = new Thread(myGroup, sleepThread2);
        Thread thread3 = new Thread(myGroup, sleepThread3);
        thread1.start();
//        thread1.start(); // 이미 실행중인 쓰레드는 실행할 수 없다
        thread2.start();
        thread3.start();
        System.out.println("thread1.getName()" + thread1.getName());
        System.out.println("thread2.getName()" + thread2.getName());
        System.out.println("thread3.getName()" + thread3.getName());

        myGroup.list();
        Thread[] threads = new Thread[4];
        myGroup.enumerate(threads);
        for (Thread thread : threads) {
            if (thread != null) {
                System.out.println("call " + thread.getName() + " " + thread.getState() + " " + thread.getThreadGroup());
            }
        }
        System.out.println(myGroup.activeGroupCount());
    }
}
