package com.soongjamm.startboot.godOfJava.thread.object_method;

public class RunObjectThreads {
    public static void main(String[] args) {
        RunObjectThreads sample = new RunObjectThreads();
//        sample.checkThreadState2();
        sample.checkThreadState3();
    }

    private void checkThreadState2() {
        Object monitor = new Object();
        StateThread thread = new StateThread(monitor);
        try {
            System.out.println("thread state= " + thread.getState());
            thread.start();
            System.out.println("thread state(after start)= " + thread.getState());

            Thread.sleep(100); // thread 가 내부에서  wait() 할 시간 벌어주는 용
            System.out.println("thread state(after 0.1sec)= " + thread.getState()); // WAITING

            synchronized (monitor) {
                monitor.notify();
            }
            Thread.sleep(100);

            System.out.println("thread state(after notify)= " + thread.getState());

            thread.join();
            System.out.println("thread state(after join)= " + thread.getState());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkThreadState3() {
        Object monitor = new Object();
        StateThread thread1 = new StateThread(monitor);
        StateThread thread2 = new StateThread(monitor);
        try {
            System.out.println("thread state= " + thread1.getState());
            thread1.start();
            thread2.start();
            System.out.println("thread state(after start)= " + thread1.getState());

            Thread.sleep(100); // thread 가 내부에서  wait() 할 시간 벌어주는 용
            System.out.println("thread state(after 0.1sec)= " + thread1.getState()); // WAITING

            synchronized (monitor) {
//                monitor.notify();
//                monitor.notify();
                monitor.notifyAll();
            }
            Thread.sleep(100);

            System.out.println("thread state(after notify)= " + thread1.getState());
            System.out.println("thread state(after notify)= " + thread2.getState());

            thread1.join();
            System.out.println("thread state(after join)= " + thread1.getState());

            thread2.join();
            System.out.println("thread state(after join)= " + thread2.getState());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
