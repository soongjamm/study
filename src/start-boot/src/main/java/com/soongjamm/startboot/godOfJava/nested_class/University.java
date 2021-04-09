package com.soongjamm.startboot.godOfJava.nested_class;

public class University {
    static String code = "1234";
    String name = "mj";
    // static nested class
    // HighSchool 의 Student와 구분하기 위해 사용
    static class Student {
        private String 전공;

        public String get전공() {
            return 전공;
        }

        public void set전공(String 전공) {
            this.전공 = 전공;
        }
    }


}
