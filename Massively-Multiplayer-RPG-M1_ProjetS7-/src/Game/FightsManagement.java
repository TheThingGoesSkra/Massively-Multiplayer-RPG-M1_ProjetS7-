package Game;

import java.util.HashSet;

public class FightsManagement extends Thread {

    private HashSet<Fight> fights;

    public FightsManagement() {
        fights = new HashSet<Fight>();
    }

    public synchronized void addFight(Fight fight){
        fights.add(fight);
        notify();
    }

    public void waitFight() throws InterruptedException {
        while(fights.size()==0){
            wait();
        }
    }

    public void fight(){
        //TODO : Version test
        for (Fight fight : fights ) {
            fight.getAttacked().setLife(2);
        }
    }

    public void run(){
        try {
            waitFight();
        } catch (InterruptedException e) {
            System.out.println("is a test");
        }
        fight();
    }
}
