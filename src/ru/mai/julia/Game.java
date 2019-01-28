package ru.mai.julia;

import ru.mai.julia.enums.CellState;
import ru.mai.julia.enums.OpponentCount;
import ru.mai.julia.socket.Coordinates;
import ru.mai.julia.socket.GameStatus;

import java.io.IOException;
import java.util.*;

public class Game extends Thread {
    private GameRules gameRules;
    private Field field;
    private List<User> players;
    private int currentUserPlaying;

    public Game(List<User> opponentList) {
        players = opponentList;
        gameRules = players.get(0).getPreferredGameRules();
        currentUserPlaying = new Random().nextInt(opponentList.size());
        players.get(currentUserPlaying).setUserSymbol(CellState.X);
        players.get((currentUserPlaying + 1)
                % gameRules.getOpponentCount().getCount()).setUserSymbol(CellState.O);
        if (gameRules.getOpponentCount() == OpponentCount.COUNT_2) {
            players.get((currentUserPlaying + 2)
                    % gameRules.getOpponentCount().getCount()).setUserSymbol(CellState.Z);
        }
        field = new Field(gameRules.getFieldSize());
    }

    @Override
    public void run() {
        try {
            loop();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loop() throws IOException, ClassNotFoundException {
        while (true) {
            User waitingForUser = null;

            for (int i = 0; i < players.size(); i++) {
                GameStatus gameStatus = new GameStatus(field,
                        i == currentUserPlaying,
                        getUserByCellState(field.checkVictory(gameRules.getWinLineLength())));
                gameStatus.setCellState(CellState.X);
                gameStatus.setCellStates(new CellState[]{CellState.X, CellState.X, CellState.O});
                gameStatus.setCellStatess(new CellState[][]{new CellState[]{CellState.X, CellState.X, CellState.O}});
                if (i == currentUserPlaying) {
                    waitingForUser = players.get(i);
                }
                players.get(i).getOutputStream().reset();
                players.get(i).getOutputStream().writeObject(gameStatus);
            }
            if (field.checkVictory(gameRules.getWinLineLength()) != null) {
                System.out.println("Победили " + field.checkVictory(gameRules.getWinLineLength()));
                final User winner = getUserByCellState(field.checkVictory(gameRules.getWinLineLength()));
                for (User player : players) {
                    if (ObjectLocator.getServer().getUsersEverPlayedList().contains(player)) {
                        User userFromList = ObjectLocator.getServer().getUsersEverPlayedList().get(ObjectLocator.getServer().getUsersEverPlayedList().indexOf(player));
                        if (winner.equals(userFromList)) {
                            userFromList.win();
                        } else {
                            userFromList.lose();
                        }
                    } else {
                        if (winner.equals(player)) {
                            player.win();
                        } else {
                            player.lose();
                        }
                        ObjectLocator.getServer().getUsersEverPlayedList().add(player);
                    }
                }

                return;
            }

            Coordinates coordinates = (Coordinates) waitingForUser.getInputStream().readObject();
            field.setCellState(coordinates.getX(), coordinates.getY(), waitingForUser.getUserSymbol());
            currentUserPlaying++;
            currentUserPlaying = currentUserPlaying % players.size();
        }
    }

    private User getUserByCellState(CellState checkVictory) {
        if (checkVictory == null) return null;
        for (User player : players) {
            if (player.getUserSymbol() == checkVictory) {
                return player;
            }
        }
        return null;
    }

    public List<User> getPlayers() {
        return players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(gameRules, game.gameRules) &&
                Objects.equals(field, game.field) &&
                Objects.equals(players, game.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameRules, field, players);
    }
}
