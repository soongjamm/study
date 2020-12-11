package generic;

public class Util1 {
    public static <T> Box<T> boxing(T t) {
        Box<T> box = new Box<>();
        box.setBox(t);
        return box;
    }
}
