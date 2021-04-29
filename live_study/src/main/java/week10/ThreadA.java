package week10;

public class ThreadA extends Thread {
    public boolean stop = false;
    public boolean work = true;

    public void run() {
        int count = 0;
        while(!stop) {
            if(work) {
                count++;
                System.out.println("작업중" + Thread.currentThread().getName());
            } else {
                System.out.println("스레드 양보" + Thread.currentThread().getName());
                Thread.yield();
            }
        }
        System.out.println("스레드 종료" + Thread.currentThread().getName() + " : " +  count + " 회 실행 ");
    }
}
