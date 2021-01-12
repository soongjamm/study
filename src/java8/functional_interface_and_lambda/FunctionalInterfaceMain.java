package java8.functional_interface_and_lambda;

import java.util.function.*;

public class FunctionalInterfaceMain {
    public static void main(String[] args) {

        /*
         * 함수형 인터페이스란
         * */
        // Anonymous inner class
        RunSomething run1 = new RunSomething() {
            @Override
            public int runIt(int number) {
                return 0;
            }
        };

        // Lambda Expression
        RunSomething run2 = (number) -> number + 1;
        run2.runIt(1); // 2

        // Effective final 예제
        int outside = 1;
//        RunSomething notProper1 = (number) -> outside++;
        RunSomething notProper2 = (number) -> outside + 1;
//        outside++;


        /*
         * 자바 기본 함수형 인터페이스
         * */

        // 함수형 인터페이스 - Function
        RunSomething plus10_1 = number -> number + 10;
        Plus10 plus10_2 = new Plus10();
        Function<Integer, Integer> plus10_3 = number -> number + 10;
        System.out.println(String.format("%d %d %d", plus10_1.runIt(1), plus10_2.apply(1), plus10_3.apply(1)));

        // UnaryOperator + 조합 메소드 (Function을 상속받은 인터페이스이기 때문에 메소드 사용가능)
        UnaryOperator<Integer> multiply2 = (integer) -> integer * 2;
        System.out.println(String.format("compose: %d==24 | andThen: %d==14",
                multiply2.compose(plus10_3).apply(2), multiply2.andThen(plus10_3).apply(2)));

        // BiFunction
        BiFunction<Integer, Integer, Integer> plusAB = (a, b) -> a + b;
        System.out.println(plusAB.apply(1, 2));

        // Consumer
        Consumer<Integer> printInt = (System.out::println);
        printInt.accept(100);

        // Supplier
        Supplier<Integer> get10 = () -> 10;
        System.out.println(get10.get());

        // Predicate
        Predicate<String> myNickname = nickname -> nickname.equals("soongjamm");
        Predicate<String> myPassword = password -> password.equals("p@ssw0rd");
        System.out.println(String.format("%s %s %s %s",
                myNickname.and(myPassword).test("p@ssw0rd"), // false
                myNickname.or(myPassword).test("soongjamm"), // true
                myNickname.test("soongjamm"), // true
                myNickname.negate().test("fake soongjamm"))); // true
    }
}
