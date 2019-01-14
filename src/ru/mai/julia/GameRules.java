package ru.mai.julia;


import ru.mai.julia.enums.FieldSize;
import ru.mai.julia.enums.OpponentCount;
import ru.mai.julia.enums.WinLineLength;

// Правила игры (Желаемые для пользователя и уже неизменяемые для сервера)
public class GameRules {
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
}
