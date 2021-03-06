package Game;

import java.rmi.RemoteException;
import java.util.*;

import Client.Client;
import Labyrinth.Labyrinth;
import Messaging.ChatLocalisation;
import OperationCenter.OperationCenter;

import java.io.Serializable;




public class Hall implements Serializable {
    private String idHall;
    private int x;
    private int y;
    private String idLabyrinth;
    private String name;
    private String idType;
    private HashMap<Pole,Door> doors;
    private transient Labyrinth proxy;
    private ChatLocalisation chatProxy;
    private Context context;
    private ArrayList<Fight> fights;
    private OperationCenter noc;


    public Hall (String idLabyrinth, String id, String name, String idType, int x, int y){
        this.context = new Context();
        this.fights = new ArrayList<Fight>();
        this.doors = new HashMap<Pole,Door>();
        this.idLabyrinth=idLabyrinth;
        this.idHall =id;
        this.name=name;
        this.idType=idType;
        this.x=x;
        this.y=y;
    }

    public ChatLocalisation getChatProxy() {
        return chatProxy;
    }

    public void setChatProxy(ChatLocalisation chatProxy) {
        this.chatProxy = chatProxy;
    }

    public String getIdHall() {
        return idHall;
    }

    public void setIdHall(String idHall) {
        this.idHall = idHall;
    }

    public int getx() {
        return x;
    }

    public void setx(int x) {
        this.x = x;
    }

    public int gety() {
        return y;
    }

    public void sety(int y) {
        this.y = y;
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

    public int changeHall(String player, Pole pole){
        Door door = getDoor(pole);
        if ( door == null) {
            return -1;
        }else{
            Player player1=context.getPlayer(player);
            // TODO Test if player1==NULL "arrete de nous prendre des cons"
            Boolean exitHall=exitPlayer(player1);
            if(exitHall == false) {
                return 0;
            }else{
               Hall hall = door.getOtherHall(this);
               String idHall = hall.getIdHall();
               Client client =  player1.getProxy();
               ChatLocalisation chatProxy=hall.getChatProxy();
               try {
                    client.setHall(idHall);
                    System.out.println(this.idHall+" "+player+" "+idHall);
                    chatProxy.moovePlayer(this.idHall,player,idHall);
               } catch (RemoteException e) {
                    e.printStackTrace();
               }
               Labyrinth proxy=hall.getProxy();
               if ( proxy == this.proxy){
                    hall.addPlayer(player1);
                    return 1;
               }
               else if ( proxy != this.proxy){
                   try {
                       client.setLabyrinthServer(proxy);
                       Client proxyClient=player1.getProxy();
                       proxy.login(idHall,player1,proxyClient);
                   } catch (RemoteException e) {
                       e.printStackTrace();
                   }
                   return 2;
               }
            }
        }
        return -2;
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
        ArrayList<Monster> monsters;
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
                tamponClient.addParticipant(player);
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
        if (isFighting == false) {
            context.removePlayer(player);
            ArrayList<Player> players = context.getPlayers();
            for(Player player1 : players){
                Client client = player1.getProxy();
                try {
                    client.removePlayer(player);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            return true;
        } else {
            System.out.println("Le joueur est en train de se battre.");
            return false;
        }
    }

    public void forceExitPlayer (Player player){
        boolean isFighting = isFighting(player);
        if (isFighting == true) {
            runnawayAll(player);
        }
        context.removePlayer(player);
        ArrayList<Player> players = context.getPlayers();
        for(Player player1 : players){
            Client client = player1.getProxy();
            try {
                client.removePlayer(player);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void forceDeadPlayer(Player looser){
        ArrayList<Participant> winners = new ArrayList<Participant>();
        ArrayList<Fight> fightsToStop = new ArrayList<Fight>();
        for(Fight fight:fights){
            Boolean exist=fight.participantExist(looser);
            if(exist){
                Participant winner=fight.getOtherParticipant(looser);
                winners.add(winner);
                fightsToStop.add(fight);
            }
        }
        for(Participant winner:winners){
            winner.heal(1);
        }
        ArrayList<Player> players=context.getPlayers();
        for(Player player:players){
            Client client=player.getProxy();
            try {
                client.endFight(winners,looser);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        context.removeParticipant(looser);
        for(Fight fight:fightsToStop){
            fight.endFight();
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
                fight.runnaway(runner);
                fight.stopFight();
            }
        }
    }

    public void runnawayAll(Participant runner){
        ArrayList<Fight> fightsToRun=new ArrayList<Fight>();
        for(Fight fight:fights){
            Boolean exist=fight.participantExist(runner);
            if(exist){
                    fightsToRun.add(fight);
            }
        }
        for(Fight fight : fightsToRun){
            if(fight.participantExist(runner)){
                fight.runnaway(runner);
                fight.stopFight();
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
