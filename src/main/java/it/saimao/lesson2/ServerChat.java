package it.saimao.lesson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerChat {
    private ServerSocket serverSocket;
    private Scanner scanner;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;

    public static void main(String[] args) throws IOException {
        ServerChat serverChat = new ServerChat();
        serverChat.init();
    }

    private void init() throws IOException {
        serverSocket = new ServerSocket(7500);
        scanner = new Scanner(System.in);
        System.out.println("Server is listening...");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected...");
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            new Thread(() -> {
                String message;
                while (true) {
                    try {
                        message = bufferedReader.readLine();
                        System.out.println("Sent from Client : " + message);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
            new Thread(() -> {
                while (true) {
                    String message = scanner.nextLine();
                    printWriter.println(message);

                }
            }).start();
        }
    }
}
