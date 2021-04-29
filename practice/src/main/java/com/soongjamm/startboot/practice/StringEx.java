package com.soongjamm.startboot.practice;

import java.nio.charset.StandardCharsets;

public class StringEx {
    public static void main(String[] args) {
        // 왜 스트링 내부가 byte[] 일까
        System.out.println("ㄱ".getBytes(StandardCharsets.UTF_8)[0]);
        System.out.println("ㄱ".getBytes(StandardCharsets.UTF_16)[0]);
        System.out.println("ㄱ".getBytes(StandardCharsets.US_ASCII)[0]);
    }
}
