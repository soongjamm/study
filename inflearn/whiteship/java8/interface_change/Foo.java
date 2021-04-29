package java8.interface_change;

public interface Foo {

    void printName();


    /**
     * @implSpec 이 구현체는 getName()으로 가져온 문장을 대문자로 출력한다.
     */
    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase());
    }

    String getName();
}
