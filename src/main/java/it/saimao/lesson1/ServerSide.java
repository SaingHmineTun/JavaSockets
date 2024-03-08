package it.saimao.lesson1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSide {
    public static void main(String[] args) {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(6000);
            System.out.println("Server is listening...");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
//                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                String read = reader.readLine();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                writer.write("Hello Client");
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}