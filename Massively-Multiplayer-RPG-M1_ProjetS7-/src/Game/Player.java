package Game;

import java.io.Serializable;
import java.util.List;

import Client.*;
import OperationCenter.GestionBDD;

public class Player extends Participant implements Serializable{

    private Client proxy;

    public Player(String name, int life, int attack, int resilience, int chance, Client proxy, int maxlife) {
        super(name, life, attack, resilience, chance, maxlife );
        this.proxy = proxy;
    }

    public Player(String name, int life, int attack, int resilience, int chance, int maxlife) {
        super(name, life, attack, resilience, chance, maxlife );
    }

    public Player(String name, int attack, int resilience, int chance, int maxLife) {
        super(name, attack, resilience, chance, maxLife);
    }

    public Client getProxy() {
        return proxy;
    }

    public void setProxy(Client proxy) {
        this.proxy = proxy;
    }
}
