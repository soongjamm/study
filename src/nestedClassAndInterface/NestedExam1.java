package nestedClassAndInterface;

public class NestedExam1 {
    public static void start() {
        A a = new A();

        // 인스턴스 멤버 클래스 객체 생성
        A.B b = a.new B();
        b.field1 = 3;
        b.method();

        // 정적 멤버 클래스 객체 생성
        A.C c =  new A.C(); // C의 인스턴스 객체
        c.field1 = 3;
        c.method1();;
        A.C.field2 = 3; // 정적 객체
        A.C.method2();

        // 로컬 클래스 객체 생성을 위한 메소드 호출
        a.method();
    }
}
