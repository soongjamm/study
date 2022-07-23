package com.soongjamm.startboot.generic.method_reference;

/*
* 람다는 불가능하나, 메서드 레퍼런스는 가능한 예시
* */
public class App {
    public static void main(String[] args) {
        G g1 = new G() {
            @Override
            public <E extends Exception> Object m() {
                return new Object();
            }
        };

        // G g2 = () -> new Object(); // 제네릭 람다식은 존재하지 않기 때문에 컴파일 에러. 람다식의 타입은 보통 다 추론되어서 구체적 타입이 오니끼

        G g3 = Object::new; // 그러나 메서드 레퍼런스는 사용할 수 있다.
    }
}
