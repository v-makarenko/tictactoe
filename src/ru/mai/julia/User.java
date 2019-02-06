package ru.mai.julia;


import ru.mai.julia.enums.CellState;

import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;

// Объект пользователя (игрока)
public class User implements Serializable {
    private String username;
    private CellState userSymbol;
    // Правила, с которыми юзер хочет играть в данный момент
    private GameRules preferredGameRules;

    private int winCount;
    private int loseCount;

    // Канал для приема сообщений от клиента, ассоциированного с этим юзером
    private transient ObjectInputStream inputStream;
    // Канал для отправки сообщений клиенту, ассоциированного с этим юзером
    private transient ObjectOutputStream outputStream;

    public User(String username) {
        this.username = username;
    }

    public User(String username, int winCount, int loseCount) {
        this.username = username;
        this.winCount = winCount;
        this.loseCount = loseCount;
    }

    public void win() {
        winCount++;
    }

    public void lose() {
        loseCount++;
    }

    // Распечатать юзера в формате рейтинговой таблицы
    public void printRating() {
        System.out.printf("%15s %15s %15s\n", username, winCount, loseCount);
    }

    // Распечатать юзера в формате предпочитаемых правил игры
    public void printGameParams() {
        if(getPreferredGameRules() != null) {
            System.out.printf("%15s %15s %15s %15s\n", username,
                    preferredGameRules.getFieldSize(),
                    preferredGameRules.getOpponentCount(),
                    preferredGameRules.getWinLineLength());
        }
    }

    public String getUsername() {
        return username;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }

    public void setPreferredGameRules(GameRules preferredGameRules) {
        this.preferredGameRules = preferredGameRules;
    }

    public GameRules getPreferredGameRules() {
        return preferredGameRules;
    }

    public boolean sameGameRules(User user) {
        return getPreferredGameRules().equals(user.getPreferredGameRules());
    }

    public CellState getUserSymbol() {
        return userSymbol;
    }

    public void setUserSymbol(CellState userSymbol) {
        this.userSymbol = userSymbol;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(ObjectOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
