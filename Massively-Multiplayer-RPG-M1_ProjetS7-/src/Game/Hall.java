package Game;

import java.util.*;
import java.util.Map.Entry;

public class Hall {
    private String name;
    private static Map<Hall,Door>  Hall_liste = new HashMap<Hall,Door>();
    private Door door = new Door();


    public Hall{

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

    public int getDoor(Pole pole){


    }

    public void addDoor(Hall hall,Door door, Pole pole){

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

    public  void removePlayer(Player player){

       context.removePlayer(player);
    }



    public void endFight(Participant looser){


    }

    public void runnaway(Participant participant1, Participant participant2){

    }

    public void addFight(Participant participant1, Participant participant2){
        Fight fight = new Fight(participant1,participant2);
    }

    public void exitHall(Player player){

        context.removePlayer(player);

    }

    public void isFighting(Participant participant1){

    }

    public void Heal(){

    }
}