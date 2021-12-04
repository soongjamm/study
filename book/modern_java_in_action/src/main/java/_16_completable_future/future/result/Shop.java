package _16_completable_future.future.result;

import java.util.Random;

import static _16_completable_future.future.practice.FutureExample2.delay;

public class Shop {

    private String name;

    public Shop(String name) {
        this.name = name;
    }

    public String getPrice(String product) {

        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[
                new Random().nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", this.name, price, code);

    }

    private double calculatePrice(String product) {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }
}
