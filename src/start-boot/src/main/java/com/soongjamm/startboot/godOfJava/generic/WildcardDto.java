package com.soongjamm.startboot.godOfJava.generic;

import java.io.Serializable;

public class WildcardDto<T> implements Serializable {
    private T object;

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
