package com.soongjamm.startboot.validator_json.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
    String message() default "The password is not satisfied with the condition.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int minDigit() default 0;
    int minAlphabet() default 0;
    int minSpecialCharacter() default 0;
    char[] specialCharacters() default {'*'};

}
