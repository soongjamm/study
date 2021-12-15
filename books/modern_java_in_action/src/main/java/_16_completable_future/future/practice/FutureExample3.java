package _16_completable_future.future.practice;

import java.util.concurrent.Future;

public class FutureExample3 {
    public static void main(String[] args) {
        FutureExample3 futureExample = new FutureExample3();
        futureExample.runAsync();
    }

    public void runAsync() {
        ShopASync shop = new ShopASync("BestShop");
        long start = System.nanoTime();

        Future<Double> futurePrice = shop.getPriceAsync1("my favorite product");// 상점에 제품 가격 정보 요청
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("invocation returned after  = " + invocationTime + "msecs");

        //제품의 가격을 계산하는 도안
        doSomethingElse();

        try {
            Double price = futurePrice.get();
            System.out.println("price = " + price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        long retrievalTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Price returned after = " + retrievalTime + "msecs");

    }

    private void doSomethingElse() {

        for (int i = 0; i < 10; i++) {
            System.out.println("print - " + i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void delay() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
