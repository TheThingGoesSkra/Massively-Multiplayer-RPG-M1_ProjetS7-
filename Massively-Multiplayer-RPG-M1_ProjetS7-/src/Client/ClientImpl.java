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
    };
    public void setHall(String Hall) throws RemoteException {};
    public void setLabyrinthServer(Labyrinth server) throws RemoteException {};
    public void startFight(Participant forward, Participant attacked) throws RemoteException {
        client.startFight(forward,attacked);
    };

    public void hitpoints(Participant forward, Participant attacked, int hitpoints) throws RemoteException {
        client.hitpoints(forward,attacked,hitpoints);
    };

    public void endFight(ArrayList<Participant> winners, Participant looser) throws RemoteException {
        client.endFight(winners,looser);
    };
    public void heal() throws RemoteException{
        client.heal();
    };

}
