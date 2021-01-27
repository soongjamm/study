package controller;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import spring.RegisterRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterRequestValidator implements Validator {
    private static final String emailRegExp =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Pattern pattern;

    public RegisterRequestValidator() {
        pattern = Pattern.compile(emailRegExp);
    }


    // 이 예제에서는 supports() 메서드를 직접 실행하지 않지만
    // 스프링 MVC가 자동으로 검증 기능을 수행하도록 설정하려면 이 메서드를 올바르게 구현해야 함.
    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterRequest registerRequest = (RegisterRequest) target;
        if(registerRequest.getEmail() == null || registerRequest.getEmail().trim().isEmpty()) {
            errors.rejectValue("email", "required");
        } else {
            Matcher matcher = pattern.matcher(registerRequest.getEmail());
            if(!matcher.matches()) {
                errors.rejectValue("email", "bad");
            }
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
        ValidationUtils.rejectIfEmpty(errors, "password", "required");
        ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "required");
        if(!registerRequest.getPassword().isEmpty()) {
            if(!registerRequest.isPasswordEqualToConfirmPassword()) {
                errors.rejectValue("confirmPassword", "nomatch");
            }
        }

    }
}
