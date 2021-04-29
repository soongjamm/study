package ch07.IoC_DI.c2_dependency_injection;

public class Driver {
    public static void main(String[] args) {
        Tire tire = new KoreaTire();
        // Tire tire = new AmericaTire();
        Car car = new Car(tire);

        System.out.println(car.getTireBrand());
    }
}
