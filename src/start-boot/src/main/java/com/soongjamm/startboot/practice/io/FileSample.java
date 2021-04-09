package com.soongjamm.startboot.practice.io;

import lombok.SneakyThrows;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class FileSample {
    public static void main(String[] args) {
        getModifiedDate();
        listRoots();
        findFileWithFilter();
    }

    @SneakyThrows
    private static void listRoots() {
        File[] files = File.listRoots();
        for (File file : files) {
            System.out.println(file.getCanonicalFile());
        }
    }

    @SneakyThrows
    private static void findFileWithFilter() {
        File file = new File(
                File.separator + "Users" +
                        File.separator + "soongjamm" +
                        File.separator
        );

        System.out.println("--- FilenameFilter ---");
        File[] files = file.listFiles((dir, name) -> name.startsWith("test"));
        for (File f : files) {
            System.out.println(f.getCanonicalFile());
        }

        System.out.println("\n--- FileFilter ---");
        File[] files2 = file.listFiles((pathname -> pathname.canExecute()));
        for (File f : files2) {
            System.out.println(f.getCanonicalFile());
        }
    }

    private static void getModifiedDate() {
        File file = new File(
                File.separator + "Users" +
                        File.separator + "soongjamm" +
                        File.separator + "test2.txt"
        );
        Date date = new Date(file.lastModified());
        System.out.println(date);
    }
}
