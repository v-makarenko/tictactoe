package ru.mai.julia.enums;

import java.io.Serializable;

public enum CellState implements Serializable {
    X("X"), O("O"), Z("Z"), EMPTY(" ");

    private String displayName;

    CellState(String displayName){
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
