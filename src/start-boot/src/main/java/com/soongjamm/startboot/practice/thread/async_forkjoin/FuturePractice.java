package com.soongjamm.startboot.practice.thread.async_forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FuturePractice {
    public void run() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<Integer> submit = service.submit(() -> {
            Thread.sleep(100);
            System.out.println("process...... async...." + Thread.currentThread().getName());
            Thread.sleep(1000);
            return 10;
        });

        System.out.println("wait for process.");
        Integer integer = submit.get(); // get이 끝날때까지 여기서 기다린다. 블록킹 콜 이라고 함.
        System.out.println("done! - " + integer + " " + Thread.currentThread().getName());
        service.shutdown(); // 안하면 쓰레드가 계속 돌아간다.
    }


    public static void main(String[] args) {
        FuturePractice futurePractice = new FuturePractice();
        try {
            futurePractice.run();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
