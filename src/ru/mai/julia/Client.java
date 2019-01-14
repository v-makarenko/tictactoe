package ru.mai.julia;

import ru.mai.julia.enums.ClientGameState;
import ru.mai.julia.socket.client.ClientSender;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static String HOST = "localhost";
    public static int PORT = 5000;

    private final Scanner scanner = new Scanner(System.in);
    private ClientGameState gameState = ClientGameState.GANE_RULES_SETUP;
    private ClientSender clientSender;

    public void printMenu() {
        System.out.println("1 - Начать игру");
        System.out.println("2 - Список победителей");
        System.out.println("3 - Список игроков, ожидающих игру");
        System.out.println("4 - Выйти");
    }

    public void getInfo() {


//        GameRules gameRules = new GameRules(prefferedOpponentCount, fieldSize, winLineLength);

    }

    public void getUserRating() {
    }

    public void getUsersPending() {
    }

    public void gameLoop() {
        int userInput = 0;
        while (userInput != 4) {
            try {
                userInput = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Введите корректную команду");
                continue;
            }
            switch (userInput) {
                case 1:
                    getInfo();
                    break;
                case 2:
                    getUserRating();
                    break;
                case 3:
                    getUsersPending();
                    break;
                default:
                    System.out.println("Введите одну из предложенных команд");

            }
        }
    }

    public ClientGameState getGameState() {
        return gameState;
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(HOST, PORT);
        DataOutputStream outputStream =  new DataOutputStream(socket.getOutputStream());
        outputStream.writeUTF("Hello");

    }
}
