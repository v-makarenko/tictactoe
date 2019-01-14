package ru.mai.julia.socket.server;

import ru.mai.julia.Client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;

public class GameRulesHandler {
    DataInputStream inputStream;
    Client client;
    public GameRulesHandler(DataInputStream dataInputStream, Client client) {
        this.client = client;
        this.inputStream = dataInputStream;
    }

    public void loop(){
        while(true) {
            try {
                String menuChoise = inputStream.readUTF();
                System.out.println(menuChoise);
                System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
