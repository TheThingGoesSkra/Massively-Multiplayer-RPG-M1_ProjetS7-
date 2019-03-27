package OperationCenter;

import Client.Client;
import Client.Session;
import Game.Player;
import Labyrinth.Labyrinth;
import Labyrinth.LabyrinthSimple;
import Messaging.ChatLocalisation;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface OperationCenter extends Remote{

    void identification(String name, Client client) throws RemoteException;
    void save(ArrayList<Player> players, String labyrinth, String Hall) throws RemoteException;
    LabyrinthSimple recordLabyrinth(Labyrinth server) throws RemoteException;
    ArrayList<String> recordMessagerie(String ip, int numPort, ChatLocalisation proxy) throws RemoteException;
}
