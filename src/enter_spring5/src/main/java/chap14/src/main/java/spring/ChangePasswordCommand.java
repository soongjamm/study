package spring;

public class ChangePasswordCommand {
    private String currentPassword;
    private String newPassword;

    public String getCurrentPasssword() {
        return currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPasssword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
