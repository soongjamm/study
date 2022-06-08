package _15._5.pub_sub_reactive;

public class Main {
    public static void main(String[] args) {
        arithmeticCell();
    }

    private static void simpleCellOnly() {
        SimpleCell c3 = new SimpleCell("C3");
        SimpleCell c2 = new SimpleCell("C2");
        SimpleCell c1 = new SimpleCell("C1");

        c1.subscribe(c3);

        c1.onNext(10);
        c2.onNext(20);
    }

    private static void arithmeticCell() {
        ArithmeticCell c5 = new ArithmeticCell("C5");
        ArithmeticCell c3 = new ArithmeticCell("C3");

        SimpleCell c4 = new SimpleCell("C4");
        SimpleCell c2 = new SimpleCell("C2");
        SimpleCell c1 = new SimpleCell("C1");

        c1.subscribe(c3::setLeft);
        c2.subscribe(c3::setRight);

        c3.subscribe(c5::setRight);
        c4.subscribe(c5::setRight);

        c1.onNext(10);
        c2.onNext(20);
        c1.onNext(15);
        c4.onNext(1);
        c4.onNext(3);

        // c1, c2가 c3을 구독 중이라서 onNext 하면 c3 이 변경을 전파받는다.
        // 만약 c3이 또 무엇을 구독한다면 변경으로 인한 변경이 또 전파될 것이다.
        // 이렇게 발행자/구독자의 그래프를 그릴 수 있다.
    }
}
