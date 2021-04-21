package com.soongjamm.startboot.designPattern.decorator.simple;

public class DecoratorClient {
    public static void main(String[] args) {
        // 프록시와 같지만 결과값을 포장해서 준다는 것이 다르다.
        IService proxy = new Decorator(new Service());
        String result = proxy.run();
        System.out.println("result = " + result);

        System.out.println();

        proxy = new Decorator(new AnotherService());
        String another = proxy.run();
        System.out.println("another = " + another);

    }
}
