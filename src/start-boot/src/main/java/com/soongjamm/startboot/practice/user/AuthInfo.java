package com.soongjamm.startboot.practice.user;

public class AuthInfo {

    private Long id;
    private String name;
    private String email;

    public AuthInfo(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
