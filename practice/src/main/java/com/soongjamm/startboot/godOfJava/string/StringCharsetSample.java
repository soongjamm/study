package com.soongjamm.startboot.godOfJava.string;

import java.io.UnsupportedEncodingException;

public class StringCharsetSample {
    public static void main(String[] args) {
        StringCharsetSample sample = new StringCharsetSample();
        sample.convert();

        System.out.println();
        sample.convertUTF16();

        System.out.println();
        sample.convertEucKr();
    }

    public void convert() {
        // 기본이 UTF-8로 되어있다.
        try {

            String korean = "자바의갓짱짱맨";

            byte[] array1 = korean.getBytes();
            for (byte b : array1) {
                System.out.print(b + " ");
            }
            System.out.println();

            String korean2 = new String(array1);
            System.out.println(korean2);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void convertUTF16() {
        try {
            // 인코딩 캐릭터셋과 디코딩 캐릭터셋이 일치해야 올바른 문자를 볼 수 있다.
            String korean = "자바의갓짱짱맨";
            byte[] array1 = korean.getBytes("UTF-16");
            printByteArray(array1);
            String korean2 = new String(array1, "UTF-16");
            System.out.println(korean2);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void convertEucKr() {
        try {
            // 인코딩 캐릭터셋과 디코딩 캐릭터셋이 일치해야 올바른 문자를 볼 수 있다.
            String korean = "자바의갓짱짱맨";
            byte[] array1 = korean.getBytes("EUC-KR");
            printByteArray(array1);
            String korean2 = new String(array1, "EUC-KR");
            System.out.println(korean2);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void printByteArray(byte[] array) {

            for (byte b : array) {
                System.out.print(b + " ");
            }
            System.out.println();

    }


}
