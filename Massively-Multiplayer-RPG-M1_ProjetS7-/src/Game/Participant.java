package Game;

import java.io.Serializable;
import java.util.ArrayList;

public class Participant implements Serializable{

    private String name;
    private int life;
    private int attack;
    private int resilience;
    private int chance;
    private int maxlife;
    private ArrayList<Bonus> listeBonus;

    public Participant(String name, int life, int attack, int resilience, int chance, int maxlife) {
        this.name = name;
        this.life = life;
        this.attack = attack;
        this.resilience = resilience;
        this.chance = chance;
        this.maxlife = maxlife;
        this.listeBonus=new ArrayList<Bonus>();
    }

    public Participant(String name, int attack, int resilience, int chance, int maxlife) {
        this.name = name;
        this.attack = attack;
        this.resilience = resilience;
        this.chance = chance;
        this.maxlife = maxlife;
        this.listeBonus=new ArrayList<Bonus>();
    }


    public Participant(String name, int life, int attack, int resilience, int chance, int maxlife, ArrayList<Bonus> bonus) {
        this.name = name;
        this.life = life;
        this.attack = attack;
        this.resilience = resilience;
        this.chance = chance;
        this.maxlife = maxlife;
        this.listeBonus=bonus;
    }

    public void useBonus(Bonus bonus){
        int attack = bonus.getAttack();
        int resilience = bonus.getResilience();
        int chance = bonus.getChance();
        int life = bonus.getLife();
        int maxLife =  bonus.getMaxlife();
        this.attack=this.attack+attack;
        this.resilience=this.resilience+resilience;
        if(chance!=0)
            this.chance=chance;
        this.life=this.life+life;
        this.maxlife=this.maxlife+maxLife;
    }

    public ArrayList<Bonus> getListeBonus() {
        return listeBonus;
    }

    public void setListeBonus(ArrayList<Bonus> listeBonus) {
        this.listeBonus = listeBonus;
    }

    public void addBonus(Bonus bonnus){
        listeBonus.add(bonnus);
    }

    public void removeBonus(Bonus bonus){
        int index=0;
        for(Bonus bonus1 : listeBonus){
            if(bonus.getName().equals(bonus1.getName())) {
                listeBonus.remove(index);
                break;
            }else{
                index++;
            }
        }
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
