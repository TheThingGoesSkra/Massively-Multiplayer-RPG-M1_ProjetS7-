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
        int attack = forward.getAttack();
        int resilience = attacked.getResilience();
        int hitpoints;
        if(attack>=resilience)
            hitpoints = 1*(attack)/resilience;
        else
            hitpoints = 1;
        return hitpoints;
    }

    public Participant chooseParticipant(){
        Random random = new Random();
        Boolean choosed = false;
        int pileOuFace = random.nextInt(2);
        while(!choosed) {
            if (pileOuFace == 0) {
                if(forward.getChance()==2)
                    return chooseParticipantAgain();
                else
                    return forward;
            } else {
                if(attacked.getChance()==2)
                    return chooseParticipantAgain();
                else
                    return attacked;
            }
        }
        return null;
    }

    public Participant chooseParticipantAgain(){
        Random random = new Random();
        Boolean choosed = false;
        int pileOuFace = random.nextInt(2);
            if (pileOuFace == 0) {
                return forward;
            } else {
                return attacked;
            }
    }

    public void sendDrop(ArrayList<Participant> winners, Participant looser){
        boolean choosed=false;
        ArrayList<Bonus> bonusList=looser.getListeBonus();
        System.out.println(bonusList);
        int size=bonusList.size();
        Random random = new Random();
        int temp;
        int rand;
        Bonus bonus;
        temp = random.nextInt(10) + 1;
        rand = random.nextInt(size);
        bonus = bonusList.get(rand);
        System.out.println("Choosed : "+bonus);
        int prob = bonus.getProba();
        if(prob>=temp) {
            System.out.println(prob+" >= "+temp+" ? --> YES");
            ArrayList<Player> players=context.getPlayers();
            int rand2 = random.nextInt(players.size());
            Player player=players.get(rand2);
            Client client = player.getProxy();
            try {
                client.receiveDrop(bonus);
                player.addBonus(bonus);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println(prob+" >= "+temp+" ? --> NO");
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
        if(looser instanceof Monster)
            sendDrop(winners,looser);
        // TODO : ADD TO DEAD PEOPLE
        for(Fight fight:fightsToStop){
            fight.endFight();
        }
        endFight();
    }

    public void runnaway(Participant runner){
        Participant forward=this.getOtherParticipant(runner);
        ArrayList<Player> players = context.getPlayers();
        for(Player player : players){
            Client client = player.getProxy();
            try {
                client.alertRunnaway(forward, runner);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        this.endFight();
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
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            return;
        }
        while(!this.isInterrupted()){
            if(forward.isAlive() && attacked.isAlive()){
            Participant notChoosed;
            Participant choosed=chooseParticipant();
            if(forward==choosed){
                notChoosed=attacked;
            }else{
                notChoosed=forward;
            }
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
                sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
        return;
    }
}
