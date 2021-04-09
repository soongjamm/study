package com.soongjamm.startboot.godOfJava.webserver.manager;

import com.soongjamm.startboot.godOfJava.webserver.RequestDTO;
import lombok.SneakyThrows;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class RequestManager {
    private Socket socket;
    private final int BUFFER_SIZE = 2048;

    public RequestManager(Socket socket) {
        this.socket = socket;
    }

    public RequestDTO readRequest() throws IOException {
        String request;
        String[] requestInfo;
        byte[] bytes = new byte[BUFFER_SIZE];

        InputStream is = new BufferedInputStream(socket.getInputStream());
        is.read(bytes);

        request = new String(bytes).trim();
        System.out.println(request);
        System.out.println("====================");

        requestInfo = request.split("\n")[0].split(" ");
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setRequestMethod(requestInfo[0]);
        requestDTO.setUri(requestInfo[1]);
        requestDTO.setHttpVersion(requestInfo[2]);

        return requestDTO;
    }
}
