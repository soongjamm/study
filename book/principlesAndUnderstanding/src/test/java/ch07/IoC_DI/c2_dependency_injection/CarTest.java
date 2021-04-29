package ch07.IoC_DI.c2_dependency_injection;

import ch07.IoC_DI.c1_dependency.Car;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    public void 자동차_코리아타이어_장착_브랜드_테스트() {
        ch07.IoC_DI.c1_dependency.Car car1 = new ch07.IoC_DI.c1_dependency.Car();

        assertEquals("장착된 타이어: 코리아 타이어", car1.getTireBrand());
    }

    @Test
    public void 자동ㅊ_미국타이어_장착_브랜드_테스트() {
        ch07.IoC_DI.c1_dependency.Car car2 = new Car();

        assertEquals("장착된 타이어: 미국 타이어", car2.getTireBrand());
    }

}