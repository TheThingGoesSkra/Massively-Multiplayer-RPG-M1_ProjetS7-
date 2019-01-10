package Game;

import Client.*;

public class Player extends Participant {

    private Client proxy;

    public Player(String name, int life, int attack, int resilience, int chance, Client proxy, int maxlife) {
        super(name, life, attack, resilience, chance, maxlife );
        this.proxy = proxy;
    }

    public Client getProxy() {
        return proxy;
    }

    public void setProxy(Client proxy) {
        this.proxy = proxy;
    }
}
