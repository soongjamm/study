package ch07.IoC_DI.c3_dependency_injection_setter;

public class Car {
    Tire tire;

    public void setTire(Tire tire) {
        this.tire = tire;
    }

    public String getTireBrand() {
        return "장착된 타이어: " + tire.getBrand();
    }
}
