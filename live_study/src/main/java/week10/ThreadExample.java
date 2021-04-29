package week10;


class DoSomething implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            Thread.currentThread().interrupt();
            System.out.println("무언가 합니다.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Test {
    public void run() throws InterruptedException {
        Thread thread = new Thread(new DoSomething());
        thread.start();
        thread.interrupt();
        thread.join();
        System.out.println("TEST");

    }
}

public class ThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Test t = new Test();
        t.run();

    }
}
