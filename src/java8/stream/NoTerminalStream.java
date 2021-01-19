package java8.stream;

import java.util.ArrayList;
import java.util.List;

public class NoTerminalStream {
    public static void main(String[] args) {

        List<String> names = new ArrayList<>();
        names.add("chulsoo");
        names.add("younghee");

        names.stream().map(x -> {
            System.out.println("출력되지 않는다.. " + x);
            return x.toUpperCase();
        });
    }
}
