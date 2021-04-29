package ch07.IoC_DI.c1_dependency;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    public void 자동차_장착_타이어브랜드_테스트() {
        Car car1 = new Car();

        assertEquals("장착된 타이어: 코리아 타이어", car1.getTireBrand());
    }

}