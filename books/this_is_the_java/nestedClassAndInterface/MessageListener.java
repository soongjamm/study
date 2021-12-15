package this_is_the_java.nestedClassAndInterface;

public class MessageListener implements Button.OnClickListener {
    @Override
    public void onClick() {
        System.out.println("메세지를 보냅니다.");
    }
}
