package Game;

import Labyrinth.Labyrinth;

import java.io.Serializable;
import java.util.*;

public class Hall implements Serializable{
    private String idHall;
    private String name;
    private String idType;
    private HashMap<Pole,Door> doors;
    private transient Labyrinth proxy;
    Context context;
    private transient List<Fight> fights;

    public Hall (String id, String name, String idType){
        this.context = new Context();
        this.fights = new ArrayList<Fight>();
        this.doors = new HashMap<Pole,Door>();
        this.idHall =id;
        this.name=name;
        this.idType=idType;
    }

    public String getIdHall() {
        return idHall;
    }

    public void setIdHall(String idHall) {
        this.idHall = idHall;
    }

    public String getName() {
        return name;
    }

    public String getIdType() {
        return idType;
    }

    public Labyrinth getProxy() {
        return proxy;
    }

    public void setProxy(Labyrinth proxy) {
        this.proxy = proxy;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public HashMap<Pole, Door> getDoors() {
        return doors;
    }

    public void setDoors(HashMap<Pole, Door> doors) {
        this.doors = doors;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Fight> getFights() {
        return fights;
    }

    public void setFights(List<Fight> fights) {
        this.fights = fights;
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
        Door door = doors.get(pole);
        return door;
    }

    public void addDoor(Door door, Pole pole){
        this.doors.put(pole,door);

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





    public void runnaway(Participant participant1, Participant participant2){

    }

    public void addFight(Participant participant1, Participant participant2) {
        ArrayList<Player> players = this.context.getPlayers();
        List<Monster> monsters = this.context.getMonsters();
        boolean monster = false;
        boolean player = false;

        for (int i = 0; i < players.size(); i++) {
            if (participant1 == players.get(i)) {
                player = true;
            }

        }
        for (int j = 0; j < players.size(); j++) {
            if (participant2 == players.get(j)) {
                monster = true;
            }
        }
    }

    public void removeFight(Fight fight){
        fights.remove(fight);
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
            boolean isFighting = false;
            ArrayList<Player> players = this.context.getPlayers();
            for (int i = 0; i < players.size(); i++) {
                if (participant1 == players.get(i)) {
                    isFighting = true;
                }
            }
            return isFighting;
        }



    public void Heal(){
         ArrayList<Player> players = this.context.getPlayers();
        for(int i = 0 ; i < players.size(); i++){
            players.get(i).heal();
        }
    }
}