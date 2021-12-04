package _16_completable_future.future.practice;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static _16_completable_future.future.practice.FutureExample3.delay;

public class ShopASync {

    private String name;

    public ShopASync(String name) {
        this.name = name;
    }

    public Future<Double> getPriceAsync1(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread( () -> {
            try {
                double price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception e) {
                futurePrice.completeExceptionally(e);
            }
        }).start();
        return futurePrice;
    }

    public Future<Double> getPriceAsync2(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice("exception item"));
    }

    private double calculatePrice(String product) {
        System.out.println("calculation start");
        if (product.equals("exception item")) {
            throw new IllegalStateException("moo ya ho");
        }
        delay();
        Random random = new Random();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

}
