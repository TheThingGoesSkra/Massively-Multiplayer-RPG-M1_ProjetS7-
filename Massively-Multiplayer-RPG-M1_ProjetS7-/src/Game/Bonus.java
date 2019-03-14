package Game;

import java.io.Serializable;

public class Bonus implements Serializable{
    private String idBonus;
    private String name;
    private int life;
    private int attack;
    private int resilience;
    private int chance;
    private int maxlife;
    private int proba;

    public Bonus(String idBonus, String name, int life, int attack, int resilience, int chance, int maxlife) {
        this.idBonus = idBonus;
        this.name = name;
        this.life = life;
        this.attack = attack;
        this.resilience = resilience;
        this.chance = chance;
        this.maxlife = maxlife;
    }

    public Bonus(String idBonus, String name, int life, int attack, int resilience, int chance, int maxlife, int proba) {
        this.idBonus = idBonus;
        this.name = name;
        this.life = life;
        this.attack = attack;
        this.resilience = resilience;
        this.chance = chance;
        this.maxlife = maxlife;
        this.proba=proba;
    }

    public String getIdBonus() {
        return idBonus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdBonus(String idBonus) {
        this.idBonus = idBonus;
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

    public int getMaxlife() {
        return maxlife;
    }

    public void setMaxlife(int maxlife) {
        this.maxlife = maxlife;
    }

    public int getProba() {
        return proba;
    }

    public void setProba(int proba) {
        this.proba = proba;
    }

    public String toString(){
        return this.name+"("+proba+")";
    }
}
