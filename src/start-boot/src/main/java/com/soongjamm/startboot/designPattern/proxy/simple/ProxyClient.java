package com.soongjamm.startboot.designPattern.proxy.simple;

public class ProxyClient {
    public static void main(String[] args) {
        // 프록시를 이용한 호출
        IService proxy = new Proxy(new Service());
        String result = proxy.run();
        System.out.println("result = " + result);

        System.out.println();

        proxy = new Proxy(new AnotherService());
        String another = proxy.run();
        System.out.println("another = " + another);

    }
}
