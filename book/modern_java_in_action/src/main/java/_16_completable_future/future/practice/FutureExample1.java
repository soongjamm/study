package _16_completable_future.future.practice;

import java.util.concurrent.*;

public class FutureExample1 {
    public static void main(String[] args) {
        FutureExample1 futureExample = new FutureExample1();
        futureExample.run();
    }

    public void run() {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return doSomeLongComputation();
            }
        });
        doSomethingElse();

        try {
            Double price = future.get(1L, TimeUnit.SECONDS);
            System.out.println("price = " + price);
        } catch (InterruptedException e) {
            // 현재 스레드에서 대기 중 인터럽트 발생
        } catch (ExecutionException e) {
            // 계산 중 예외 발생
        } catch (TimeoutException e) {
            // Future 가 완료되기 전에 타임아웃 발생
        }
    }

    private void doSomethingElse() {
        for (int i = 0; i < 10; i++) {
            System.out.println("print - " + i);
        }
    }

    private Double doSomeLongComputation() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 10d;
    }
}
