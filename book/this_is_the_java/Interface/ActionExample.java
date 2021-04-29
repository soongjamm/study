package this_is_the_java.Interface;

public class ActionExample {
    public static void start() {
        Action action = new Action() {
            @Override
            public void work() {
                System.out.println("복사를 합니다.");
            }
        };
        action.work();
    }
}
