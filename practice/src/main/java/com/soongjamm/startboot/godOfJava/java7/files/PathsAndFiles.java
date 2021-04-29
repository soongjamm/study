package com.soongjamm.startboot.godOfJava.java7.files;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsAndFiles {
    public static void main(String[] args) {
        PathsAndFiles pathsAndFiles = new PathsAndFiles();
        String dir = "/Users/soongjamm/godofjava/nio";
        String dir2 = "/Users/soongjamm/java-study";
//        pathsAndFiles.checkPath(dir);
        pathsAndFiles.checkPath2(dir, dir2);
    }

    private void checkPath(String dir) {
        Path path = Paths.get(dir);
        System.out.println(path.toString());
        System.out.println("GetFileName(): " + path.getFileName());
        System.out.println("GetNameCount(): " + path.getNameCount());
        System.out.println("getParent(): " + path.getParent());
        System.out.println("getRoot(): " + path.getRoot());
    }

    public void checkPath2(String dir1, String dir2) {
        Path path = Paths.get(dir1);
        Path path2 = Paths.get(dir2);
        Path relativize = path.relativize(path2);
        System.out.println("relativize path = " + relativize);

        Path absolute = relativize.toAbsolutePath();
        System.out.println("toAbsolutePath path = " + absolute);

        Path normalized = absolute.normalize();
        System.out.println("normalized path = " + normalized);

        Path resolved = absolute.resolve("godofjava");
        System.out.println("resolved path = " + resolved);


    }
}
