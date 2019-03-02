package Client;

import Game.Context;
import Game.Monster;
import Game.Participant;
import Game.Player;
import Labyrinth.Labyrinth;
import OperationCenter.OperationCenter;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientSimple {

    private Client proxy;
    private OperationCenter noc;
    private Context context;
    private Session session;

    public ClientSimple() {
        try {
            Client proxy=new ClientImpl(this);
            this.proxy=proxy;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setContext(Context context){
        this.context=context;
    };

    public void setNoc(OperationCenter noc){
        this.noc=noc;
    };

    public Client getProxy() {
        return proxy;
    }

    public void setProxy(Client proxy) {
        this.proxy = proxy;
    }

    public OperationCenter getNoc() {
        return noc;
    }

    public Context getContext() {
        return context;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void addPlayer(Player player){
        context.addPlayer(player);
        System.out.println("Nouveau context : "+context.getPlayers().toString());
    };
    public void setHall(String Hall){};
    public void setLabyrinthServer(Labyrinth server){};

    public void startFight(Participant forward, Participant attacked){
        System.out.println(forward.getName()+" attaque "+attacked.getName()+" !");
    };

    public void hitpoints(Participant forward, Participant attacked, int hitpoints){
        if(attacked instanceof Player){
            Player myplayer = session.getPlayer();
            if(attacked.getName().equals(myplayer.getName())){
                System.out.println(forward.getName()+" vous fait perdre "+hitpoints+" pts de vie !");
                myplayer.hitpoints(hitpoints);
                System.out.println("Votre vie : "+myplayer.getLife());
            }else{
                System.out.println(forward.getName()+" fait perdre "+hitpoints+" pts de vie à "+attacked.getName()+" !");
                Player player = context.getPlayer(attacked.getName());
                if(player!=null){
                    player.hitpoints(hitpoints);
                }
                System.out.println("Sa vie : "+player.getLife());
            }
        }else if(attacked instanceof Monster){
            System.out.println(forward.getName()+" fait perdre "+hitpoints+" pts de vie à "+attacked.getName()+" !");
            Monster monster=context.getMonster(attacked.getName());
            monster.hitpoints(hitpoints);
            System.out.println("Sa vie : "+monster.getLife());
        }
    };

    public void endFight(ArrayList<Participant> winners, Participant looser) {
        System.out.println("Perdant : "+looser.getName()+" Gagnants : "+winners.toString());
        for(Participant winner : winners){
            if(winner instanceof Player){
                Player myplayer = session.getPlayer();
                if(winner.getName().equals(myplayer.getName())){
                    myplayer.heal(1);
                }else{
                    Player player = context.getPlayer(winner.getName());
                    player.heal(1);
                }
            }else if(winner instanceof Monster){
                Monster monster = context.getMonster(winner.getName());
                monster.heal(1);
            }
        }
    };

    public void heal() throws RemoteException{
        System.out.println("Tous le monde est soigné !");
        Player myplayer = session.getPlayer();
        myplayer.heal();
        ArrayList<Player> players=context.getPlayers();
        for(Player player:players) {
            player.heal();
        }
        ArrayList<Monster> monsters=context.getMonsters();
        for(Monster monster:monsters) {
            monster.heal();
        }
    };


    public void initialisation(){
        OperationCenter r = null;
        try {
            r = (OperationCenter) Naming.lookup("rmi://localhost/ServerNocRMI");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        setNoc((OperationCenter)r);
    }

    public void nocConnection(String name){
        try {
            this.session=noc.identification(name);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void labyrinthConnection(){
        Labyrinth labyrinth=session.getProxy();
        try {
            labyrinth.login(session,proxy);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws RemoteException {

        ClientSimple client=new ClientSimple();
        client.initialisation();
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir le nom de votre personnage :");
        String str = sc.nextLine();
        client.nocConnection(str);
        // test Noc connection
        Session session=client.getSession();
        Player player=session.getPlayer();
        String name=player.getName();
        int life=player.getLife();
        String idHall=session.getIdHall();
        Labyrinth proxy=session.getProxy();
        System.out.println("player:"+name+" , Life:"+life+", idHall:"+idHall+", proxy:"+proxy);
        // test Labyrinth connection
        client.labyrinthConnection();
        Context context=client.getContext();
        List<Player> players=context.getPlayers();
        List<Monster> monsters=context.getMonsters();
        System.out.println("Monsters : "+monsters.toString());
        System.out.println("Players : "+players.toString());

    }
}
