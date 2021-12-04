package _16_completable_future.future.practice;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.toList;

public class FutureExample7 {
    public static void main(String[] args) {
        FutureExample7 futureExample = new FutureExample7();
        futureExample.runAsync();
    }

    private static List<Shop> shops = Arrays.asList(
            new Shop("BestPrice"),
            new Shop("BestPrice2"),
            new Shop("LestSaveBig"),
            new Shop("MyFavorite"),
            new Shop("WorstShop"),
            new Shop("WorstShop"),
            new Shop("WorstShop"),
            new Shop("WorstShop"),
            new Shop("WorstShop"),
            new Shop("WorstShop"),
            new Shop("MostWorstShop"),
            new Shop("MostWorstShop2"),
            new Shop("MostWorstShop2"),
            new Shop("MostWorstShop2"),
            new Shop("MostWorstShop2"),
            new Shop("MostWorstShop2"),
            new Shop("MostWorstShop2"),
            new Shop("MostWorstShop2"),
            new Shop("MostWorstShop2"),
            new Shop("MostWorstShop2"),
            new Shop("MostWorstShop2"),
            new Shop("MostWorstShop2"),
            new Shop("MostWorstShop3"),
            new Shop("Emart")); // 12개 부터 1초 늘어난다.

    public void runAsync() {
        long start = System.nanoTime();
        System.out.println("findPrices = " + findPrices("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    public List<String> findPrices(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))
                ))
                .collect(toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }
}
