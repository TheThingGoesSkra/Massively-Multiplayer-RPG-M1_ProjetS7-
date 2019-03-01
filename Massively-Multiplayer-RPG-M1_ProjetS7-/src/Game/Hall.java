package Game;

import java.util.*;

import Client.Client;
import Labyrinth.Labyrinth;
import java.io.Serializable;




public class Hall implements Serializable {
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

    public int changeHall(String player, Pole pole){
        int idHalls =0;
        Door door = new Door();
        getDoor(pole);
        if ( getDoor(pole) != null) {
           Player player1=context.getPlayers(player);
            exitPlayer(player1);

            if(exitPlayer(player1) == true){
               Hall hall= door.getOtherHall(this.idHall);
                getName();
               Client client =  player1.getProxy();
               hall.getProxy();

               if ( hall.getProxy() == this.getProxy()){
                    hall.addPlayer(player1);
               }

               else if ( hall.getProxy() != this.getProxy()){
                    player1.getProxy();
               }
            }

        }
        return idHalls;
    }


    public Door getDoor(Pole pole){
        Door door = doors.get(pole);
        return door;
    }

    public void addDoor(Door door, Pole pole){
        this.doors.put(pole,door);

    }

    public void addMonster(Monster monster) {
        context.addMonster(monster);

    }

    public void removeMonster(Monster monster) {
        context.removeMonster(monster);

    }

    public void addPlayers(Player player) {
        context.addPlayer(player);

    }

    public boolean addPlayer(Player player){
        boolean playerAdd =false;
        ArrayList<Player> players;
        List<Monster> monsters;

        Client client = new Client;
        Client client1 = new Client;
        client = player.getProxy();
        players = context.getPlayers();

        for ( Player player1 : players ) {
            client1 = player1.getProxy();
        }
        context.addPlayer(player);
        monsters=context.getMonsters();
        if( monsters !=null){
            Monster monster = new Monster();
            monster = monsters.get(0);
            addFight(player,monster);
            playerAdd =true;
            return playerAdd;
        }

        else {
            return playerAdd;
        }
    }
    public boolean exitPlayer (Player player){

        boolean isFighting = isFighting(player);

        if (isFighting = false) {
            context.removePlayer(player);
            return isFighting;
        } else {
            System.out.println("Le joueur est en train de se battre ");
            return isFighting;
        }

    }


    public void runnaway(Participant participant1, Participant participant2) {

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

        if (monster && player == true) {

            Fight fight1 = new Fight(participant1, participant2);
            fights.add(fight1);
        }
    }


        public void removeFight (Fight fight1){
            fights.remove(fight1);
        }


        public boolean isFighting (Participant participant1){
            boolean isFighting = false;
            for (Fight fight : fights) {
                if (participant1 == fight.getAttacked() || participant1 == fight.getForward()) {
                    isFighting = true;
                }
            }
            return isFighting;
        }


        public void Heal () {
            ArrayList<Player> players = this.context.getPlayers();
            for (int i = 0; i < players.size(); i++) {
                players.get(i).heal();
            }
        }


    }
