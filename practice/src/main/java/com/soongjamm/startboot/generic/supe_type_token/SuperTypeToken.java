package com.soongjamm.startboot.generic.supe_type_token;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class SuperTypeToken {

    static class TypeReference<T> {
        Type type;

        public TypeReference() {
            Type sType = getClass().getGenericSuperclass();
            if (sType instanceof ParameterizedType) {
                this.type = ((ParameterizedType)sType).getActualTypeArguments()[0];
            } else {
                throw new RuntimeException();
            }
        }
    }
}
