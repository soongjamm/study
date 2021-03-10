package com.soongjamm.startboot.practice.user;

import java.util.Objects;

public class LoginDto {
    private String email;
    private String password;
    private String remember;

    public LoginDto(String email, String password, String remember) {
        this.email = email;
        this.password = password;
        this.remember = remember;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean isRemember() {
        if (Objects.isNull(remember)) {
            return false;
        }
        return true;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRemember(String remember) {
        this.remember = remember;
    }
}
