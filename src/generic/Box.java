package generic;

public class Box<T> {
    T object;

    public void setBox(T object) {
        this.object = object;
    }

    public T getBox() {
        return object;
    }
}
