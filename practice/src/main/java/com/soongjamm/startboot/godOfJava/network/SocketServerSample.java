package com.soongjamm.startboot.godOfJava.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerSample {
    public static void main(String[] args) {
        SocketServerSample sample = new SocketServerSample();
        sample.startServer();
    }

    public void startServer() {
        Socket client = null;
        try (ServerSocket server = new ServerSocket(9999)) {
            while (true) {
                System.out.println("server : Waiting for request.");
                client = server.accept();
                System.out.println("server : Accepted.");

                OutputStream os = client.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

                String data = null;
                bw.append("ok!");

                System.out.println("----------------");


                bw.close();
                os.close();
                client.close();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
