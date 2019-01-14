package ru.mai.julia;


import ru.mai.julia.enums.CellState;

// Объект пользователя (игрока)
public class User {
    private String username;
    private CellState userSymbol;
    private GameRules preferredGameRules;

    private int winCount;
    private int loseCount;

    public User(String username) {
        this.username = username;
    }

    public void win() {
        winCount++;
    }

    public void lose() {
        loseCount++;
    }



}
