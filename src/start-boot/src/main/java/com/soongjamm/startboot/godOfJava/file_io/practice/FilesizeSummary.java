package com.soongjamm.startboot.godOfJava.file_io.practice;

import java.io.File;
import java.text.DecimalFormat;

public class FilesizeSummary {
    public static void main(String[] args) {
        FilesizeSummary sample = new FilesizeSummary();
        String path = "/Users/soongjamm/smart_roasting/";
        long sum = sample.printFileSize(path);
        System.out.println("\n" + path + "'s total size = " + sample.convertFileLength(sum));
    }

    private String convertFileLength(long fileLength) {
        DecimalFormat decimalFormat = new DecimalFormat("0.000");

        double result = 0;
        if (fileLength > 1024 * 1024 * 1024) {
            result = (double) (fileLength) / (1024 * 1024 * 1024);
            return decimalFormat.format(result) + " gb";
        }

        if (fileLength > 1024 * 1024) {
            result = (double) (fileLength) / (1024 * 1024);
            return decimalFormat.format(result) + " mb";
        }

        if (fileLength > 1024) {
            result = (double) (fileLength) / (1024);

            return decimalFormat.format(result) +  " kb";
        }

        result = (double) (fileLength);
        return decimalFormat.format(result) + " byte";
    }

    private long printFileSize(String dirName) {
        File dir = new File(dirName);
        long sum = 0;

        if (!dir.isDirectory()) {
            System.out.println("not directory");
            return 0L;
        }

        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                String path = file.getAbsolutePath();
                long fileLength = file.length();
                System.out.println(path + " : " + convertFileLength(file.length()));
                sum += fileLength;
            } else {
                String tempDirName = file.getAbsolutePath();
                long fileLength = printFileSize(file.getAbsolutePath());
                System.out.println(" [" + tempDirName + "] : " + convertFileLength(file.length()));
                sum += fileLength;
            }
        }
        return sum;
    }
}
