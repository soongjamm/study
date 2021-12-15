package ch07.IoC_DI.c2_dependency_injection;

public class AmericaTire implements Tire {
    @Override
    public String getBrand() {
        return "미국 타이어";
    }
}
