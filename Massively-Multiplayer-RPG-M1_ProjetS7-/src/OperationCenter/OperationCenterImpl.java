package OperationCenter;

import Client.Client;
import Client.Session;
import Game.Hall;
import Game.Player;
import Labyrinth.Labyrinth;
import Labyrinth.LabyrinthSimple;
import Messaging.ChatLocalisation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OperationCenterImpl extends UnicastRemoteObject implements OperationCenter {

    private HashMap<Labyrinth,List<String>> responsabilities;
    private OperationCenterSimple noc;

    protected OperationCenterImpl(OperationCenterSimple noc) throws RemoteException {
        super();
        this.noc=noc;
    };

    public void identification(String name, Client proxy) throws RemoteException {
        noc.identification(name, proxy);
    };

    public void save(ArrayList<Player> players, String idLabyrinth, String idHall)  throws RemoteException {
        noc.save(players,idLabyrinth,idHall);
    };
    public LabyrinthSimple recordLabyrinth(Labyrinth server)  throws RemoteException {
        noc.addServerResponsabilities(server);
        LabyrinthSimple labyrinth=noc.getLabyrinth();
        return labyrinth;
    }
    public ArrayList<String> recordMessagerie(String ip, int numPort, ChatLocalisation proxy) throws RemoteException {
        ArrayList<String> halls = noc.recordMessagerie(ip,numPort,proxy);
        return halls;
    }

   /*public void a(){
        Labyrinth a;
        List<String> b=responsabilities.get(a);
        for (String c:b) {

        }
    }*/

}
