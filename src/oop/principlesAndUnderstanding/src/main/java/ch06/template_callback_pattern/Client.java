package ch06.template_callback_pattern;

public class Client {
    public static void main(String[] args) {
        Soldier rambo = new Soldier();

        rambo.runContext("빵야 빵야 총!!");
        System.out.println();

        rambo.runContext("칼을 슥슥!!");
        System.out.println();

        rambo.runContext("지건!!");

    }
}
