package Client;

import Game.Bonus;
import Game.Context;
import Game.Participant;
import Game.Player;
import Labyrinth.Labyrinth;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Client extends Remote {

    void setContext(Context context) throws RemoteException;
    void addParticipant(Participant participant) throws RemoteException;
    void removePlayer(Player player) throws RemoteException;
    void setHall(String idHall) throws RemoteException;
    void setLabyrinthServer(Labyrinth server) throws RemoteException;
    void startFight(Participant forward, Participant attacked) throws RemoteException;
    void hitpoints(Participant forward, Participant attacked, int hitpoints) throws RemoteException;
    void useBonus(Participant participant, Bonus bonus) throws RemoteException;
    void endFight(ArrayList<Participant> winners, Participant looser) throws RemoteException;
    void heal() throws RemoteException;
    void alertRunnaway(Participant forward, Participant runner) throws RemoteException;
    void receiveDrop(Bonus bonus) throws RemoteException;
}

