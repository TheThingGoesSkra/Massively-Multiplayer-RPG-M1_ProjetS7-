package Labyrinth;

import Client.Client;
import Client.Session;
import Game.Hall;
import Game.Pole;

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

    public LabyrinthSimple(String id, String name){
        this.id=id;
        this.name=name;
        this.halls = new HashSet<Hall>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
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

    public void login(Session s, Client proxy){};
    public void changeHall(String Hall, String player, Pole direction){};
    public void newFight(String forward, String attacked){};
    public void runnaway(String Hall, String forward, String runner){};
    public void logOut(String Hall, String player){};

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
