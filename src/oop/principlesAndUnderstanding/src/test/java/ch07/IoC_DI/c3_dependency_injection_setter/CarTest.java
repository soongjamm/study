package ch07.IoC_DI.c3_dependency_injection_setter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    public void 자동차_코리아타이어_장착_타이어브랜드_테스트() {
        Car car = new Car();
        Tire tire = new KoreaTire();
        car.setTire(tire);

        assertEquals("장착된 타이어: 코리아 타이어", car.getTireBrand());
    }

    @Test
    public void 자동차_미국타이어_장착_타이어브랜드_테스트() {
        Car car = new Car();
        Tire tire = new AmericaTire();
        car.setTire(tire);

        assertEquals("장착된 타이어: 미국 타이어", car.getTireBrand());
    }
}