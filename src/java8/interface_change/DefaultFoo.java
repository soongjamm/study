package java8.interface_change;

public class DefaultFoo implements Foo, Wrong{

    String name;

    public DefaultFoo(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println(getName());
    }

    // Wrong을 구현하지 않으면 재정의 할 필요없는 메소드
    @Override
    public void printNameUpperCase() {

    }

    @Override
    public String getName() {
        return this.name;
    }
}
