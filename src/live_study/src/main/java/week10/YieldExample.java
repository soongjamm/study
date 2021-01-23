package week10;

public class YieldExample {
    public static void main(String[] args) {
        ThreadA thread0 = new ThreadA();
        ThreadA thread1 = new ThreadA();

        thread0.start();
        thread1.start();

        try {Thread.sleep(3000);}catch(InterruptedException e){}
        thread0.work = false;

        try {Thread.sleep(3000);}catch(InterruptedException e){}
        thread1.work = true;

        try {Thread.sleep(3000);}catch(InterruptedException e){}
        thread0.stop = true;
        thread1.stop = true;
    }
}
