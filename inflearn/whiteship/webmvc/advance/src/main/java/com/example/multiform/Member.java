package com.example.multiform;

import javax.validation.constraints.NotBlank;

public class Member {

    interface idValidation {

    }
    interface nameValidation{}

    @NotBlank(groups = idValidation.class)
    private String id;

    @NotBlank(groups = nameValidation.class)
    private String name;

    public Member() {
    }

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
