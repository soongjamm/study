package this_is_the_java.nestedClassAndInterface;

public class ButtonExample {
    public static void start() {
        Button btn = new Button();

        btn.setOnClickListener(new CallListener());
        btn.touch();

        btn.setOnClickListener(new MessageListener());
        btn.touch();
    }
}
