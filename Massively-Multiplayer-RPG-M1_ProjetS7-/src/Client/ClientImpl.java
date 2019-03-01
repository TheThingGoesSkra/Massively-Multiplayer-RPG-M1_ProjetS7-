package Client;

import Game.Context;
import Game.Participant;
import Game.Player;
import Labyrinth.Labyrinth;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ClientImpl extends UnicastRemoteObject implements Client, Serializable {

    private transient ClientSimple client;

    protected ClientImpl(ClientSimple client) throws RemoteException {
        this.client=client;
    }

    public void setContext(Context context) throws RemoteException {
        client.setContext(context);
    };
    public void addPlayer(Player player) throws RemoteException {
        client.addPlayer(player);
        System.out.println("Nouveau context : "+client.getContext().getPlayers().toString());
    };
    public void setHall(String Hall) throws RemoteException {};
    public void setLabyrinthServer(Labyrinth server) throws RemoteException {};
    public void startFight(Participant forward, Participant attacked) throws RemoteException {};
    public void hitpoints(Participant forward, Participant attacked, int hitpoints) throws RemoteException {};
    public void endFight(ArrayList<Participant> winners, Participant looser) throws RemoteException {};

}
