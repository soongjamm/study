package _15._4.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static _15._2.동기API_비동기API._2_ExecutorServiceExample.*;

public class _2_CFCombine {
    // f(x), g(x) 를 실행하는 두개의 활성 스레드가 있을 때,
    // 한 스레드는 다른 스레드가 return 을 실행후 종료할때까지 기다렸다가 시작한다.
    // 위 상황에서 스레드를 완벽하게 활용하는 테스크를 구현하려면 comleteable future 를 이용해야 한다.
    // 처음 두 태스크가 실행되기 전까지 세번째 테스크를 실행할 수 없는데, 이것을 자바에서 해결하는 방법은 future 를 조합하면 된다.
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int x = 1337;

        CompletableFuture<Integer> a = new CompletableFuture<>();
        CompletableFuture<Integer> b = new CompletableFuture<>();

        // a, b 연산을 모르는 상태에서 이를 이용하는 연산을 만든다. 앞의 두 작업이 끝낼 때 까지 스레드에서 실행되지 않는다.  => 블록 문제가 안생긴다.
        CompletableFuture<Integer> c = a.thenCombine(b, (y, z) -> y + z);
        executorService.submit(() -> a.complete(f(x)));
        executorService.submit(() -> b.complete(g(x)));
        System.out.println(c.get());
        executorService.shutdown();

        // sum을 하는 c 는 a, b 연산이 끝나야 활성화된다. 즉 sum 하는 쓰레드가 블록킹되지 않는다 (자원이 기다리는 상태로 낭비되지 않는다)
        // (앞에 1의 문제점은 블록가능성이 있는 f(x)나 g(x) 함수를 호출하는 쓰레드가 sum 도 해야해서, sum 하는 쓰레드가 블록 상태로 낭비된다.
    }
}
