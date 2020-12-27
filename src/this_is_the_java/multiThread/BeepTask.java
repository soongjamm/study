package this_is_the_java.multiThread;

import java.awt.*;

public class BeepTask implements Runnable {
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
}
