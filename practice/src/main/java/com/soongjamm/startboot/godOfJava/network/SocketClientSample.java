package com.soongjamm.startboot.godOfJava.network;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class SocketClientSample {
    public static void main(String[] args) {
        SocketClientSample sample = new SocketClientSample();
        sample.sendSocketSample();
    }

    private void sendSocketSample() {
        for (int i = 0; i < 10; i++) {
            sendSocketData("I liked java at " + new Date());
        }
        sendSocketData("EXIT");
    }

    private void sendSocketData(String data) {
        try (Socket socket = new Socket("localhost", 9999)) {

            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String getResponse = "";
            StringBuilder stringBuilder = new StringBuilder();


            while ((getResponse = br.readLine()) != null) {
                stringBuilder.append(getResponse);
            }
            System.out.println(stringBuilder.toString());

            br.close();
            is.close();


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
