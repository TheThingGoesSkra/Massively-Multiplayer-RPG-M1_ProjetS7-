package Client;

import Game.Player;
import Labyrinth.Labyrinth;

import java.io.Serializable;

public class Session implements Serializable{

    private  String idLabyrinth;
    private  String idHall;
    private Player player;
    private Labyrinth proxy;
    private int numPortMessaging;
    private String hostMessaging;

    public Session(Player player, String idLabyrinth, String idHall) {
        this.player=player;
        this.idHall=idHall;
        this.idLabyrinth=idLabyrinth;
    }

    public String getHall() {
        return idHall;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getIdLabyrinth() {
        return idLabyrinth;
    }

    public void setIdLabyrinth(String idLabyrinth) {
        this.idLabyrinth = idLabyrinth;
    }

    public String getIdHall() {
        return idHall;
    }

    public void setIdHall(String idHall) {
        this.idHall = idHall;
    }

    public Labyrinth getProxy() {
        return proxy;
    }

    public void setProxy(Labyrinth proxy) {
        this.proxy = proxy;
    }


    public int getNumPortMessaging() {
        return numPortMessaging;
    }

    public void setNumPortMessaging(int numPortMessaging) {
        this.numPortMessaging = numPortMessaging;
    }

    public String getHostMessaging() {
        return hostMessaging;
    }

    public void setHostMessaging(String hostMessaging) {
        this.hostMessaging = hostMessaging;
    }
}
