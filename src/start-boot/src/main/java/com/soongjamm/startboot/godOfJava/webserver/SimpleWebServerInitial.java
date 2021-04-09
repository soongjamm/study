package com.soongjamm.startboot.godOfJava.webserver;

import lombok.SneakyThrows;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleWebServerInitial {
    public static void main(String[] args) {
        SimpleWebServerInitial server = new SimpleWebServerInitial();
        int port = 9000;
        server.start(port);
    }

    private final int BUFFER_SIZE = 2048;

    @SneakyThrows
    private void start(int port) {
        try (ServerSocket server = new ServerSocket(port)) {
            Socket socket = server.accept();
            while (true) {

                InputStream request = new BufferedInputStream(socket.getInputStream());
                byte[] receivedBytes = new byte[BUFFER_SIZE];
                request.read(receivedBytes);
                String requestData = new String(receivedBytes).trim();
                System.out.println("RequestData = \n" + requestData);
                System.out.println("---------");

                PrintStream response = new PrintStream(socket.getOutputStream());
                response.println("HTTP/1.1 200 OK ");
                response.println("Content-type: text/html");
                response.println();
                response.print("It is working");
                response.flush();
                response.close();

                socket.close();
            }
        }
    }
}
