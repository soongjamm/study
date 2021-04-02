package hello.core.singleton;

// 싱글톤 객체가 상태를 가지면 문제가 생긴다.
public class StatefulSingletonService {

    private int price;

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
