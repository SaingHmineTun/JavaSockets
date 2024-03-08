package it.saimao.lesson1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientSide {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 6000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg = reader.readLine();
            System.out.println("Read message sent by server");
            System.out.println(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
