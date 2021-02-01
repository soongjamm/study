package service.user;

import domain.user.User;
import domain.user.UserDao;
import dto.RegisterUserDto;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public class RegisterUserService {

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    UserDao userDao;

    @Transactional
    public void register(RegisterUserDto dto) {
        User duplicate = userDao.findByEmail(dto.getEmail());
        if (duplicate != null) {
            throw new DuplicateKeyException(dto.getEmail() + " already exists");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRegdate(LocalDateTime.now());
    }
}
