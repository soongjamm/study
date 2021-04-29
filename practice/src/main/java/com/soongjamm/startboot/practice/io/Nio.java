package com.soongjamm.startboot.practice.io;

import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Nio {
    public static void main(String[] args) {
        Nio nio = new Nio();
        String filename = "/Users/soongjamm/nio.txt";

        nio.write(filename);
        nio.read(filename);
    }

    @SneakyThrows
    private void read(String filename) {
        FileChannel channel = new FileInputStream(filename).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        System.out.println("position before flip - " + buffer.position());
        buffer.flip();
        System.out.println("position after flip - " + buffer.position());
        System.out.println("buffer limit - " + buffer.limit());

        while (buffer.hasRemaining()) {
            if (buffer.position() == 10) {
                buffer.mark();
            }
            System.out.print((char) buffer.get() + "" + buffer.position() + " ");
        }

        reset(buffer);

        channel.close();
    }

    private void reset(ByteBuffer buffer) {
        System.out.println();
        buffer.reset();
        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get());
        }
    }

    @SneakyThrows
    private void write(String filename) {
        FileChannel channel = new FileOutputStream(filename).getChannel();
        byte[] bytes = "this.is.nio.test".getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        channel.write(buffer);
        channel.close();
    }
}
