package _15._4.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static _15._2.동기API_비동기API._2_ExecutorServiceExample.*;

public class CFComplete {
    // f(x), f(y) 를 동시에 실행해 합계를 구하는 코드
    // CompletableFuture 는 실행할 코드 없이 Future를 만들도록 허용하고 complete() 메서드로 나중에 어떤 값을 이용해 스레드가 이를 완료하여 get 으로 값을 얻을 수 있다.
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int x = 1337;

        CompletableFuture<Integer> b = new CompletableFuture<>();
        executorService.submit(() -> b.complete(g(x)));
        int a = f(x);
        System.out.println(b.get() + a);

        executorService.shutdown();

        // f(x)나 g(x)의 실행이 끝나지 않은 상황에서 get() 을 기다려야하므로 프로세싱 자원 낭비가 생길수도
        // CopmpletableFuture 를 이용하면 해결됌
    }
}
