package generic;

public class BoxingMethodExample {
    public static void run() {
        Box<Integer> box1 = Util1.boxing(10);
        int value1 = box1.getBox();
    }
}
