package _15._2.동기API_비동기API;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.IntConsumer;

/**
 * return 으로 결과를 반환하지 않고, 결과가 준비되면 이를 람다로 호출하는 태스크를 만드는 콜백방식
 * */
public class _3_CallbackStyleExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int x = 1337;
        Result result = new Result();

        f(x, (int y) -> {
            result.left = y;
            System.out.println((result.left + result.right));
        });

        g(x, (int z) -> {
            result.right = z;
            System.out.println((result.left + result.right));
        });
    }

    public static void f(int x, IntConsumer dealWithResult) {
        try {
            Thread.sleep(x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dealWithResult.accept(x);
    }

    public static void g(int x, IntConsumer dealWithResult) {
        try {
            Thread.sleep(x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dealWithResult.accept(x);
    }

    private static class Result {
        private int left;
        private int right;
    }
}
