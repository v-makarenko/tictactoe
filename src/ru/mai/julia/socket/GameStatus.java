package ru.mai.julia.socket;

import ru.mai.julia.Field;
import ru.mai.julia.User;
import ru.mai.julia.enums.CellState;

import java.io.Serializable;

public class GameStatus implements Serializable {
    private Field field;
    private boolean yourTurn;
    private User droppedUser;
    private User winner;
    private CellState cellState;
    private CellState[] cellStates;
    private CellState[][] cellStatess;

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public GameStatus(Field field, boolean yourTurn, User winner) {
        this.field = field;
        this.yourTurn = yourTurn;
        this.winner = winner;
    }

    public User getWinner() {
        return winner;
    }

    public boolean isYourTurn() {
        return yourTurn;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "GameStatus{" +
                "field=" + field +
                ", yourTurn=" + yourTurn +
                ", winner=" + winner +
                '}';
    }

    public CellState[] getCellStates() {
        return cellStates;
    }

    public void setCellStates(CellState[] cellStates) {
        this.cellStates = cellStates;
    }

    public CellState[][] getCellStatess() {
        return cellStatess;
    }

    public void setCellStatess(CellState[][] cellStatess) {
        this.cellStatess = cellStatess;
    }

    public User getDroppedUser() {
        return droppedUser;
    }

    public void setDroppedUser(User droppedUser) {
        this.droppedUser = droppedUser;
    }
}
