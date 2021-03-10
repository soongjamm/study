package com.soongjamm.startboot.validator_json.domain;

import com.soongjamm.startboot.validator_json.utils.Password;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
public class RegisterRequestDto {
    @NotBlank(message = "you must input email.")
    @Email
    private String email;
    @NotBlank(message = "you must input name.")
    private String name;
    @NotEmpty(message = "you must input password.")
    @Size(min = 6, max = 30, message = "password must be between 6 and 30")
    @Password(minDigit = 1, minAlphabet = 1, minSpecialCharacter = 1, specialCharacters = {'*', '!'})
    private String password;
    @NotEmpty(message = "you must input confirmed password.")
    private String confirmedPassword;
}
