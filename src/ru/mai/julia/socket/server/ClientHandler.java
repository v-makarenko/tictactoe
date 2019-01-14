package ru.mai.julia.socket.server;

import ru.mai.julia.Client;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    Client client;
    LobbyHandler lobbyHandler;
    GameHandler gameHandlerl;
    GameRulesHandler gameRulesHandler;

    public ClientHandler(Socket socket, Client client) {
        try (InputStream socketInputStream = socket.getInputStream();
             DataInputStream inputStream = new DataInputStream(
                     socketInputStream)) {
            lobbyHandler = new LobbyHandler(inputStream, client);
            gameRulesHandler = new GameRulesHandler(inputStream, client);
            handleGame(inputStream, client);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    private void handleGame(DataInputStream inputStream, Client client) throws IOException {
        while (true) {
            switch (client.getGameState()) {
                case GANE_RULES_SETUP:
                    gameRulesHandler.loop();
                    break;
                case GAME:
                    break;
                case LOBBY:
                default:
                    break;
            }
        }
    }

    public void print() {

    }

    public void printMenu() {
        System.out.println("1 - Начать игр");
    }

    @Override
    public void run() {

    }
}
