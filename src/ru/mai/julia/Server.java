package ru.mai.julia;

import ru.mai.julia.socket.server.ServerReceiver;

import java.util.*;

public class Server {
    private List<User> usersPending = new ArrayList<>();
    private List<User> usersEverPlayed = new ArrayList<>();
    private Set<Game> games = new HashSet<>();

    public Server() {
        ObjectLocator.setServer(this);
    }

    public List<User> getUsersPendingList() {
        return usersPending;
    }

    public List<User> getUsersEverPlayedList() {
        Collections.sort(usersEverPlayed, Collections.reverseOrder(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.equals(o2)) {
                    return 0;
                }
                return o1.getWinCount() - o2.getWinCount();
            }
        }));
        return usersEverPlayed;
    }

    public static void main(String[] args) {
        new Server().start();
    }

    private void start() {
        ServerReceiver serverReceiver = new ServerReceiver();
        serverReceiver.init();
    }

    public void addPendingUser(User user) {
        usersPending.add(user);
    }

    public Game createGame(List<User> opponentList) {
        usersPending.removeAll(opponentList);
        Game game = new Game(opponentList);
        games.add(game);
        game.start();
        return game;
    }

    public Game getGameForUser(User user) {
        for (Game game : games) {
            if (game.getPlayers().contains(user)) {
                return game;
            }
        }
        return null;
    }
}
