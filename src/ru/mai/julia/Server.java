package ru.mai.julia;


import ru.mai.julia.socket.server.ClientHandler;
import ru.mai.julia.socket.server.ServerReceiver;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private List<User> usersPending = new ArrayList<>();
    private List<User> usersEverPlayed = new ArrayList<>();
    private List<Game> games = new ArrayList<>();


    // TODO дз, StringBuilder?
    public String getUsersPendingList(){
        return null;
    }

    // TODO - посмотри Comparator, Collections.sort
    public String getUsersEverPlayedList(){
        return null;
    }

    public static void main(String[] args) {
        ServerReceiver serverReceiver = new ServerReceiver();
        serverReceiver.init();
    }


}
