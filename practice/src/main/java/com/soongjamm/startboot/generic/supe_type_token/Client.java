package com.soongjamm.startboot.generic.supe_type_token;

import com.soongjamm.startboot.generic.supe_type_token.SuperTypeToken.TypeReference;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class Client {
    public static void main(String[] args) throws NoSuchFieldException {

        // Type Token (특정 타입의 클래스 정보를 넘겨서 타입 안전성을 꿰하도록 코드를 작성하는 기법.. )
        // 타입 파라미터 정보를 런타임에 얻어올 수 없다.
        Super<String> typeToken = new Super<>();
        Class<?> stringClass = typeToken.getClass().getDeclaredField("value").getType();
        System.out.println(stringClass + " = Object (type erasure)");


        // Super Type Token 제네릭 타입을 상속받으면 구체 클래스를 받아올 수 있다.
        Sub superTypeToken = new Sub();
        Type genericSuperclass = superTypeToken.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;

        Type actualTypeArgument = parameterizedType.getActualTypeArguments()[0];
        System.out.println(actualTypeArgument);


//        TypeReference t = new TypeReference<List<String>>(); // RuntimeException.. 타입 토큰과 마찬가지 상태
        TypeReference tl = new TypeReference<List<String>>(){}; // 익명클래스로 상속한 상태이므로 슈퍼타입토큰 상태
        System.out.println(tl.type);
    }
}
