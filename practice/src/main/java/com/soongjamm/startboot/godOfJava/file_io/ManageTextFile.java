package com.soongjamm.startboot.godOfJava.file_io;

import java.io.*;
import java.util.Scanner;

import static java.io.File.separator;

public class ManageTextFile {
    public static void main(String[] args) {
        ManageTextFile manager = new ManageTextFile();
        int numberCount = 10;

        String path = separator + "Users" + separator + "soongjamm" +
                separator + "godofjava" + separator + "text" + separator;
        String filename = "numbers.txt";
        File file = new File(path);

        if (!file.exists()) {
            file.mkdirs();
        }

        manager.writeFile(path + filename, numberCount);
        manager.readFile(path + filename);
        manager.readFileWithScanner(path + filename);
    }

    private void readFileWithScanner(String filename) {
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
            System.out.println("read with scanner success");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readFile(String filename) {

        try (FileReader fr = new FileReader(filename);
             BufferedReader br = new BufferedReader(fr)) {

            br.lines().forEach(System.out::println);

            System.out.println("read success");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFile(String filename, int numberCount) {
        FileWriter fileWriter = null;
        BufferedWriter bw = null;

        try {
            fileWriter = new FileWriter(filename, false);
            bw = new BufferedWriter(fileWriter);
            for (int i = 0; i < numberCount; i++) {
                bw.write(Integer.toString(i));
                bw.newLine();
            }
            System.out.println("Write success");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
