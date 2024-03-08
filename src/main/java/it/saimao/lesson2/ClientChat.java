package it.saimao.lesson2;

import javax.naming.ldap.SortKey;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientChat {

    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private Scanner scanner;

    public static void main(String[] args) throws IOException {

        ClientChat clientChat = new ClientChat();
        clientChat.init();
    }

    private void init() throws IOException {
        Socket socket = new Socket("127.0.0.1", 7500);
        printWriter = new PrintWriter(socket.getOutputStream(), true);
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        scanner = new Scanner(System.in);
        startWriterThread();
        startReaderThread();
    }

    private void startReaderThread() {
        new Thread(() -> {
            String message;
            while (true) {
                try {
                    message = bufferedReader.readLine();
                    System.out.println("Sent from Server Server : " + message);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private void startWriterThread() {
        new Thread(() -> {
            String message;
            while (true) {
                message = scanner.nextLine();
                printWriter.println(message);

            }
        }).start();
    }
}
