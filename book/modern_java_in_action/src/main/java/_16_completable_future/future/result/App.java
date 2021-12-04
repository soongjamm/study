package _16_completable_future.future.result;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class App {
    private static List<Shop> shops = Arrays.asList(
            new Shop("BestPrice"),
            new Shop("LestSaveBig"),
            new Shop("MyFavorite"),
            new Shop("MostWorstShop"),
            new Shop("Emart"));

    public static void main(String[] args) {
        App app = new App();

        long start = System.nanoTime();
//        List<String> findPRices = app.findPrices("myPhone27S");
//        System.out.println("findPrices = " + findPRices);
        CompletableFuture[] futures = app.findPricesStream("myPhone27S").map(f -> f.thenAccept(System.out::println))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();
        long duration = (System.nanoTime() - start) / 1_000_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    public Stream<CompletableFuture<String>> findPricesStream(String product) {
        Executor executor = Executors.newFixedThreadPool(4);
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));
    }

    public List<String> findPrices(String product) {
        Executor executor = Executors.newFixedThreadPool(30);
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
                .collect(toList());
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
//
//    public List<String> findPrices(String product) { // very long time
//        return shops.stream()
//                .map(shop -> shop.getPrice(product))
//                .map(Quote::parse)
//                .map(Discount::applyDiscount)
//                .collect(toList());
//    }
}
