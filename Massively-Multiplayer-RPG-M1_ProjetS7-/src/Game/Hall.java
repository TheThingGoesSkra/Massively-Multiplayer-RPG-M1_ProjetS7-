package Game;

import java.util.*;

public class Hall {
    private String name;
    private static Map<Pole, Door> Hall_liste = new HashMap<Pole, Door>();
    Context context = new Context();
    private ArrayList<Fight> fights;
    boolean monster = false;
    boolean player = false;
    public Hall(String name) {

        this.name = name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void changeHall(String player, Pole pole){

    }


    public Door getDoor(Pole pole) {
        Door door = Hall_liste.get(pole);
        return door;
    }

    public void addDoor(Door door, Pole pole) {
        Hall.Hall_liste.put(pole, door);

    }

    public void addMonster(Monster monster) {
        context.addMonster(monster);

    }

    public void removeMonster(Monster monster) {
        context.removeMonster(monster);

    }

    public void addPlayer(Player player) {
        context.addPlayer(player);

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


        for (int i = 0; i < players.size(); i++) {
            if (participant1 == players.get(i)) {
                this.player = true;
            }

        }
        for (int j = 0; j < players.size(); j++) {
            if (participant2 == players.get(j)) {
                this.monster = true;
            }
        }

        if (this.monster && this.player == true) {

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
