package Game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class Context implements Serializable {

    private List<Monster> monsters;
    private ArrayList<Player> players;

    public void addMonster(Monster monster){
        monsters.add(monster);
    }

    public void addPlayer(Player player){
        players.add(player);
    }
    public void setPlayers(ArrayList<Player> players){
        this.players=players;
    }

    public void removeMonster(Monster monster){

        monsters.remove(monster);
    }

    public void removePlayer(Player player){

        players.remove(player);
    }



    public void Heal(){
        int i = 0;
        while (i it = this.players.iterator();{

        }
    }
}
