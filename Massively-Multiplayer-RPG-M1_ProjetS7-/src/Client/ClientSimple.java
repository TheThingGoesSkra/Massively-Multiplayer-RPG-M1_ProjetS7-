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
    };
    public void setHall(String Hall){};
    public void setLabyrinthServer(Labyrinth server){};
    public void startFight(Participant forward, Participant attacked){};
    public void hitpoints(Participant forward, Participant attacked, int hitpoints){};
    public void endFight(ArrayList<Participant> winners, Participant looser) {};


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
        client.nocConnection("Chupacabra");
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
