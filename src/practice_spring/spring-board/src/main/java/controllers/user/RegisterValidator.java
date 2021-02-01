package controllers.user;


import dto.RegisterUserDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class RegisterValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterUserDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmedPassword", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
    }
}
