package com.soongjamm.startboot.practice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public AuthInfo authenticate(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
        if (!user.equalPassword(password)) {
            throw new IllegalArgumentException();
        }

        return new AuthInfo(user.getId(), user.getName(), user.getEmail());
    }

}
