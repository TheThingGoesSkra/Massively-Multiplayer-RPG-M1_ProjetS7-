package OperationCenter;

import Client.Session;
import Game.Player;
import Labyrinth.Labyrinth;

import java.util.ArrayList;

public class OperationCenterSimple {

    public Session identification(String name){Session session=new Session();return session;};
    public void save(ArrayList<Player> players, String labyrinth, String Hall){};
    public void recordLabyrinth(Labyrinth server){};
    public void recordMessagerie(String ip,int numPort){};

}
