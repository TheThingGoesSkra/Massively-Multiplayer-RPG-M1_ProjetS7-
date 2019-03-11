package Game;

import java.io.Serializable;

public class Monster extends Participant implements Serializable{

    private String idMonster;
    private int boss;
    public Monster(String idMonster, String name, int attack, int resilience, int chance, int maxLife, int boss) {
        super(name, maxLife, attack, resilience, chance, maxLife);
        this.idMonster=idMonster;
        this.boss=boss;
    }

    public int getBoss() {
        return boss;
    }

    public String getIdMonster() {
        return this.idMonster;
    }
}
