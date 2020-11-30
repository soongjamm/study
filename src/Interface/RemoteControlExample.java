package Interface;

public class RemoteControlExample {
    public static void start() {
        RemoteControl rc = null;
        rc = new Television();
        rc.turnOn();
        rc.setMute(true);

        rc = new Audio();
        rc.turnOn();
        rc.setMute(true);

        RemoteControl.changeBattery();

////         익명 구현 객체
//        RemoteControl rc = new RemoteControl() {
//            @Override
//            public void turnOn() {
//
//            }
//
//            @Override
//            public void turnOff() {
//
//            }
//
//            @Override
//            public void setVolume(int volume) {
//
//            }
//        };
    }
}
