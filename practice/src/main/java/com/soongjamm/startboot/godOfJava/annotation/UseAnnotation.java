package com.soongjamm.startboot.godOfJava.annotation;

import java.lang.reflect.Field;

public class UseAnnotation {
    public static void main(String[] args) {
        UseAnnotation annotation = new UseAnnotation();
        annotation.play();
    }
    private void play() {
        validate(sayMooyaho);
    }

    private void validate(String sayMooyaho) {
        Field declaredField = UseAnnotation.class.getDeclaredFields()[0];
        CustomAnnotation annotation = declaredField.getAnnotation(CustomAnnotation.class);
        System.out.println(annotation.mustbe().equalsIgnoreCase(sayMooyaho));
    }

    @CustomAnnotation(mustbe = "mooyaho")
    String sayMooyaho = "mooyaho";
}
