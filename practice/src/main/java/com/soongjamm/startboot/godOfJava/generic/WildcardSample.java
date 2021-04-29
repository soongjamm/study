package com.soongjamm.startboot.godOfJava.generic;

public class WildcardSample {
    public static void main(String[] args) {
        WildcardSample sample = new WildcardSample();

        sample.callMethodWithoutWildcard();

        sample.callWildcard();

        sample.callBoundedWildcard();

        sample.callGenericMethod();

        sample.callBoundedGenericMethod();

        sample.callMultipleBoundedGenericMethod();
    }



    /////////////////////////////////////////////////////////////////////////////////////
    // TODO
    private void callMultipleBoundedGenericMethod() {
        WildcardDto<Car> w1 = new WildcardDto<>();
        multipleBoundedGenericMethod(w1, new Bus("jongno 05"), "hi");
    }

    private <S,T extends Car> void multipleBoundedGenericMethod(WildcardDto<T> w1, T bus, S hi) {
        w1.setObject(bus);
        System.out.println(hi);
    }


    /////////////////////////////////////////////////////////////////////////////////////
    // TODO
    // 메소드에 제네릭 타입을 2개 받아야 하는 경우
    private void callBoundedGenericMethod() {
        WildcardDto<Car> wildcard = new WildcardDto<>();
        boundedGenericMethod(wildcard, new Bus("jongno 11"));
    }

    private <T extends Car> void boundedGenericMethod(WildcardDto<T> wildcard, T data) {
        wildcard.setObject(data);
        System.out.println(wildcard.getObject());
    }


    /////////////////////////////////////////////////////////////////////////////////////
    // TODO
    // 메소드에서 제네릭타입 파라미터의 값을 설정하는 경우
    private void callGenericMethod() {
        WildcardDto<String> wildcard = new WildcardDto<>();
        genericMethod(wildcard, "Data");
    }

    // 리턴타입 앞에 제네릭 타입이 선언되어있고 파라미터에서 제네릭타입을 포함한 객체를 받고 있다..
    private <T> void genericMethod(WildcardDto<T> wildcard, T data) {
        wildcard.setObject(data);
        T value = wildcard.getObject();
        System.out.println("generic method called : " + value);
    }


    /////////////////////////////////////////////////////////////////////////////////////
    // TODO
    // 특정 타입이나 하위클래스만 제네릭 타입으로 선언하는 경우
    private void callBoundedWildcard() {
        WildcardDto<Car> dto1 = new WildcardDto<>();
        dto1.setObject(new Car("mustang"));

        WildcardDto<Car> dto2 = new WildcardDto<>();
        dto2.setObject(new Bus("jongro 09"));

        WildcardDto<Airplane> dto3 = new WildcardDto<>();
        dto3.setObject(new Airplane("daehan"));

        boundedWildcardMethod(dto1);
        boundedWildcardMethod(dto2); // Car 를 상속받은 Bus 제네릭 타입 객체라 가능
        // boundedWildcardMethod(dto3);   // Car 를 상속받지 않은 제네릭 타입 객체라 불가능

    }

    private void boundedWildcardMethod(WildcardDto<? extends Car> dto) {
        Car value = dto.getObject();
    }



    /////////////////////////////////////////////////////////////////////////////////////
    // TODO
    // 제네릭 타입 객체를 생성해서 다른 메소드에 넘겨줄 때, 제네릭타입이 여러가지라면 하나로 명시하기 어렵다.
    // 그러나 메소드 타입별로 오버로딩이 불가능하기 때문에. -> 와일드카드로 오버로딩 효과를 낸다.
    private void callWildcard() {
        WildcardDto<String> wildcardString = new WildcardDto<>();
        wildcardString.setObject("A");
        wildcardMethod(wildcardString);

        WildcardDto<Integer> wildcardInteger = new WildcardDto<>();
        wildcardInteger.setObject(1);
        wildcardMethod(wildcardInteger);
    }

    private void wildcardMethod(WildcardDto<?> wildcardDto) {
        Object value = wildcardDto.getObject();
        if (value instanceof String) {
            // do something
        }

        if (value instanceof Integer) {
            // do something
        }

        // 단점 : wildcard에 값을 추가할 수 없음 -> 매개변수로 사용된 객체에 값을 추가할 수 없음
        // 그래서 wildcard는 메소드의 매개변수로만 사용하는 것이 좋다.
        WildcardDto<?> whatever = new WildcardDto<>();
        // whatever.setObject("String"); // 불가능. ? 타입인데 스트링을 넣는거니까

        // 단점이 보완된 코드는 callGenericMethod() 참고

    }


    /////////////////////////////////////////////////////////////////////////////////////
    // TODO
    // 제네릭 타입을 쓰는 객체를 생성해서 다른 메소드에 넘겨준다.
    private void callMethodWithoutWildcard() {
        WildcardDto<String> wildcardString = new WildcardDto<>();
        wildcardString.setObject("A");
        wildcardStringMethod(wildcardString);

        WildcardDto<Integer> wildcardInteger = new WildcardDto<>();
        wildcardInteger.setObject(1);
//        wildcardStringMethod(wildcard2); // 제네릭 타입만 바꾼 오버로딩이 불가능하기 때문에 불가능. 와일드카드 필요

    }

    private void wildcardStringMethod(WildcardDto<String> c) {
        System.out.println(c.getObject());
    }

    // 제네릭 타입만 바꾼 오버로딩이 불가능하다.
//    private void wildcardStringMethod(WildcardDto<Integer> c) {
//        System.out.println(c.getObject());
//    }

}
