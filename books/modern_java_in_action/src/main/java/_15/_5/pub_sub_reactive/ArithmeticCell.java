package _15._5.pub_sub_reactive;

public class ArithmeticCell extends SimpleCell {
    private int left;
    private int right;

    public ArithmeticCell(String name) {
        super(name);
    }

    public void setLeft(int left) {
        this.left = left;
        onNext(left + this.right); // update value and notify to all subscribers
    }

    public void setRight(int right) {
        this.right = right;
        onNext(this.left + right);
    }
}
