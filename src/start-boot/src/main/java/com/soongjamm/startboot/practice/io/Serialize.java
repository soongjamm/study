package com.soongjamm.startboot.practice.io;

import lombok.SneakyThrows;

import java.io.*;

public class Serialize {
    public static void main(String[] args) {
        Serialize serialize = new Serialize();
        serialize.run();
    }

    @SneakyThrows
    private void run() {
//        Obj soongjamm = write();
        read();
    }

    private Obj write() throws IOException {
        FileOutputStream fos = new FileOutputStream("/Users/soongjamm/mySerialized.obj");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Obj soongjamm = new Obj("soongjamm");
        oos.writeObject(soongjamm);
        return soongjamm;
    }

    private Obj read() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("/Users/soongjamm/mySerialized.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        return (Obj) ois.readObject();
    }
}
