package com.soongjamm.startboot.IoPractice;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class IoPractice {
    public static void main(String[] args) throws IOException {
        // 한글은 2바이트로 이루어져서 inputStream의 read() 한번으로 못읽는다.

        // output stream
        OutputStream outputStream = new FileOutputStream("/Users/soongjamm/test.txt");
        byte[] input = "온나나havana".getBytes(StandardCharsets.UTF_8);
        outputStream.write(input);


        // 1. stream read - 1byte
        InputStream inputStream = new FileInputStream("/Users/soongjamm/test.txt");
        char sr = (char) inputStream.read();
        System.out.println("1.+ " + sr);

        // 2. reader read - 2byte
        Reader reader = new FileReader("/Users/soongjamm/test.txt");
        char rr = (char) reader.read();
        System.out.println("3.+ " + rr); // 빠

        byte[] bytes = "test".getBytes(StandardCharsets.UTF_8);
        String str = new String(bytes, 0, 3);
        System.out.println(str);

        // File
        File file = new File("/Users/soongjamm/test.txt");
        file.delete(); //
        if (file.exists()) {
            file.delete();
            System.out.println(file.getName() + " deleted");
        } else {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(input);
            System.out.println("created");
            fos.close();
        }

        // Filter stream - InputStreamReader
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        char isr = (char) inputStreamReader.read();
        System.out.println("2.+ " + isr);

        // Filter stream - OutputStreamWriter
        OutputStream os = new FileOutputStream("/Users/soongjamm/test2.txt");
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(os);
        outputStreamWriter.append(" add this."); // 이게 OutputStreamWriter 를 이용해 Buffer -> 문자 스트림 변경했을 때 장
        String data = "바이트 출력 스트림 to 문자 출력 스트림";
        outputStreamWriter.write(data);

        outputStreamWriter.flush();
        outputStreamWriter.close();

        // Filter stream - BufferdInputStream ~ 성능테스트
        InputStream is = new FileInputStream("/Users/soongjamm/testLongText.txt");
        long start;
        long end;
        start = System.currentTimeMillis();
        while (is.read() != -1) {
        }
        end = System.currentTimeMillis();
        System.out.println("-------");
        System.out.println("InputStream read : " + (end - start));

        is = new FileInputStream("/Users/soongjamm/testLongText.txt");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
        start = System.currentTimeMillis();
        while (bufferedInputStream.read() != -1) {
        }
        end = System.currentTimeMillis();
        System.out.println("BufferedInputStream read : " + (end - start));
        System.out.println("-------");

        // Filter stream - DataInputOutputStream

        // fos 로 파일만들어 내보내기
        FileOutputStream fos = new FileOutputStream("/Users/soongjamm/testDataStream.txt");
        DataOutputStream dos = new DataOutputStream(fos);

        dos.writeUTF("soongjamm");
        dos.writeBoolean(true);
        dos.writeDouble(12.34);
        dos.writeUTF("finished");

        dos.flush();
        dos.close();
        fos.close();

        // 읽어들여서 출력
        FileInputStream fis = new FileInputStream("/Users/soongjamm/testDataStream.dat");
        DataInputStream dis = new DataInputStream(fis);
        String name = dis.readUTF();
        boolean graduated = dis.readBoolean();
        double score = dis.readDouble();
        String status = dis.readUTF();
        System.out.println(name + " graduated ? " + graduated + " / score : " + score + " and " + status);


        // Filter stream - PrintStream
        // 출력하기위해 출력스트림
        OutputStream outputStream1 = new FileOutputStream("/Users/soongjamm/testPrintStream.txt");
        PrintStream printStream = new PrintStream(outputStream1);
        printStream.printf("텍스트를 입력하고\n");
        printStream.printf("파일인풋스트림을 받았으니 파일에 저장되겠지");


    }
}
