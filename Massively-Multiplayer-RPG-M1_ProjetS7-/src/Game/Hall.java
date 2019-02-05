package Game;

import java.util.*;
import java.util.Map.Entry;

public class Hall {
    private String name;
    private static Map<Pole,Door>  Hall_liste = new HashMap<Pole,Door>();
    Context context = new Context();

    public Hall (String name){

        this.name=name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void hitpoints(Participant participant, int hitpoint){
        int lifeParticipant=participant.getLife();
        int life= lifeParticipant-hitpoint;
        participant.setLife(life);
    }

    public Door getDoor(Pole pole){
        Door door = Hall_liste.get(pole);
        return door;
    }

    public void addDoor(Door door, Pole pole){
        Hall.Hall_liste.put(pole,door);

    }

    public void addMonster(Monster monster){
        context.addMonster(monster);

    }

    public void addPlayer(Player player){
        context.addPlayer(player);

    }

    public void removeMonster(Monster monster){
     context.removeMonster(monster);

    }

    public void removePlayer(Player player){
       context.removePlayer(player);
    }

    public void removeFight(Fight fight){

    }

    public void addFight(Participant participant1, Participant participant2){
        Fight fight = new Fight(participant1,participant2);
    }

    public boolean isFighting(Participant participant1){
       return true;
    }

    public void Heal(){
         ArrayList<Player> players = this.context.getPlayers();
        for(int i = 0 ; i < players.size(); i++){
            players.get(i).heal();
        }
    }
}