package Client;

import Game.Context;
import Game.Participant;
import Game.Player;
import Labyrinth.Labyrinth;

import java.rmi.RemoteException;

public class ClientSimple {

    public void setContext(Context context){};
    public void addPlayer(Player player){};
    public void setHall(String Hall){};
    public void setLabyrinthServer(Labyrinth server){};
    public void startFight(Participant forward, Participant attacked){};
    public void hitpoints(Participant forward, Participant attacked, int hitpoints){};

}
