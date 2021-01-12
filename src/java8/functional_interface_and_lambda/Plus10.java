package java8.functional_interface_and_lambda;

import java.util.function.Function;

public class Plus10 implements Function<Integer, Integer> {

    @Override
    public Integer apply(Integer integer) {
        return integer + 10;
    }
}
