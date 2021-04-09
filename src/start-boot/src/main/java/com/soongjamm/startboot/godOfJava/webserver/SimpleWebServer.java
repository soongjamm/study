package com.soongjamm.startboot.godOfJava.webserver;

import com.soongjamm.startboot.godOfJava.webserver.handler.RequestHandler;
import lombok.SneakyThrows;

import java.net.ServerSocket;
import java.net.Socket;

public class SimpleWebServer {
    public static void main(String[] args) {
        SimpleWebServer server = new SimpleWebServer();
        server.run(9000);
    }

    @SneakyThrows
    public void run(int port) {
        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                Socket socket = server.accept();
                RequestHandler requestHandler = new RequestHandler(socket);
                requestHandler.start();
            }
        }

    }
}
