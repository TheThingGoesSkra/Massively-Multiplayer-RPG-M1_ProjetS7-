package Game;

import java.io.Serializable;

public class Monster extends Participant implements Serializable{

    private String idMonster;

    public Monster(String idMonster, String name, int life, int attack, int resilience, int chance, int maxLife) {
        super(name, life, attack, resilience, chance, maxLife);
        this.idMonster=idMonster;
    }

    public String getIdMonster() {
        return this.idMonster;
    }
}
