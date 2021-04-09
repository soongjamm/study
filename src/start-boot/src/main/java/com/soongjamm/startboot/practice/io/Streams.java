package com.soongjamm.startboot.practice.io;

import lombok.SneakyThrows;

import java.io.*;
import java.nio.CharBuffer;

public class Streams {
    @SneakyThrows
    public static void main(String[] args) {
//        writeAndReadFile();
        onlyStream();
    }

    @SneakyThrows
    // InputStream의 readAllBytes()는 자바9 부터 생겼다.
    private static void onlyStream() {
        String fileName = "/Users/soongjamm/myText.txt";
        FileInputStream fis = new FileInputStream(fileName);
        Integer d;
        while ((d = fis.read()) != -1) {
            System.out.print(d);
        }
        System.out.println();

        // Buffered~ 는 mark() 등을 구현해놓았고 내부적으로 버퍼생성되어서 성능적으로 더 좋은 듯
        BufferedInputStream bis = new BufferedInputStream(fis);
        bis.read();
    }

    private static void writeAndReadFile() throws IOException {
        String fileName = "/Users/soongjamm/myText.txt";
        FileWriter writer = new FileWriter(fileName);
        writer.append("FileWriter Test. successful\n");
        writer.append(writer.getEncoding());
        writer.flush(); // flush() 를 안하면 텍스트가 저장안됌.
        writer.close();
    }
}
