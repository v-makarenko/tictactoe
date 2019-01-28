package ru.mai.julia;


import ru.mai.julia.enums.FieldSize;
import ru.mai.julia.enums.OpponentCount;
import ru.mai.julia.enums.WinLineLength;

import java.io.Serializable;
import java.util.Objects;

// Правила игры (Желаемые для пользователя и уже неизменяемые для сервера)
public class GameRules implements Serializable {
    private FieldSize fieldSize;
    private OpponentCount opponentCount;
    private WinLineLength winLineLength;

    public GameRules(OpponentCount opponentCount, FieldSize fieldSize, WinLineLength winLineLength) {
        this.fieldSize = fieldSize;
        this.winLineLength = winLineLength;
        this.opponentCount = opponentCount;
    }

    public FieldSize getFieldSize() {
        return fieldSize;
    }

    public OpponentCount getOpponentCount() {
        return opponentCount;
    }

    public WinLineLength getWinLineLength() {
        return winLineLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameRules gameRules = (GameRules) o;
        return fieldSize == gameRules.fieldSize &&
                opponentCount == gameRules.opponentCount &&
                winLineLength == gameRules.winLineLength;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldSize, opponentCount, winLineLength);
    }
}
