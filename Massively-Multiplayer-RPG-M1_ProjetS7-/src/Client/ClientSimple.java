package Client;

import GUI.PrincipalGUI;
import Game.*;
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

import static java.lang.Thread.sleep;

public class ClientSimple {

    private PrincipalGUI gui;
    private Client proxy;
    private OperationCenter noc;
    private Context context;
    private Session session;

    public ClientSimple() {
        try {
            Client proxy=new ClientImpl(this);
            this.proxy=proxy;
            /* Set the Nimbus look and feel */
            //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
            /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
             * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
             */
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(PrincipalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(PrincipalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(PrincipalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(PrincipalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>
            //</editor-fold>

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    gui=new PrincipalGUI();
                    gui.setVisible(true);
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public PrincipalGUI getGui() {
        return gui;
    }

    public void setGui(PrincipalGUI gui) {
        this.gui = gui;
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
        gui.getInformationsGUI1().getLabyrinthGUI1().startFight(forward,attacked);
    };

    public void hitpoints(Participant forward, Participant attacked, int hitpoints){
        if(attacked instanceof Player){
            Player myplayer = session.getPlayer();
            if(attacked.getName().equals(myplayer.getName())){
                System.out.println(forward.getName()+" vous fait perdre "+hitpoints+" pts de vie !");
                myplayer.hitpoints(hitpoints);
            }else{
                System.out.println(forward.getName()+" fait perdre "+hitpoints+" pts de vie à "+attacked.getName()+" !");
                Player player = context.getPlayer(attacked.getName());
                if(player!=null){
                    player.hitpoints(hitpoints);
                }
            }
        }else if(attacked instanceof Monster){
            System.out.println(forward.getName()+" fait perdre "+hitpoints+" pts de vie à "+attacked.getName()+" !");
            Monster monster=context.getMonster(attacked.getName());
            monster.hitpoints(hitpoints);
        }
        gui.getInformationsGUI1().getLabyrinthGUI1().hitpoints(forward,attacked,hitpoints);
        gui.getInformationsGUI1().getLabyrinthGUI1().initJTree();
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
        gui.getInformationsGUI1().getLabyrinthGUI1().endFight(winners,looser);
    };

    public void newFight(String attacked){
        Labyrinth labyrinth = session.getProxy();
        String idHall = session.getHall();
        Player myplayer = session.getPlayer();
        String myname = myplayer.getName();
        try {
            labyrinth.newFight(idHall,myname,attacked);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void runnaway(String forward){
        Labyrinth labyrinth = session.getProxy();
        String idHall = session.getHall();
        Player myplayer = session.getPlayer();
        String myname = myplayer.getName();
        try {
            labyrinth.runnaway(idHall,forward,myname);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void alertRunnaway(Participant forward, Participant runner) {
        System.out.println(runner.getName()+" fuit le combat face à "+forward.getName()+" !");
        gui.getInformationsGUI1().getLabyrinthGUI1().alertRunnaway(forward,runner);
    }

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
        gui.getInformationsGUI1().getLabyrinthGUI1().heal();
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
        client.initialisation();;
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir le nom de votre personnage :");
        String str = sc.nextLine();
        System.out.println("Veuillez saisir le nom du personnage que vous souhaitez attaquer :");
        String str2 = sc.nextLine();
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
        if(str.equals("Chupacabra")) {
            try {
                sleep(9000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            client.newFight(str2);
            try {
                sleep(9000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            client.runnaway(str2);
        }
    }

}
