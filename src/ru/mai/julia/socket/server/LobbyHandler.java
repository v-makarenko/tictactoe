package ru.mai.julia.socket.server;

import ru.mai.julia.ObjectLocator;
import ru.mai.julia.User;
import ru.mai.julia.enums.ClientGameState;

import java.io.*;

public class LobbyHandler {
    private final ClientHandler clientHandler;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public LobbyHandler(ObjectInputStream objectInputStream,
                        ObjectOutputStream objectOutputStream, ClientHandler clientHandler) {
        this.inputStream = objectInputStream;
        this.outputStream = objectOutputStream;
        this.clientHandler = clientHandler;
    }

    public void loop() throws IOException, ClassNotFoundException {
        while (true) {
            String input = inputStream.readUTF();
            switch (input) {
                case "getUsersPending":
                    System.out.println("getUsersPending");
                    outputStream.reset();
                    outputStream.writeObject(ObjectLocator.getServer().getUsersPendingList());
                    break;
                case "getUserRating":
                    System.out.println("getUserRating");
                    outputStream.reset();
                    outputStream.writeObject(ObjectLocator.getServer().getUsersEverPlayedList());
                    break;
                case "startGame":
                    System.out.println("startGame");
                    clientHandler.setClientGameState(ClientGameState.GAME);
                    return;
                case "setUser":
                    System.out.println("setUser");
                    User user = (User) inputStream.readObject();
                    user.setInputStream(inputStream);
                    user.setOutputStream(outputStream);
                    clientHandler.setUser(user);
                    clientHandler.getUser().printRating();
                    break;
            }

        }

    }
}
