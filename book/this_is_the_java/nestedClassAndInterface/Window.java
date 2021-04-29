package this_is_the_java.nestedClassAndInterface;

public class Window {
    // btn1은 필드 초기값, btn2는 매개값 대입으로 사용
    Button btn1 = new Button();
    Button btn2 = new Button();

    Button.OnClickListener listener = new Button.OnClickListener() {
        @Override
        public void onClick() {
            System.out.println("전화를 겁니다.");
        }
    };

    Window() {
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick() {
                System.out.println("문자를 보냅니다.");
            }
        });
    }

}
