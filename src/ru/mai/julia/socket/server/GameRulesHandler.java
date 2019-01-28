package ru.mai.julia.socket.server;

import ru.mai.julia.Client;

import java.io.*;

public class GameRulesHandler {
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;
    Client client;

    public GameRulesHandler(ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) {
        this.client = client;
        this.inputStream = objectInputStream;
        this.inputStream = objectInputStream;
    }

    public void loop() {
        while (true) {
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
