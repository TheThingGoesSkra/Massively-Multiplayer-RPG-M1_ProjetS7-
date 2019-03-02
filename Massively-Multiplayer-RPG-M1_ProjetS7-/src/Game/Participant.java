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

    public Participant(String name, int attack, int resilience, int chance, int maxlife) {
        this.name = name;
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

    public Boolean hitpoints(int hitpoints){
        if(this.life>0) {
            this.life = this.life - hitpoints;
            if(this.life<=0){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    public void heal(){
        this.life=this.maxlife;
    }

    public void heal(int healpoints){
        this.life=this.life+healpoints;
    }

    public Boolean isAlive(){
        if(life>0){
            return true;
        }else{
            return false;
        }
    }

    public String toString(){
        return name;
    }
}
