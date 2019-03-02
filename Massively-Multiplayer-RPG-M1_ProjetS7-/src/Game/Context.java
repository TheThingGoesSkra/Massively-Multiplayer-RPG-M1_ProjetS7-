package Game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class Context implements Serializable {

    private ArrayList<Monster> monsters;
    private ArrayList<Player> players;

    public Context(){
        this.monsters=new ArrayList<Monster>();
        this.players=new ArrayList<Player>();
    }

    public Player getPlayer(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    public Monster getMonster(String name) {
        for (Monster monster : monsters) {
            if (monster.getName().equals(name)) {
                return monster;
            }
        }
        return null;
    }

    public Boolean participantExist(Participant participant){
        if(participant instanceof Player){
            for(Player player : players){
                if(participant==player){
                    return true;
                }
            }
        }
        if(participant instanceof Monster){
            for(Monster monster : monsters){
                if(participant==monster){
                    return true;
                }
            }
        }
        return false;
    }

    public void addMonster(Monster monster){
        monsters.add(monster);
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void removeParticipant(Participant participant){
        if(participant instanceof Player){
            removePlayer((Player)participant);
        }
        if(participant instanceof Monster){
            removeMonster((Monster)participant);
        }
    }

    public void removeMonster(Monster monster){
        monsters.remove(monster);
    }

    public void removePlayer(Player player){
        players.remove(player);
    }


    public ArrayList<Player> getPlayers(){
        return players;
    }

    public ArrayList<Monster> getMonsters() {
        return  monsters;
    }
}
