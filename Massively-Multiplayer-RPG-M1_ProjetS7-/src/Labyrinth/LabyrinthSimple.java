package Labyrinth;

import Client.Client;
import Client.Session;
import Game.Hall;
import Game.Pole;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class LabyrinthSimple {

    private HashSet<Hall> halls = new HashSet<Hall>();

    public void login(Session s, Client proxy){};
    public void changeHall(String Hall, String player, Pole direction){};
    public void newFight(String forward, String attacked){};
    public void runnaway(String Hall, String forward, String runner){};
    public void logOut(String Hall, String player){};
    public void setReponsabiities(HashMap<Labyrinth,ArrayList<String>> resp){};

}
