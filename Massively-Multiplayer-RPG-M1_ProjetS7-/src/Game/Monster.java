package Game;

public class Monster extends Participant {

    private String idMonster;

    public Monster(String idMonster, String name, int life, int attack, int resilience, int chance, int maxLife) {
        super(name, life, attack, resilience, chance, maxLife);
        this.idMonster=idMonster;
    }

    public String getIdMonster() {
        return this.idMonster;
    }
}
