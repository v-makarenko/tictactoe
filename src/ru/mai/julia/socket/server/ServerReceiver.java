package ru.mai.julia.socket.server;


import ru.mai.julia.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerReceiver {
    public static final int PORT = 5000;
    public static final String CHARSET = "UTF8";
    public void init(){
        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
