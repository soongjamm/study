package spring;

public class LoginCommand {

    private String email;
    private String password;
    private boolean rememberEmail;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isRememberEmail() {
        return rememberEmail;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRememberEmail(boolean rememberEmail) {
        this.rememberEmail = rememberEmail;
    }
}
