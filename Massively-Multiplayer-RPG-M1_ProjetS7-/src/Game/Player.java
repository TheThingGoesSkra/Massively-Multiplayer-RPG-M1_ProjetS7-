package Game;

import java.util.ArrayList;
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
        
        Participant Joueur = new Participant(id,0,0,0,0,0); 
        try 
        {
        	String where="idplayer="+"\""+id+"\"";
        	myBDD.requeteSelect("*", "player", where);
        	List<List<String>> Res = myBDD.getResult();
        	List<String> Res1 = Res.get(0);
        	myBDD.printResultData();
    	
        	Joueur = new Participant(Res1.get(0),
        			Integer.parseInt(Res1.get(1)),Integer.parseInt(Res1.get(2)),
        			Integer.parseInt(Res1.get(3)),Integer.parseInt(Res1.get(4)),
        			Integer.parseInt(Res1.get(5))); 
    	
    	
        }
        catch (Exception e)
        {
        	//myBDD.requete("insert into player (idplayer,life,attaque,"
        	//		+ "resistance,chance,idclasse) values "
        	//	+ "\"joueur inexistant\",0,0,0,0,0)",0);
        	String values= "(\""+id+"\",0,0,0,0,0)";
        	myBDD.requeteInsertInto("player" , values);
        }
		
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
    	Identification("Joueurnonexistant");   
    }
}
