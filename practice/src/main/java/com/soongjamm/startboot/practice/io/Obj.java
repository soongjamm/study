package com.soongjamm.startboot.practice.io;

import java.io.Serializable;
import java.util.Objects;

public class Obj implements Serializable {
    static final long serialVersionUID = 1L;

    private String name;
    private String name2;

    public Obj(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Obj obj = (Obj) o;
        return Objects.equals(name, obj.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
