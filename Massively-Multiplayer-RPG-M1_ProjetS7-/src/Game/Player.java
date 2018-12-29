package Game;

import Client.*;

public class Player extends Participant {

    private Client proxy;

    public Player(String name, int life, int attack, int resilience, int chance, Client proxy) {
        super(name, life, attack, resilience, chance);
        this.proxy = proxy;
    }

    public Client getProxy() {
        return proxy;
    }

    public void setProxy(Client proxy) {
        this.proxy = proxy;
    }
}
