package com.soongjamm.startboot.godOfJava.serialize_nio;

import lombok.SneakyThrows;

import java.io.*;

import static java.io.File.separator;

public class ManageObject {
    public static void main(String[] args) {
        ManageObject manager = new ManageObject();
        String fullPath = separator + "Users" + separator + "soongjamm" +separator + "godofjava" + separator + "text" + separator + "serial.obj";
        SerialDTO dto = new SerialDTO("GodOfJavaBook", 1, true, 100);
        manager.saveObject(fullPath, dto);
        manager.loadObject(fullPath);
    }

    @SneakyThrows
    private void saveObject(String fullPath, SerialDTO dto) {
        File file = new File(fullPath);
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(dto);
        oos.close();

        System.out.println("write success");
    }

    @SneakyThrows
    private void loadObject(String fullPath) {
        File file = new File(fullPath);
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        SerialDTO dto = (SerialDTO) ois.readObject();
        System.out.println(dto);
    }
}
