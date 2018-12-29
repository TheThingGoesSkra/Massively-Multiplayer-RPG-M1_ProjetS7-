package Game;

public class Participant {

    private String name;
    private int life;
    private int attack;
    private int resilience;
    private int chance;

    public Participant(String name, int life, int attack, int resilience, int chance) {
        this.name = name;
        this.life = life;
        this.attack = attack;
        this.resilience = resilience;
        this.chance = chance;
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
}
