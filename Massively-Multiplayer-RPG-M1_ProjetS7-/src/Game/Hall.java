package Game;

import java.util.*;
import java.util.Map.Entry;

public class Hall {
    private String name;
    private static Map<Hall,Door>  Hall_liste = new HashMap<Hall,Door>();


    private List<Participant> list = new LinkedList<>();


    public List<Participant> getList() {
        return list ;
    }

    public Hall{

        this.name=name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getDoor(Pole pole){


    }

    public void addDoor(Hall hall,Door door, Pole pole){
        Hall_liste.put(hall.name,)
    }
    public void addMonster(Monster monster){
        list.add(monster);
    }

    public void addPlayer(Player player){
        list.add(player);

    }

    public void removeMonster(Monster monster){
        Hall_liste.remove(monster);

    }

    public  void removePlayer(Player player){

        Hall_liste.remove(player);
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