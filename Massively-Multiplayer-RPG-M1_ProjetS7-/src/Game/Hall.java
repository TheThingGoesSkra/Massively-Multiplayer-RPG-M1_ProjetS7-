package Game;

import java.rmi.RemoteException;
import java.util.*;

import Client.Client;
import Labyrinth.Labyrinth;
import OperationCenter.OperationCenter;

import java.io.Serializable;




public class Hall implements Serializable {
    private String idHall;
    private String idLabyrinth;
    private String name;
    private String idType;
    private HashMap<Pole,Door> doors;
    private transient Labyrinth proxy;
    private Context context;
    private ArrayList<Fight> fights;
    private OperationCenter noc;


    public Hall (String idLabyrinth, String id, String name, String idType){
        this.context = new Context();
        this.fights = new ArrayList<Fight>();
        this.doors = new HashMap<Pole,Door>();
        this.idLabyrinth=idLabyrinth;
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

    public void setFights(ArrayList<Fight> fights) {
        this.fights = fights;
    }

    public String getIdLabyrinth() {
        return idLabyrinth;
    }

    public void setIdLabyrinth(String idLabyrinth) {
        this.idLabyrinth = idLabyrinth;
    }

    public OperationCenter getNoc() {
        return noc;
    }

    public void setNoc(OperationCenter noc) {
        this.noc = noc;
    }

    public void setName(String name) {
        this.name = name;

    }

    public void changeHall(String player, Pole pole){

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

    public void removeParticipant(Participant participant){
        context.removeParticipant(participant);
    }

    public void removeMonster(Monster monster) {
        context.removeMonster(monster);

    }

    public void addPlayers(Player player) {
        context.addPlayer(player);

    }

    public void addPlayer(Player player){
        ArrayList<Player> players;
        List<Monster> monsters;
        Client client= player.getProxy();
        Client tamponClient;
        try {
            client.setContext(context);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        players = context.getPlayers();
        for ( Player tamponPlayer : players ) {
            tamponClient = tamponPlayer.getProxy();
            try {
                tamponClient.addPlayer(player);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        context.addPlayer(player);
        monsters=context.getMonsters();
        if(!monsters.isEmpty()) {
            Monster monster = monsters.get(0);
            this.addFight(monster, player);
        }
    }

    public boolean exitPlayer (Player player){
        boolean isFighting = isFighting(player);
        if (isFighting = false) {
            context.removePlayer(player);
            return true;
        } else {
            System.out.println("Le joueur est en train de se battre.");
            return false;
        }
    }

    public Fight getFight(Participant forward, Participant attacked){
        for(Fight fight:fights){
            Boolean forwardExist=fight.participantExist(forward);
            Boolean attackedExist=fight.participantExist(attacked);
            if(forwardExist && attackedExist){
                return fight;
            }
        }
        return null;
    }

    public void runnaway(String forwardName, String runnerName) {
        Participant forward=context.getParticipant(forwardName);
        Participant runner=context.getParticipant(runnerName);
        if(forward!=null && runner!=null) {
            Fight fight = this.getFight(forward, runner);
            if(fight!=null){
                System.out.println("runnaway");
                fight.runnaway(runner);
            }
        }
    }

    public void addFight(Participant forward, Participant attacked) {
        ArrayList<Player> players = this.context.getPlayers();
        List<Monster> monsters = this.context.getMonsters();
        boolean participant1 = context.participantExist(forward);
        boolean participant2 =  context.participantExist(attacked);;
        if (participant1 && participant2) {
            Fight fight = new Fight(forward, attacked, context, fights, idHall, idLabyrinth, noc);
            fight.start();
            fights.add(fight);
        }
    }

    public void addFight(String forwardName, String attackedName) {
        Participant forward=context.getParticipant(forwardName);
        Participant attacked=context.getParticipant(attackedName);
        if(forward!=null && attacked!=null){
            Fight fight = new Fight(forward, attacked, context, fights, idHall, idLabyrinth, noc);
            fight.start();
            fights.add(fight);
        }
    }

        public void removeFight (Fight fight){
            fights.remove(fight);
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
            ArrayList<Player> players = context.getPlayers();
            for(Player player : players){
                player.heal();
            }
        }


    }
