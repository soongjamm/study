package java8.interface_change;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;

public class DefaultMethodMain {
    public static void main(String[] args) {
        Foo foo = new DefaultFoo("soongjamm");
        foo.printName();
        foo.printNameUpperCase();

        System.out.println();
        /**
         * 자주 사용되는 인터페이스의 디폴트 메소드 소개
         */
        List<String> names = Arrays.asList(new String[]{"soong", "jamm", "lee", "park"});

        names.forEach(System.out::print);
        System.out.println();
        Spliterator<String> spliterator = names.spliterator();
        Spliterator<String> spliterator2 = spliterator.trySplit();
        while(spliterator.tryAdvance(System.out::println));
        System.out.println("==============");
        while(spliterator2.tryAdvance(System.out::println));

        // Comparator
        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        Comparator<String> compareTo = String::compareTo;
        names.stream()
               .sorted(compareToIgnoreCase.reversed().thenComparing(compareTo));
    }
}
