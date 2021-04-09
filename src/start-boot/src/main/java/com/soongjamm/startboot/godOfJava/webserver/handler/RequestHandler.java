package com.soongjamm.startboot.godOfJava.webserver.handler;

import com.soongjamm.startboot.godOfJava.webserver.RequestDTO;
import com.soongjamm.startboot.godOfJava.webserver.manager.RequestManager;
import com.soongjamm.startboot.godOfJava.webserver.manager.ResponseManager;

import java.io.IOException;
import java.net.Socket;

public class RequestHandler extends Thread {

    private Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        RequestManager requestManager = new RequestManager(socket);
        RequestDTO requestDTO = null;
        try {
            requestDTO = requestManager.readRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ResponseManager responseManager = new ResponseManager(socket, requestDTO);
        try {
            responseManager.writeRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
