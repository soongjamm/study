package com.soongjamm.startboot.godOfJava.java7;

import java.util.stream.Stream;

public class LambdaSample {

    public static final LambdaSample lambdaSample = new LambdaSample();
    public final LambdaSample notStaticSample = new LambdaSample();

    public static void main(String[] args) {
        LambdaSample sample = new LambdaSample();
        sample.play(new String[]{"무우","야하","호오오"});


    }

    public void hey(String a) {
        System.out.println("a " + a);
    }

    public void play(String[] strings) {
        Stream.of(strings).forEach(LambdaSample::mooyaho); // LambdaSample 은 해당 제네릭타입이 아니지만, 스태틱 메소드는 사용 가능
        Stream.of(strings).forEach(String::toString); // 스트링 타입이 제네릭타입이므로 사용가능
        Stream.of(strings).forEach(System.out::println); // out은 System의 클래스멤버고 그 멤버의 메소드 ㅇㅋ
        Stream.of(strings).forEach(LambdaSample.lambdaSample::hey); // 위와 마찬가지. 그 멤버의 메소드는 스태틱 아니어도 됌
    }

    public static void mooyaho(String s) {
        System.out.println("moo ya ho ho ho " + s);
    }
}
