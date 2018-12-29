package Labyrinth;

import Game.*;

import static java.lang.Thread.sleep;

public class LabyrinthImpl {

    public static void main (String[] args) {

        // TODO : Version test

        System.out.println("c'est parti !");
        Player player=new Player("Boris", 10,1,0,1,null);
        Monster monster = new Monster("chupacabra",10,1,0,1);
        FightsManagement fm = new FightsManagement();
        Fight fight= new Fight(player,monster);
        fm.start();
        fm.addFight(fight);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(monster.getLife()+ "    and     "+player.getLife());
    }


}
