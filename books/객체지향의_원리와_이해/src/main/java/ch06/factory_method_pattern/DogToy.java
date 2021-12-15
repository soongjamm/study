package ch06.factory_method_pattern;

public class DogToy extends AnimalToy {
    // 팩터리 메서드가 생성할 객체
    @Override
    void identify() {
        System.out.println("나는 테니스공! 강아지의 친구");
    }
}
