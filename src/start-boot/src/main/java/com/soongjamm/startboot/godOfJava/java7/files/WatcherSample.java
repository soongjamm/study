package com.soongjamm.startboot.godOfJava.java7.files;

import lombok.SneakyThrows;

import java.nio.file.*;
import java.util.List;

import static java.nio.file.StandardWatchEventKinds.*;

public class WatcherSample extends Thread {
    String dirName;

    public WatcherSample(String dirName) {
        this.dirName = dirName;
    }

    @SneakyThrows
    public static void main(String[] args) {
        String dirName = "/Users/soongjamm";
        String fileName = "watcher.txt";
        WatcherSample sample = new WatcherSample(dirName);
        sample.setDaemon(true);
        sample.start();
        Thread.sleep(100);
        for (int i = 0; i < 10; i++) {
            sample.fileWriteDelete(dirName, fileName + i);
        }
    }

    @Override
    public void run() {
        System.out.println("### watcher thread is started");
        System.out.format("Dir= %s \n", dirName);
        addWatcher();
    }

    @SneakyThrows
    private void addWatcher() {
        Path dir = Paths.get(dirName);
        WatchService watcher = FileSystems.getDefault().newWatchService();
        WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        while (true) {
            key = watcher.take();
            List<WatchEvent<?>> eventList = key.pollEvents();
            for (WatchEvent<?> event : eventList) {
                Path name = (Path) event.context();
                if (event.kind() == ENTRY_CREATE) {
                    System.out.format("%s created\n", name);
                }
                if (event.kind() == ENTRY_DELETE) {
                    System.out.format("%s deleted\n", name);
                }
                if (event.kind() == ENTRY_MODIFY) {
                    System.out.format("%s modified\n", name);
                }
            }
            key.reset();
        }

    }

    @SneakyThrows
    private void fileWriteDelete(String dirName, String fileName) {
        Path path = Paths.get(dirName, fileName);
        String contents = "Watcher sample";
        StandardOpenOption openOption = StandardOpenOption.CREATE;
        System.out.println("Write " + fileName);
        System.out.println(path.toString());
        Files.write(path, contents.getBytes(), openOption);
        Files.delete(path);
        Thread.sleep(100);
    }
}
