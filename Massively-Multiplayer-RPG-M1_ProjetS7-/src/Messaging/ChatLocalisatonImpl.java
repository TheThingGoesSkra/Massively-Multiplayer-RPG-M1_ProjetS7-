package Messaging;

import Labyrinth.Labyrinth;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatLocalisatonImpl  extends UnicastRemoteObject implements ChatLocalisation {

    private ServerMsg server;

    public ChatLocalisatonImpl(ServerMsg server) throws RemoteException {
        super();
        this.server=server;
    }

    public void addPlayer(String idHall, String name) throws RemoteException{
        this.server.addPlayer(idHall,name);
    }

    public void removePlayer(String idHall, String name) throws RemoteException{
        this.server.removePlayer(idHall,name);
    }

    public void moovePlayer(String idHall, String name, String idHall2) throws RemoteException{
        this.server.moovePlayer(idHall,name);
    }

}
