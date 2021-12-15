package this_is_the_java.multiThread;

import java.awt.*;

public class BeepPrintExample2 {
    public static void main(String[] args) {
        Runnable beepTask = new BeepTask();
        Thread thread1 = new Thread(beepTask); // new Thread()로 직접 Thread를 객체화
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                for (int i = 0; i < 5; i++) {
                    try {
                        toolkit.beep();
                        Thread.sleep(500);
                    } catch (Exception e) {

                    }
                }
            }
        });
        thread2.start();

        Thread thread3 = new Thread( () -> {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            for (int i = 0; i < 5; i++) {
                try {
                    toolkit.beep();
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
