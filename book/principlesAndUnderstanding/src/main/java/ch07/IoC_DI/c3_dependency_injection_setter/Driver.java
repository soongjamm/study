package ch07.IoC_DI.c3_dependency_injection_setter;

public class Driver {
    public static void main(String[] args) {
        Tire tire = new KoreaTire();
        // Tire tire = new AmericaTire();
        Car car = new Car();
        car.setTire(tire);

        System.out.println(car.getTireBrand());
    }
}
