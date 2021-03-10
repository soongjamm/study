package com.soongjamm.startboot.validator_json.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    int minDigit;
    int minAlphabet;
    int minSpecialCharacter;
    String specialCharacters;

    @Override
    public void initialize(Password constraintAnnotation) {
        minDigit = constraintAnnotation.minDigit();
        minAlphabet = constraintAnnotation.minAlphabet();
        minSpecialCharacter = constraintAnnotation.minSpecialCharacter();
        StringBuilder sb = new StringBuilder();
        for (char c : constraintAnnotation.specialCharacters()) {
            sb.append(c);
        }
        specialCharacters = sb.toString();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        int numberOfDigit = 0;
        int numberOfAlphabet = 0;
        int numberOfSpecialCharacter = 0;

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                numberOfDigit++;
            }
            if (Character.isAlphabetic(c)) {
                numberOfAlphabet++;
            }
            if (specialCharacters.contains(Character.toString(c))) {
                numberOfSpecialCharacter++;
            }
        }

        if (numberOfDigit >= minDigit &&
                numberOfAlphabet >= minAlphabet &&
                numberOfSpecialCharacter >= minSpecialCharacter ){
            return true;
        }

        return false;
    }
}
