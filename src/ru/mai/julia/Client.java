package ru.mai.julia;

import ru.mai.julia.enums.*;
import ru.mai.julia.socket.Coordinates;
import ru.mai.julia.socket.GameStatus;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static String HOST = "localhost";
    public static int PORT = 5000;

    private final Scanner scanner = new Scanner(System.in);
    private ClientGameState gameState = ClientGameState.GANE_RULES_SETUP;

    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private User currentUser;

    public Client() throws IOException {
        socket = new Socket(HOST, PORT);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    public void printMenu() {
        System.out.println("1 - Начать игру");
        System.out.println("2 - Список победителей");
        System.out.println("3 - Список игроков, ожидающих игру");
        System.out.println("4 - Изменить желаемые правила игры");
        System.out.println("5 - Выйти");
    }

    public void getInfo() throws IOException {
        System.out.print("Введите имя: ");
        currentUser = new User(scanner.nextLine().trim());

        OpponentCount prefferedOpponentCount = null;
        while (prefferedOpponentCount == null) {
            System.out.print("Введите количество соперников (1,2): ");
            prefferedOpponentCount = OpponentCount.parse(scanner.nextLine());
        }

        FieldSize fieldSize = null;
        WinLineLength winLineLength = null;
        while (fieldSize == null) {
            System.out.println("Введите размер поля: 1 - 3x3, 2 - 9x9, 3 - 15x15");
            String input = scanner.nextLine();
            fieldSize = FieldSize.parse(input);
            winLineLength = WinLineLength.parse(input);
        }

        GameRules gameRules = new GameRules(prefferedOpponentCount, fieldSize, winLineLength);
        currentUser.setPreferredGameRules(gameRules);

        outputStream.writeUTF("setUser");
        outputStream.writeObject(currentUser);
    }

    public void getUserRating() throws IOException, ClassNotFoundException {
        outputStream.writeUTF("getUserRating");
        outputStream.flush();
        List<User> userList = (List<User>) inputStream.readObject();
        System.out.printf("%15s %15s %15s\n", "Имя", "Победы", "Поражения");
        System.out.println();
        for (User user : userList) {
            user.printRating();
        }
    }

    public void getUsersPending() throws IOException, ClassNotFoundException {
        outputStream.writeUTF("getUsersPending");
        outputStream.flush();
        List<User> userList = (List<User>) inputStream.readObject();
        System.out.printf("%15s %15s\n", "Имя", "Правила");
        for (User user : userList) {
            user.printGameParams();
        }
    }

    public void gameLoop() throws IOException, ClassNotFoundException {
        int userInput = 0;
        getInfo();
        while (userInput != 5) {
            printMenu();
            try {
                userInput = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Введите корректную команду");
                continue;
            }
            switch (userInput) {
                case 1:
                    startGame();
                    break;
                case 2:
                    getUserRating();
                    break;
                case 3:
                    getUsersPending();
                    break;
                case 4:
                    getInfo();
                    break;
                default:
                    System.out.println("Введите одну из предложенных команд");

            }
        }
    }

    private void startGame() throws IOException, ClassNotFoundException {
        outputStream.writeUTF("startGame");
        outputStream.flush();
        while (true) {
            GameStatus gameStatus = (GameStatus) inputStream.readObject();
            System.out.println(gameStatus);

            printField(gameStatus.getField());

            if (gameStatus.getWinner() != null) {
                System.out.println("Игра окончена");
                System.out.println("Победитель: " + gameStatus.getWinner().getUsername());
                if(gameStatus.getWinner().equals(currentUser)){
                    System.out.println("Вы победили!");
                }
                return;
            } else if(gameStatus.getDroppedUser()!=null){
                System.out.println(gameStatus.getDroppedUser().getUsername() + " вышел из игры и проиграл. Вы возвращены в режим ожидания.");
                return;
            } else {
                if (gameStatus.isYourTurn()) {
                    System.out.println("Введите координаты хода через пробел");
                    System.out.println("Ваш ход: ");
                    String turn;
                    int x;
                    int y;
                    while (true) {
                        turn = scanner.nextLine();
                        if (turn.split(" ").length == 2) {
                            x = Integer.parseInt(turn.split(" ")[0]);
                            y = Integer.parseInt(turn.split(" ")[1]);
                            if (gameStatus.getField().getCellState(x-1, y-1) == CellState.EMPTY) {
                                break;
                            } else {
                                System.out.println("Клетка уже занята!");
                            }
                        }
                    }
                    Coordinates coordinates = new Coordinates(x, y);
                    outputStream.writeObject(coordinates);
                }
            }
        }
    }


    // TODO разобрать пример через Stream
    private void printField(Field field) {
        for (int y = 0; y < field.getHeight(); y++) {
            for (int x = 0; x < field.getWidth(); x++) {
                System.out.print(field.getCellState(x, y));

                if (x < field.getWidth() - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (y < field.getHeight() - 1) {
                for (int x = 0; x < field.getWidth() * 2 - 1; x++) {
                    System.out.print("—");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Client().gameLoop();
    }
}
