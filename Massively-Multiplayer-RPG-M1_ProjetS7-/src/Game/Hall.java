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

    public int getDoor(Pole pole){


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

    public boolean exitHall(Player player){

        boolean isFighting=isFighting(player);

        if (isFighting=false){
            context.removePlayer(player);
            return  isFighting;
        }
        else{
            System.out.println("Le joueur est en train de se battre ");
            return  isFighting;
        }


    }

    public boolean isFighting(Participant participant1){
       return true;
    }

    public void Heal(){

        for(int i = 0 ; i < context.getPlayers().size(); i++){
            context.getPlayers().get(i).setLife(context.getPlayers().get(i).getMaxlife());
        }
    }
}