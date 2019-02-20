package Game;

import java.io.Serializable;

public class Participant implements Serializable{

    private String name;
    private int life;
    private int attack;
    private int resilience;
    private int chance;
    private int maxlife;

    public Participant(String name, int life, int attack, int resilience, int chance, int maxlife) {
        this.name = name;
        this.life = life;
        this.attack = attack;
        this.resilience = resilience;
        this.chance = chance;
        this.maxlife = maxlife;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getResilience() {
        return resilience;
    }

    public void setResilience(int resilience) {
        this.resilience = resilience;
    }

    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    public int getMaxlife() {return maxlife;}

    public void setMaxlife(int maxlife) {this.maxlife = maxlife;}

    public void heal(){

    }

    public void heal(int soin){

    }
}
