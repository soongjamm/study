package com.soongjamm.startboot.godOfJava.java7.files;

import lombok.SneakyThrows;

import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileManager {

    @SneakyThrows
    public void copyMoveDelete(Path fromPath, String fileName) {
        Path toPath= fromPath.toAbsolutePath().getParent();

        // make dir if not exists
        Path copyPath=toPath.resolve("copied");
        if(!Files.exists(copyPath)) {
            Files.createDirectories(copyPath);
        }

        // copy file
        Path copiedFilePath = copyPath.resolve(fileName);
        StandardCopyOption copyOption =  StandardCopyOption.REPLACE_EXISTING;
        Files.copy(fromPath, copiedFilePath, copyOption);

        // read copied file
        System.out.println("((((((copied file contents)))))))");
        readFile(copiedFilePath);

        // move file
        Path movedFilePath = Files.move(copiedFilePath, copyPath.resolve("moved.txt"), copyOption);

        //delete files
        Files.delete(movedFilePath);
        Files.delete(copyPath);
    }




    public List<String> getContents() {
        List<String> contents = new ArrayList<>();
        contents.add("안녕");
        contents.add("아니");
        contents.add("안녕못해");
        contents.add("해라");
        contents.add("안녕~");
        contents.add("date : " + new Date());
        return contents;
    }

    public Path writeFile(Path path) throws Exception {
        Charset charset = Charset.forName("EUC-KR");
        List<String> contents = getContents();
        StandardOpenOption openOption = StandardOpenOption.CREATE;
        return Files.write(path, contents, charset, openOption);
    }

    public void readFile(Path path) throws Exception {
        Charset charset = Charset.forName("EUC-KR");
        System.out.println("Path = " + path);
        List<String> fileContents = Files.readAllLines(path, charset);
        for (String tempContents : fileContents) {
            System.out.println(tempContents);
        }
        System.out.println();
    }

    @SneakyThrows
    public Path writeAndRead(String fileName) {
        Path returnPath = null;
        Path path = Paths.get(fileName);
        returnPath = writeFile(path);
        System.out.println("****created file contents*****");
        readFile(returnPath);

        return returnPath;
    }

    public static void main(String[] args) {
        FileManager manager = new FileManager();
        String fileName = "AboutThisBook.txt";
        Path from = manager.writeAndRead(fileName);
        manager.copyMoveDelete(from, fileName);
    }
}
