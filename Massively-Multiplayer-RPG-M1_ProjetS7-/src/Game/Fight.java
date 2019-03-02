package Game;

import Client.Client;
import OperationCenter.OperationCenter;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fight extends Thread {

    private Participant forward;
    private Participant attacked;
    private String idLabyrinth;
    private String idHall;
    private Context context;
    private List<Fight> fights;
    private OperationCenter noc;


    public Fight(Participant forward, Participant attacked, Context context, List<Fight> fights, String idHall, String idLabyrinth, OperationCenter noc) {
        this.forward = forward;
        this.attacked = attacked;
        this.idLabyrinth=idLabyrinth;
        this.idHall=idHall;
        this.context=context;
        this.fights=fights;
        this.noc=noc;
    }

    public Participant getForward() {
        return forward;
    }

    public void setForward(Participant forward) {
        this.forward = forward;
    }

    public Participant getAttacked() {
        return attacked;
    }

    public void setAttacked(Participant attacked) {
        this.attacked = attacked;
    }

    public Boolean participantExist(Participant participant){
        if(participant==forward){
            return true;
        }if(participant==attacked){
            return true;
        }else{
            return false;
        }
    }

    public Participant getOtherParticipant(Participant participant){
        if (participant == forward){
            return attacked;}
        else if (participant == attacked){
            return forward;
        }
        return null;
    }

    public void alertFight(){
        ArrayList<Player> players = context.getPlayers();
        for(Player player : players){
            Client client=player.getProxy();
            try {
                client.startFight(forward,attacked);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
    public int calculateHitpoint(Participant forward, Participant attacked){
        int hitpoints = forward.getAttack();
        return hitpoints;
    }

    public Participant chooseParticipant(){
        Random random = new Random();
        int pileOuFace = random.nextInt(2);
        if (pileOuFace == 0) {
            return forward;
        } else {
            return attacked;
        }
    }

    public void deadParticipant(Participant looser){
        ArrayList<Participant> winners = new ArrayList<Participant>();
        ArrayList<Fight> fightsToStop = new ArrayList<Fight>();
        for(Fight fight:fights){
            Boolean exist=fight.participantExist(looser);
            if(exist){
                Participant winner=fight.getOtherParticipant(looser);
                winners.add(winner);
                if(winner!=this.forward && winner!=this.attacked){
                    fightsToStop.add(fight);
                }
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
        endFight();
    }

    public void endFight(){
        int index=fights.indexOf(this);
        fights.remove(index);
        if(fights.isEmpty()){
            ArrayList<Player> players=context.getPlayers();
            for(Player player:players){
                player.heal();
                Client client=player.getProxy();
                try {
                    client.heal();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            ArrayList<Monster> monsters=context.getMonsters();
            for(Monster monster:monsters) {
                monster.heal();
            }
            try {
                if(!players.isEmpty()) {
                    noc.save(players, idLabyrinth, idHall);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        this.interrupt();
    }

    public void run(){
        alertFight();
        while(true){
            Participant notChoosed;
            Participant choosed=chooseParticipant();
            if(forward==choosed){
                notChoosed=attacked;
            }else{
                notChoosed=forward;
            }
            if(choosed.isAlive() && notChoosed.isAlive()){
                int hitpoints = calculateHitpoint(notChoosed,choosed);
                Boolean isKiller=choosed.hitpoints(hitpoints);
                List<Player> players=context.getPlayers();
                for(Player player:players){
                    Client client=player.getProxy();
                    try {
                        client.hitpoints(notChoosed,choosed,hitpoints);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                if(isKiller){
                    deadParticipant(choosed);
                }
            }
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
