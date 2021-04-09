package com.soongjamm.startboot.godOfJava.file_io;

import lombok.SneakyThrows;

import java.io.File;

public class FileSample {
    public static void main(String[] args) {
        String path = "/Users/soongjamm/";

        FileSample sample = new FileSample();
//        sample.mkdir(path);
//        sample.newFile(path);
//        sample.findRoot();
        sample.useFileFilter(path);

    }

    private void useFileFilter(String path) {
        File file = new File(path);
        File[] files = file.listFiles(new TxtFilter());
        for (File f : files) {
            System.out.println(f.getName());
        }
    }

    @SneakyThrows
    private void findRoot() {
        for (File file : File.listRoots()) {
            System.out.println(file.getCanonicalPath());
        }
    }

    @SneakyThrows
    private void newFile(String path) {
        File file = new File(path + "/man.txt");
        file.createNewFile();
        System.out.println(file.exists());
    }

    private void mkdir(String path) {
        File file = new File(path + "/godOfjava");
        file.mkdirs();
    }
}
