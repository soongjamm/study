package com.soongjamm.startboot.interface_practice;

import java.util.ArrayList;
import java.util.List;

// 클라이언트
public class Racing {

    List<Car> cars = new ArrayList<>();

    // 만약 여기서 FastCar, SlowCar 등을 직접 생성했다면
    // Racing 에서 흐름을 제어하게 되는 것이다.
    // 의존성 주입을 받게 되면 어떤 구현부가 오는지 Racing 에서는 알 수 없고
    // 이것을 제어의 흐름이 외부에 넘어갔다고 한다.
    // 팩토리 - 빈 컨테이너 : 어떤 연관관계를 맺고 생성을 할 것인가?
    // Racing(클라이언트) - 스프링 프레임워크 내부 코드 : 어떻게 사용될 것인가? 컨트롤러와 서비스를 어떻게 사용할 것인가? 를 맡고있다.

    // start()나 result()에 각각의 실행 흐름이 존재한다.


    // 의존성 주입
    public Racing() {
        MovingStrategy movingStrategy = new FastStrategy();
        for (int i=0; i<5; i++) {
//            cars.add(new Car(movingStrategy)); // 레이싱의 책임은 경주지, 객체 생성이 아니다. 관심사의 분리가 덜 되었다.
            // Car 생성에 대한 제어, 행동 제어 관점이 있다. 팩토리를 생성해서 생성에 대한 제어를 분리해준다.

            cars.add(CarFactory.car());
        }
    }

    // 움직이는 방식을 바꾸고 싶다면 여기만 바꾸면 된다.
    public void start() {
        for (int i=0; i<5; i++) {
            cars.forEach(Car::move);
        }
    }

    // 출력 방식을 바꾸고 싶다면 여기만 바꾸면 된다.
    public void result() {
        cars.forEach(Car::getPosition);
    }

}
