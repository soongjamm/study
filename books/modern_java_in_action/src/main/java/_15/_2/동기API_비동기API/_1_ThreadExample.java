package _15._2.동기API_비동기API;

/**
 *  코드가 복잡하다.
 *  task 제출고 실행이 섞여있고 메인 스레드가 종료를 기다리고
* */
public class _1_ThreadExample {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        int x = 1337;
        Result result = new Result();

        Thread t1 = new Thread(() -> result.left = f(x));
        Thread t2 = new Thread(() -> result.right = g(x));
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        long end = System.currentTimeMillis();
        System.out.println(result.left + result.right);
    }

    public static int f(int x) {
        try {
            Thread.sleep(x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int g(int x) {
        try {
            Thread.sleep(x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }

    private static class Result {
        private int left;
        private int right;
    }
}
