package _16_completable_future.future.result;

import java.util.Random;

import static _16_completable_future.future.practice.FutureExample2.delay;

public class Discount {
    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " +
                Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(double price, Code code) {
        randomDelay(); // mock server delay
        return price * (100 - code.percentage) / 100;
    }

    private static void randomDelay() {
        Random random = new Random();
        long l = random.nextInt(5000);
        l = l >= 0 ? l : l*-1;
        try {
            Thread.sleep(l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
