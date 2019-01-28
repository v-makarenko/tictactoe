package ru.mai.julia.socket.server;

import ru.mai.julia.Game;
import ru.mai.julia.ObjectLocator;
import ru.mai.julia.User;
import ru.mai.julia.enums.ClientGameState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameHandler {
    private ClientHandler clientHandler;

    public GameHandler(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public void loop() throws InterruptedException {
        while (true) {
            List<User> opponentList = new ArrayList<>();
            Game game = null;
            synchronized (this) {
                Game gameForCurrentUser = ObjectLocator.getServer().getGameForUser(clientHandler.getUser());
                if (gameForCurrentUser != null) {
                    gameForCurrentUser.join();
                    return;
                }

                for (int i = 0; i < ObjectLocator.getServer().getUsersPendingList().size(); i++) {
                    User user = ObjectLocator.getServer().getUsersPendingList().get(i);
                    if (!user.equals(clientHandler.getUser())
                            && user.sameGameRules(clientHandler.getUser())) {
                        opponentList.add(user);
                    }
                    if (isOpponentListFilled(opponentList)) {
                        break;
                    }
                }
                if (isOpponentListFilled(opponentList)) {
                    opponentList.add(clientHandler.getUser());
                    game = ObjectLocator.getServer().createGame(opponentList);

                }
            }
            if (game != null) {
                game.join();
                clientHandler.setClientGameState(ClientGameState.LOBBY);
                return;
            }
            Thread.sleep(10000);
        }
    }

    private boolean isOpponentListFilled(List<User> opponentList) {
        return opponentList.size() == clientHandler
                .getUser()
                .getPreferredGameRules()
                .getOpponentCount()
                .getCount();
    }
}
