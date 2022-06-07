package _15._2.동기API_비동기API;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 앞에것 보단 낫지만, 여전히 submit 메서드처럼 관심사가 다른 코드로 오염되어 있다.
 * */
public class _2_ExecutorServiceExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int x = 1337;

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> y = executorService.submit(() -> f(x));
        Future<Integer> z = executorService.submit(() -> g(x));


        executorService.shutdown();
        System.out.println(y.get() + z.get());
    }

    public static int f(int x) {
        try {
            Thread.sleep(x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 10;
    }

    public static int g(int x) {
        try {
            Thread.sleep(x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
