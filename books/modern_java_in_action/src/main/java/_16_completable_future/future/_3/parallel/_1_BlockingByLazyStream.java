package _16_completable_future.future._3.parallel;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class BlockingByLazyStream {
    public static void main(String[] args) {
        List<PostOffice> postOffices = List.of(new PostOffice("seoul"), new PostOffice("busan"), new PostOffice("daegu"), new PostOffice("incheon"));

        long start = System.currentTimeMillis();

        List<Integer> results = postOffices.stream()
                .map(postOffice -> CompletableFuture.supplyAsync(postOffice::getStackedCount))
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        long end = System.currentTimeMillis();

        System.out.println((end - start) + "ms");

        // 결과 : 비동기로 테스크를 실행해도 2*4 8초가 걸림
    }

    public static class PostOffice {
        private String region;

        public PostOffice(String region) {
            this.region = region;
        }

        public int getStackedCount() {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new Random().nextInt(10);
        }
    }
}
