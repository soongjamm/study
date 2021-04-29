package com.soongjamm.startboot.godOfJava.webserver.manager;

import com.soongjamm.startboot.godOfJava.webserver.RequestDTO;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;

public class ResponseManager {

    private Socket socket;
    private RequestDTO dto;


    public ResponseManager(Socket socket, RequestDTO dto) {
        this.socket = socket;
        this.dto = dto;
    }

    public void writeRequest() throws IOException {
        PrintStream ps = new PrintStream(socket.getOutputStream());

        ps.println(dto.getHttpVersion() + " 200 OK");
        ps.println("Content-Type: text/html");
        ps.println();
        if (dto.getUri().equals("/")) {
            ps.print("hello world");
        }
        if (dto.getUri().equals("/today")) {
            ps.print(new Date().toString());
        }
        ps.flush();
        ps.close();
    }
}
