package OperationCenter;

import Client.Session;
import Game.Player;
import Labyrinth.Labyrinth;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface OperationCenter extends Remote{

    public Session identification(String name) throws RemoteException;
    public void save(ArrayList<Player> players, String labyrinth, String Hall) throws RemoteException;
    public void recordLabyrinth(Labyrinth server) throws RemoteException;
    public void recordMessagerie(String ip,int numPort) throws RemoteException;
}
