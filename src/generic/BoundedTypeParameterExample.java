package generic;

public class BoundedTypeParameterExample {
    public static void run() {
        //String str = Util.compare("a", "b"); // String은 Number 타입이 아니므로 불가능

        // 왼쪽이 작으면 -1, 왼쪽이 크면 +1
        int result1 = Util2.compare(10, 20);
        int result2 = Util2.compare(4.5, 3);

        System.out.println(result1 + " " + result2);
    }
}
