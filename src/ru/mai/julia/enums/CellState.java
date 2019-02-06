package ru.mai.julia.enums;

import java.io.Serializable;

// Состояние клетки поля, а также символ, выданный игроку
public enum CellState implements Serializable {
    X("X"), O("O"), Z("Z"), EMPTY(" ");

    // То, как символ будет отображаться на экране
    private String displayName;

    CellState(String displayName){
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
