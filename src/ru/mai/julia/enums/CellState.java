package ru.mai.julia.enums;

public enum CellState {
    X("X"), O("O"), EMPTY(" ");

    private String displayName;

    CellState(String displayName){
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
