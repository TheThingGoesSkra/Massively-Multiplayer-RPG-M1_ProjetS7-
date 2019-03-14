package Client;

import Game.Bonus;
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
    public void addParticipant(Participant participant) throws RemoteException {
        client.addParticipant(participant);
    };
    public void removePlayer(Player player) throws RemoteException {
        client.removePlayer(player);
    };
    public void setHall(String idHall) throws RemoteException {
        client.setHall(idHall);
    }

    ;
    public void setLabyrinthServer(Labyrinth server) throws RemoteException {
        client.setLabyrinthServer(server);
    };
    public void startFight(Participant forward, Participant attacked) throws RemoteException {
        client.startFight(forward,attacked);
    };

    public void hitpoints(Participant forward, Participant attacked, int hitpoints) throws RemoteException {
        client.hitpoints(forward,attacked,hitpoints);
    }

    public void useBonus(Participant participant, Bonus bonus) throws RemoteException {
        client.useBonus(participant,bonus);
    }

    public void endFight(ArrayList<Participant> winners, Participant looser) throws RemoteException {
        client.endFight(winners,looser);
    };

    public void alertRunnaway(Participant forward, Participant runner) throws RemoteException{
        client.alertRunnaway(forward,runner);
    }

    @Override
    public void receiveDrop(Bonus bonus) throws RemoteException {
        client.receiveDrop(bonus);
    }

    public void heal() throws RemoteException{
        client.heal();
    };

}
