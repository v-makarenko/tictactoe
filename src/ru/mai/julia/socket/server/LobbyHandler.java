package ru.mai.julia.socket.server;

import ru.mai.julia.Client;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.DataInputStream;

public class LobbyHandler {
    private DataInputStream inputStream;
    private Client client;

    public LobbyHandler(DataInputStream dataInputStream, Client client) {
        this.client = client;
        this.inputStream = dataInputStream;

    }

    public void print() {

    }

    public void printMenu() {
        System.out.println("1 - Начать игр");
    }
}
