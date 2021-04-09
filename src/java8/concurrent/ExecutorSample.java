package java8.concurrent;

import java.util.List;
import java.util.concurrent.*;

public class ExecutorSample {
    public boolean singleThreadExecutor() {
        // Executor 만 쓰는 경우는 거의 없다.
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<String> submit = es.submit(() -> Thread.currentThread().getName());
        System.out.println(submit);

        es.shutdown();
        return es.isShutdown();
    }

    public boolean threadPool() {
        // 스레드풀에 스레드 3개 생성해놓고 5개의 작업을 처리함
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(() -> System.out.println("first"));
        executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("second");
        });

        executorService.submit(() -> System.out.println("three"));
        executorService.submit(() -> System.out.println("four"));
        executorService.submit(() -> System.out.println("five"));
        executorService.shutdown();
        try {
            return executorService.awaitTermination(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void schedule() {
        ScheduledExecutorService scheduled = Executors.newSingleThreadScheduledExecutor();
        scheduled.schedule(() -> System.out.println("!!"), 1, TimeUnit.SECONDS);
        scheduled.scheduleAtFixedRate(() -> System.out.println("test"), 1, 2, TimeUnit.SECONDS);
    }
}
