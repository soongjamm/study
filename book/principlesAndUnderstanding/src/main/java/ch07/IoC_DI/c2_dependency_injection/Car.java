package ch07.IoC_DI.c2_dependency_injection;

public class Car {
    Tire tire;

    public Car(Tire tire) {
        this.tire = tire;
    }

    public String getTireBrand() {
        return "장착된 타이어: " + tire.getBrand();
    }
}
