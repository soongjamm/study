package _16_completable_future.future.practice;

public class FutureExample2 {
    public static void main(String[] args) {
        FutureExample2 futureExample = new FutureExample2();
        futureExample.runSync();
    }

    public void runSync() {
        System.out.println("start");
        ShopSync shop = new ShopSync("The best shop");
        double iPhone = shop.getPrice("iPhone");
        System.out.println("Do something else");
        System.out.println("iPhone = " + iPhone);
    }

    public static void delay() {
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
