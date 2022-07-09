package com.soongjamm.startboot.generic;

import java.util.ArrayList;
import java.util.List;

// https://www.slipp.net/questions/202
public class GenericAndWildcard {

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        List<String> stringList2 = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        List<Integer> integerList2 = new ArrayList<>();

        /** wildcard : 매개변수의 타입 파라미터들은 명확하지만, 리턴 타입이 명확하지 않다. 좋지 않은 방법이다. **/
        List<?> objects = wildcardMethod(stringList, stringList2);
        List<?> objects1 = wildcardMethod(integerList, integerList2);

        /** wildcard : 매개변수의 타입 파라미터들과 리턴 타입 모두 명확하다.. **/
        List<String> strings = genericMethod(stringList, stringList2);
        List<Integer> integers = genericMethod(integerList, integerList2);


        // 타입이 명확해야 하는 경우 (리턴 타입, 제네릭 메소드 안에서 타입 파라미터의 통일 등등) 에서는 제네릭 타입이 좋고
        // 제네릭 메서드 내에서 리턴하지 않고, 값을 추가하지 않는 경우? 와일드 카드가 간결하다 -> 아무 타입이나 와도 무방하다라는 의도를 남기때 사용?
    }

    /** list1의 ?와 list2의 ?가 같은지 알 수 없다. **/
    // List는 제네릭 클래스이다. 특정 파라미터 타입을 받고 있는데, 거기에 '?' 가 들어간 것이다. 즉, wildcard type 은  generic type 하위의 관계다.
    // 리턴 타입에 wildcard 를 쓰면 generic이 주는 타입 추론의 장점을 잃어버린다.
    // 매개 변수에 wildcard 를 스면 어떤 타입이든 올 수있다는 의미를 줄 수 있다.
    public static List<?> wildcardMethod(List<?> list1, List<?> list2) { // List<?> = Unbounded Wildcard Type
        // list1.addAll(list2); // compile error.
        list1.add(null);  /** wildcard type 에는 null 만 추가할 수 있다. **/
        return list1;
    }

    /** list1의 list2의 타입 파라미터 T는 같다.. **/
    public static <T> List<T> genericMethod(List<T> list1, List<T> list2) {
         list1.addAll(list2);
         return list1;
    }
}
