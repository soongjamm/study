package week10;

public class PriorityExample {
    public static void main(String args[]) {
        for (int i = 1; i <= 10; i++) {
            Thread thread = new CalcThread(i + "번째 스레드");
            if (i == 10) {
                thread.setPriority(Thread.MAX_PRIORITY);
            } else {
                thread.setPriority(Thread.MIN_PRIORITY);
            }
            thread.start();
        }
    }
}