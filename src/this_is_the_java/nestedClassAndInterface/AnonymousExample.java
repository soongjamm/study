package this_is_the_java.nestedClassAndInterface;

public class AnonymousExample {
    public static void start() {
        Anonymous anony = new Anonymous();
        // 익명 객체 필드 사용
        anony.field.wake();
        // 익명 객체 로컬 변수 사용
        anony.method1();
        // 익명 객체 매개값 사용
        anony.method2(new Person() {
            void study() {
                System.out.println("공부합니다.");
            }
            @Override
            void wake() {
                System.out.println("5시에 일어납니다.");
                study();
            }
        });
    }
}
