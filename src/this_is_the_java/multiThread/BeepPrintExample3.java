package this_is_the_java.multiThread;

import java.awt.*;

public class BeepPrintExample3 {
    public static void main(String args[]) {
        Thread thread1 = new BeepThread(); // new Thread()로 직접 Thread를 객체화하지 않음. 상속받아 구현한 BeepThread()를 객체화함.
        thread1.start();

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                for (int i = 0; i < 5; i++) {
                    toolkit.beep();
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {

                    }
                }
            }
        };
        thread2.start();

        Thread thread3 = new Thread(() -> {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            for (int i = 0; i < 5; i++) {
                toolkit.beep();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {

                }
            }
        });
        thread3.start();


        for (int i = 0; i < 5; i++) {
            System.out.println("띵");
            try {
                Thread.sleep(500);
            } catch (Exception e) {

            }
        }
    }
}
