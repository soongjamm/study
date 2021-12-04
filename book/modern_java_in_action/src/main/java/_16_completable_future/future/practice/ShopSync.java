package _16_completable_future.future.practice;

import java.util.Random;

import static _16_completable_future.future.practice.FutureExample2.delay;

public class ShopSync {

    private String name;

    public ShopSync(String name) {
        this.name = name;
    }


    public double getPrice(String product) {
        return calculatePrice(product);
    }

    private double calculatePrice(String product) {
        delay();
        Random random = new Random();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

}
