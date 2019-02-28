package Client;

import Game.Context;
import Game.Participant;
import Game.Player;
import Labyrinth.Labyrinth;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote {

    public void setContext(Context context) throws RemoteException;
    public void addPlayer(Player player) throws RemoteException;
    public void setHall(String Hall) throws RemoteException;
    public void setLabyrinthServer(Labyrinth server) throws RemoteException;
    public void startFight(Participant forward, Participant attacked) throws RemoteException;
    public void hitpoints(Participant forward, Participant attacked, int hitpoints) throws RemoteException;
}
