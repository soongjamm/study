package com.soongjamm.startboot.godOfJava.thread;

public class RunThreads {
    public static void main(String[] args) {
        RunThreads threads = new RunThreads();
        threads.runBasic();
    }

    public void runBasic() {

        ThreadSample[] threads = new ThreadSample[5];
        RunnableSample[] runnable = new RunnableSample[5];
        for (int i = 0; i < 5; i++) {
            runnable[i] = new RunnableSample();
            threads[i] = new ThreadSample();

            new Thread(runnable[i]).start();
            threads[i].start();
        }

        DaemonSample daemon = new DaemonSample();
        daemon.setDaemon(true);
        daemon.start();

        System.out.println("RunThreads.runBasic() method is ended.");
    }
}
