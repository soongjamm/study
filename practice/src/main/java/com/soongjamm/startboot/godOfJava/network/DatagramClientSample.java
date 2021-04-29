package com.soongjamm.startboot.godOfJava.network;

import java.io.IOException;
import java.net.*;
import java.util.Date;

public class DatagramClientSample {
    public static void main(String[] args) {
        DatagramClientSample sample = new DatagramClientSample();
        sample.sendDatagramSample();
    }

    private void sendDatagramSample() {
        for (int i = 0; i < 10; i++) {
            sendDatagramData("I liked udp " + new Date());
        }
        sendDatagramData("EXIT" + new Date());

    }

    private void sendDatagramData(String data) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress address = InetAddress.getByName("127.0.0.1");
            byte[] buffer = data.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length, address, 9999);
            client.send(packet);
            System.out.println("client send data");
            client.close();
            Thread.sleep(1000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
