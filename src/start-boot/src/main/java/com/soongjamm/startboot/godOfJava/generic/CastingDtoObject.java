package com.soongjamm.startboot.godOfJava.generic;

import java.io.Serializable;

public class CastingDtoObject implements Serializable {
    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
