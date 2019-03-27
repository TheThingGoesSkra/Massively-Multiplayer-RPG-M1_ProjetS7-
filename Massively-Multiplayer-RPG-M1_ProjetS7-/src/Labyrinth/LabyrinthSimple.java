package Labyrinth;

import Client.Client;
import Client.Session;
import Game.*;
import OperationCenter.OperationCenter;

import java.awt.*;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class LabyrinthSimple implements Serializable{

    private String id;
    private String name;
    private HashSet<Hall> halls;
    private String idHall;
    private OperationCenter noc;


    public LabyrinthSimple(String id, String name, String idHall){
        this.id=id;
        this.name=name;
        this.idHall=idHall;
        this.halls = new HashSet<Hall>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OperationCenter getNoc() {
        return noc;
    }

    public void setNoc(OperationCenter noc) {
        this.noc = noc;
        for(Hall hall:halls){
            hall.setNoc(noc);
        }
    }

    public String getName() {
        return name;
    }

    public void addPlayer(String idHall, Player player){
        Hall hall = this.getHall(idHall);
        hall.addPlayer(player);
    }
    public void setName(String name) {
        this.name = name;
    }

    public HashSet<Hall> getHalls() {
        return halls;
    }

    public void setHalls(HashSet<Hall> halls) {
        this.halls = halls;
    }

    public void addHall(Hall hall){
        this.halls.add(hall);
    }

    public Hall getHall(String hallId){
        for(Hall hall : halls){
            String id=hall.getIdHall();
            if(id.equals(hallId)){
                return hall;
            }
        }
        return null;
    }

    public void login(Session s, Client proxy){
        Player player;
        String idHall;
        idHall= s.getIdHall();
        player = s.getPlayer();
        player.setProxy(proxy);
        addPlayer(idHall,player);
    };

    public void login(String idHall, Player player, Client proxy){
        player.setProxy(proxy);
;       addPlayer(idHall,player);
    };

    public void newGame(String idHall, String idPlayer){
        Hall hall = getHall(idHall);
        Labyrinth proxy = hall.getProxy();
        Context context = hall.getContext();
        Player player1= context.getPlayer(idPlayer);
        hall.forceExitPlayer(player1);
        Client client =  player1.getProxy();
        Hall newHall = getHall(this.idHall);
        Labyrinth newProxy = newHall.getProxy();
        try {
            client.setHall(this.idHall);
            if(proxy!=newProxy){
                Client proxyClient=player1.getProxy();
                newProxy.login(this.idHall,player1,proxyClient);
                client.setLabyrinthServer(newProxy);
            }else{
                addPlayer(this.idHall,player1);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void newFight(String idHall, String forwardName, String attackedName){
        Hall hall=this.getHall(idHall);
        hall.addFight(forwardName,attackedName);
    };

    public void runnaway(String idHall, String forwardName, String runnerName){
        Hall hall = this.getHall(idHall);
        hall.runnaway(forwardName,runnerName);
    };

    public void logout(String idHall, String player){
        Hall hall = getHall(idHall);
        Context context = hall.getContext();
        Player player1= context.getPlayer(player);
        hall.forceDeadPlayer(player1);
    };

    public int changeHall(String idHall, String player, Pole direction){
         int answer;
         Hall hall = getHall(idHall);
         answer = hall.changeHall(player, direction);
         return answer;
    };

    public void chooseBonus(String idHall, String namePlayer, String bonus) throws RemoteException {
        Hall hall = getHall(idHall);
        Context context = hall.getContext();
        Player player = context.getPlayer(namePlayer);
        ArrayList<Bonus> bonusList=player.getListeBonus();
        int i=0;
        for(Bonus bonus2 : bonusList){
            if(bonus2.getName().equals(bonus)){
                player.useBonus(bonus2);
                ArrayList<Player> players=context.getPlayers();
                for(Player player1:players){
                    Client client=player1.getProxy();
                    client.useBonus(player,bonus2);
                }
                bonusList.remove(i);
                break;
            }
            i++;
        }
    }

    public void setReponsabiities(HashMap<Labyrinth,ArrayList<String>> resp){
        for(Labyrinth key : resp.keySet()){
            List<String> hallsId = resp.get(key);
            for(String hallId : hallsId){
                Hall hall = this.getHall(hallId);

                if(hall==null){
                    System.err.println("Erreur : Hall introuvable ");
                }else{
                    hall.setProxy(key);
                }
            }
        }
    }

}
