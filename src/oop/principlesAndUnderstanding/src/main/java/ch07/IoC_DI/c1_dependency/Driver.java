package ch07.IoC_DI.c1_dependency;

public class Driver {
    public static void main(String[] args) {
        Car car = new Car();
        System.out.println(car.getTireBrand());
    }
}
