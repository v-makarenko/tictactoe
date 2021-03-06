package ru.mai.julia.socket;

import ru.mai.julia.Field;
import ru.mai.julia.User;

import java.io.Serializable;

public class GameStatus implements Serializable {
    private Field field;
    private boolean yourTurn;
    private User droppedUser;
    private User winner;
    private boolean draw;

    public GameStatus(Field field, boolean yourTurn, User winner, boolean draw) {
        this.field = field;
        this.yourTurn = yourTurn;
        this.winner = winner;
        this.draw = draw;
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

    public User getDroppedUser() {
        return droppedUser;
    }

    public void setDroppedUser(User droppedUser) {
        this.droppedUser = droppedUser;
    }

    public boolean isDraw() {
        return draw;
    }
}
