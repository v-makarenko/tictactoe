package ru.mai.julia.socket.server;

import ru.mai.julia.Client;
import ru.mai.julia.ObjectLocator;
import ru.mai.julia.User;
import ru.mai.julia.enums.ClientGameState;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    private static int threadMaxNo = 1;
    private static int threadNo;
    LobbyHandler lobbyHandler;
    GameHandler gameHandler;

    private ClientGameState clientGameState = ClientGameState.LOBBY;
    Socket socket;
    private User user;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        threadNo = threadMaxNo;
        threadMaxNo++;
    }

    private void handleGame() throws IOException, ClassNotFoundException, InterruptedException {
        while (true) {
            switch (clientGameState) {
                case GAME:
                    ObjectLocator.getServer().addPendingUser(user);
                    gameHandler = new GameHandler(this);
                    gameHandler.loop();
                    break;
                case LOBBY:
                    lobbyHandler.loop();
                default:
                    break;
            }
        }
    }

    @Override
    public void run() {
        try (InputStream socketInputStream = socket.getInputStream();
             OutputStream socketOutputStream = socket.getOutputStream();
             ObjectOutputStream outputStream = new ObjectOutputStream(
                     socketOutputStream);
             ObjectInputStream inputStream = new ObjectInputStream(
                     socketInputStream);) {
            lobbyHandler = new LobbyHandler(inputStream, outputStream, this);
            gameHandler = new GameHandler(this);
            handleGame();
        } catch (EOFException e) {
            System.out.println("Клиент " + user != null ? user.getUsername() : "" + " отключился");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setClientGameState(ClientGameState clientGameState) {
        this.clientGameState = clientGameState;
    }
}
