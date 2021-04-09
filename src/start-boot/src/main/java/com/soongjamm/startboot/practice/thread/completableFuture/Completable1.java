package com.soongjamm.startboot.practice.thread.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Completable1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //
        // a 그냥 Future를 사용하면 결과값으로 비동기작인 작업 이어서 처리하기가 어려웠다. 콜백을 줄 수가 없어서.
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "eminem";
        }).thenAccept((name) -> System.out.println(name + " is god")).thenRun(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("who is god");
        System.out.println("doing something..... ");
        future.get();
    }
}
