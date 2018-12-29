package Game;

import java.util.HashSet;

public class FightsManagement extends Thread {

    private HashSet<Fight> fights;

    public FightsManagement() {
        this.fights = new HashSet<Fight>();
    }

    public void addFight(Fight fight){
        this.fights.add(fight);
    }

    public void run(){
    }
}
