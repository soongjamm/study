package generic;

public class BoxExample {
    public static void run () {
        Box<String> box = new Box<>();
        box.setBox("홍길동");
        String name = box.getBox();

        Box<Integer> box2 = new Box<>();
        box2.setBox(3);
        int number = box2.getBox();
    }
}
