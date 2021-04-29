package ch07.IoC_DI.c5_di_spring_property;

public class Car {
    Tire tire;

    public void setTire(Tire tire) {
        this.tire = tire;
    }

    public String getTireBrand() {
        return "장착된 타이어: " + tire.getBrand();
    }
}
