package java8.functional_interface_and_lambda;

import java.util.function.BiConsumer;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

public class Lambda {
    public static void main(String[] args) {
        /*
         * 람다식
         * */

        // 매개값이 0개 ~ 2개
        Supplier<Integer> give5 = () -> 5;
        IntConsumer printInt2 = (integer) -> System.out.println(integer);
        BiConsumer<Integer, Integer> print2Int = ((integer1, integer2) -> System.out.println(integer1 + integer2));

        // 변수 캡처
        String captureVar = "I'm outsider";
        class LocalClass {
            void method() {
                String captureVar = "LocalClass var";
                System.out.println("outsider is " + captureVar);
            }
        }


        RunSomething capture = new RunSomething() {
            @Override
            public int runIt(int number) {
                String captureVar = "Anonymous var";
                System.out.println(captureVar);
                return 0;
            }
        };


        new LocalClass().method();
        capture.runIt(0);
//        Consumer<String> lambdaCapture = (captureVar) -> System.out.println(captureVar); // 컴파일 에러
        captureVar = "text";

    }
}
