package com.soongjamm.startboot.godOfJava.thread;

import lombok.SneakyThrows;

public class RunSupportThreads {
    public static void main(String[] args) {
        RunSupportThreads sample = new RunSupportThreads();
        sample.checkThreadState1();
//        sample.checkJoin();
    }

    @SneakyThrows
    public void checkThreadState1() {
        SleepThread thread = new SleepThread(2000);
        System.out.println("thread state=" + thread.getState());
        thread.start();
        System.out.println("thread state(after start)=" + thread.getState());

        Thread.sleep(100); // 쓰레드가 내부에서 sleep 할때까지 시간벌기용
        System.out.println("thread state(after 1sec)=" + thread.getState()); // 정해진 시간만큼 sleep 이라 TIMED_WAITING

        thread.join(); // main 이 thread 보다 먼저 종료되면 결과를 제대로 못보니까 기다려주는 용 (끝날때 까지 무한정 기다린다)
        thread.interrupt(); // 이미 종료된 thread 를 인터럽트해서 무반응
        System.out.println("thread state(after join)=" + thread.getState());
        System.out.println("============================================================");
    }

    @SneakyThrows
    public void checkJoin() {
        SleepThread thread = new SleepThread(2000);
        thread.start();
        System.out.println("is alive? - " + thread.isAlive()); // true
        System.out.println("have access - ok if not throw SecurityException"); thread.checkAccess(); // true
        thread.join(500); // thread 가 끝나기를 500 ms 만큼만 기다린다.
//        thread.join(2002); // 2000이상 이면 이미 thread 가 종료되었다.  // 이미 종료된 thread 는 인터셉트해도 아무 반응이 없다.
        thread.interrupt(); // thread 가 아직 안끝났는데 인터럽트해서 InterruptedException 발생
        System.out.println("is interrupted? - " + thread.isInterrupted()); // true
        System.out.println("thread state(after join) = " + thread.getState());
        System.out.println("============================================================");

    }

}
