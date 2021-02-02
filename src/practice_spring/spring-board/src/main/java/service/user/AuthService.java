package service.user;

import domain.user.User;
import domain.user.UserDao;
import dto.AuthInfo;

public class AuthService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public AuthInfo authenticate(String email, String password) {
        User user = userDao.findByEmail(email);
        if (user == null
                || !user.equalPassword(password)) {
            throw new IllegalArgumentException("존재하지 않는 email이거나 일치하지 않는 password");
        }
        return new AuthInfo(user.getName(), user.getEmail());
    }
}
