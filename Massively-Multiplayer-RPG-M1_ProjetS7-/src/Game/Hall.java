package Game;

import java.util.HashMap;
import java.util.Map;

public class Hall {
    private String name;
    private static Map<Hall,Door>  Hall_liste = new HashMap<Hall,Door>();
    public Hall{

        this.name=name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getDoor(Pole pole){


    }

    public void addDoor(Door door, Pole pole){

    }
    public void addMonster(Monster monster){
        Hall_liste.
    }

    public void addPlayer(Player player){


    }

    public void removeMonster(Monster monster){


    }

    public  void removePlayer(Player player){


    }

    public void hitpoints(Participant participant, int hitpoint){
        int lifeParticipant=participant.getLife();
        int life= lifeParticipant-hitpoint;
        participant.setLife(life);
    }

    public void endFight(Participant looser){

    }

    public void runnaway(Participant participant1, Participant participant2){

    }

    public void addFight(Participant participant1, Participant participant2){

    }

    public void exitHall(Player player){


    }

    public void isFighting(Participant participant1){

    }

    public void Heal(){

    }
}