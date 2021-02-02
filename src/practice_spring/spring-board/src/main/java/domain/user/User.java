package domain.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {
    private Long id;
    private String email;
    private String password;
    private String name;
    private LocalDateTime regdate;


    public void setRegdate(LocalDateTime regdate) {
        this.regdate = regdate;
    }

    public LocalDateTime getRegdate() {
        return regdate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public boolean equalPassword(String password) {
        return this.password.equals(password);
    }
}
