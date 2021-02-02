package dto;

public class AuthInfo {
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public AuthInfo(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
