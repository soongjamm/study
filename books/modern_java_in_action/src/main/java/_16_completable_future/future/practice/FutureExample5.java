package _16_completable_future.future.practice;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.*;

public class FutureExample5 {
    public static void main(String[] args) {
        FutureExample5 futureExample = new FutureExample5();
        futureExample.runAsync();
    }

    private static List<Shop> shops = Arrays.asList(
            new Shop("BestPrice"),
            new Shop("LestSaveBig"),
            new Shop("MyFavorite"),
            new Shop("Emart"));

    public void runAsync() {
        long start = System.nanoTime();
        System.out.println("findPrices = " + findPrices("myPhone27S"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs" );
    }

    public List<String> findPrices(String product) {
        return shops.parallelStream()
                .map(shop -> String.format("$2 price is $.2f", shop.getName(), shop.getPrice(product)))
                .collect(toList());
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
