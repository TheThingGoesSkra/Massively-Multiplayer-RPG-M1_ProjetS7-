package Client;

import Game.Player;
import Labyrinth.Labyrinth;

public class Session {
    private  String idHall;
    private Labyrinth proxy;
    private Player player;

    public String getHall() {
        return idHall;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
