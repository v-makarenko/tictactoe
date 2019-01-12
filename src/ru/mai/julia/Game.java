package ru.mai.julia;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private GameRules gameRules;
    private Field field;
    private List<User> players = new ArrayList<>();


    public void start() {
        // TODO исправить
//        gameRules = new GameRules();
        field = new Field(gameRules.getFieldSize());
        gameLoop();
    }


    public void gameLoop() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
//            printMenu();
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            field.setCellState(x, y, CellState.X);
            printField();
        }
    }


    private void printMenu() {
        System.out.println("1 - Поставить символ");
    }

    // TODO разобрать пример через Stream
    private void printField() {
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

}
