package ch06.template_method_pattern;

public class Dog extends Animal {

    @Override
    // 추상메소드 오버라이드
    void play() {
        System.out.println("멍머엉");
    }

    @Override
    // Hook(갈고리 메소드) 오버라이딩
    void runSomething() {
        System.out.println("멍머엉~ 꼬리 살라앙");
    }
}
