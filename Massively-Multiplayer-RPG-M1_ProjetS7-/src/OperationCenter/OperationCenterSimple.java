package OperationCenter;

import Client.Session;
import Game.Player;
import Labyrinth.Labyrinth;
import Labyrinth.LabyrinthSimple;

import java.util.ArrayList;
import java.util.HashMap;

public class OperationCenterSimple {

    private LabyrinthSimple labyrinth;
    HashMap<Labyrinth,ArrayList<String>> resp;
    private ArrayList<String> halls;

    public Session identification(String name) {return null;};
    public void save(ArrayList<Player> players, String labyrinth, String Hall) {};
    public LabyrinthSimple recordLabyrinth(Labyrinth server) {return labyrinth;};
    public ArrayList<String> recordMessagerie(String ip,int numPort) {return halls;};


}
