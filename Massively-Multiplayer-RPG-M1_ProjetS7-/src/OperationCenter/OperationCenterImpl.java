package OperationCenter;

import Client.Session;
import Game.Player;
import Labyrinth.Labyrinth;
import Labyrinth.LabyrinthSimple;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OperationCenterImpl extends UnicastRemoteObject implements OperationCenter {

    private HashMap<Labyrinth,List<String>> responsabilities;

    protected OperationCenterImpl() throws RemoteException {};
    public Session identification(String name) throws RemoteException {return null;};
    public void save(ArrayList<Player> players, String labyrinth, String Hall)  throws RemoteException {};
    public LabyrinthSimple recordLabyrinth(Labyrinth server)  throws RemoteException {return null;};
    public ArrayList<String> recordMessagerie(String ip,int numPort) throws RemoteException {return null;};
   /*public void a(){
        Labyrinth a;
        List<String> b=responsabilities.get(a);
        for (String c:b) {

        }
    }*/

}
