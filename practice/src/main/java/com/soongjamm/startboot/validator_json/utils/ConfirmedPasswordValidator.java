package com.soongjamm.startboot.validator_json.utils;

import com.soongjamm.startboot.validator_json.domain.NotEqualToPasswordException;
import com.soongjamm.startboot.validator_json.domain.RegisterRequestDto;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class ConfirmedPasswordValidator {

    @SneakyThrows
    public void validate(RegisterRequestDto dto) {
        if(!dto.getPassword().equals(dto.getConfirmedPassword())) {
            throw new NotEqualToPasswordException("Password is not equal to Confirmed Password");
        }
    }
}
