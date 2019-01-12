package ru.mai.julia;

import java.util.Scanner;

public class Client {
    private final Scanner scanner = new Scanner(System.in);

    public void printMenu() {
        System.out.println("1 - Начать игру");
        System.out.println("2 - Список победителей");
        System.out.println("3 - Список игроков, ожидающих игру");
        System.out.println("4 - Выйти");
    }

    public void getInfo() {
        User user;
        System.out.print("Введите имя: ");
        user = new User(scanner.nextLine().trim());


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

//        OpponentCount prefferedOpponentCount = null;
//        while (prefferedOpponentCount == null) {
//            System.out.print("Введите количество символов в ряд для победы (3, 4, 5):  ");
//            prefferedOpponentCount = OpponentCount.parse(scanner.nextLine());
//        }

        GameRules gameRules = new GameRules(prefferedOpponentCount, fieldSize, winLineLength);

    }

    public void getUserRating(){}

    public void getUsersPending(){}

    public void gameLoop(){
        int userInput = 0;
        while (userInput != 4){
            try {
                userInput = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e){
                System.out.println("Введите корректную команду");
                continue;
            }
            switch (userInput){
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

}
