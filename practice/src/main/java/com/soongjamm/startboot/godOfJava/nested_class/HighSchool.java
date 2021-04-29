package com.soongjamm.startboot.godOfJava.nested_class;

public class HighSchool {

    // static nested class
    // University 의 Student와 구분하기 위해 사용
    static class Student {
        private String 출석번호;

        public String get출석번호() {
            return 출석번호;
        }

        public void set출석번호(String 출석번호) {
            this.출석번호 = 출석번호;
        }

    }


}
