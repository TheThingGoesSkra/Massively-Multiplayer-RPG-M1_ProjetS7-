package Game;

import java.util.List;

import Client.*;
import OperationCenter.GestionBDD;

public class Player extends Participant {

    private Client proxy;
    
    public static Participant Identification(String id) {
    	
    	String url = "jdbc:mysql://localhost:3306/projets7";
        url += "?autoReconnect=true&useSSL=false&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
        String user = "root";
        String passwd = "";
        GestionBDD myBDD = new GestionBDD(url,user,passwd);
        
        String where="idplayer="+"\""+id+"\"";
        
        myBDD.requeteSelect("*", "player", where);
        List<String> Res = myBDD.getResult().get(0);
        
        myBDD.printResultData();
    	
        Participant Joueur = new Participant(Res.get(0),
        		Integer.parseInt(Res.get(1)),Integer.parseInt(Res.get(2)),
        		Integer.parseInt(Res.get(3)),Integer.parseInt(Res.get(4)),
        		Integer.parseInt(Res.get(5))); 
    	
    	return Joueur;
    	
    }

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
    
    public static void main(String[] args){
    	Identification("Arthemis");   
    }
}
