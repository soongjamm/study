package java8.functional_interface_and_lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReference {
    public static void main(String[] args) {
        // 매개값이 없는 생성자
        Supplier<Greeting> greet1 = () -> new Greeting();
        Supplier<Greeting> greet2 = Greeting::new;

        // 매개값이 있는 생성자
        Function<String, Greeting> greet3 = (name) -> new Greeting(name);
        Function<String, Greeting> greet4 = Greeting::new;
        greet4.apply("soongjamm").hello();

        // 스태틱 메소드 참조
        Consumer<Integer> staticMethod = (integer) -> RunSomething.staticPrint(integer);
        Consumer<Integer> staticMethod2 = RunSomething::staticPrint;
        BiConsumer<Integer, Integer> staticMethod3 = RunSomething::staticPrint2;

        // 특정 객체의 인스턴스 메소드 참조
        Function<String, Integer> instanceMethod = (str) -> str.length();
        Function<String, Integer> instanceMethod2 = String::length;
        String[] companies = {"samsung", "lg", "apple"};
        Arrays.asList(companies).stream()
                .forEach(String::toUpperCase);

        // 임의 객체의 인스턴스 메소드 참조
        String[] names = {"soong", "jamm", "woo"};
        Arrays.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });
        Arrays.sort(names, (o1, o2) -> o1.compareToIgnoreCase(o2));
        Arrays.sort(names, String::compareToIgnoreCase);

        Greeting[] greetings = {new Greeting("park")};
        Arrays.asList(greetings).stream()
                .forEach(Greeting::hello);

    }
}
