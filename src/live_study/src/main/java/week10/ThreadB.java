package week10;

public class ThreadB extends Thread{
    public boolean stop = false;

    public void run() {
        while(!stop) {
            System.out.println("양보하겠다");
            Thread.yield();
//            Thread.
        }
        System.out.println("스레드 종료");
    }
}
