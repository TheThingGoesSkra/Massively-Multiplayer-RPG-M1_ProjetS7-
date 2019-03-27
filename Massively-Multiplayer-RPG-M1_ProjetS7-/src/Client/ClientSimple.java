package Client;

import GUI.*;
import Game.*;
import Labyrinth.Labyrinth;
import OperationCenter.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class ClientSimple {

    private PrincipalGUI gui;
    private Client proxy;
    private OperationCenter noc;
    private static Context context;
    private Session session;
    private Messaging messaging;
    private GestionBDDI myBDD;

    public ClientSimple() {
        try {
            Client proxy=new ClientImpl(this);
            this.proxy=proxy;
            this.context=new Context();
            String url = "jdbc:sqlite:projets7.sqlite";
            this.myBDD = new GestionBDDI(url);
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

    public void startGUI(){
        /* Set the Nimbus look and feel */
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
        gui=new PrincipalGUI(this);
        gui.setVisible(true);
    }

    public void setContextSimple(Context context){
        this.context=context;
    }

    public void setContext(Context context){
        ArrayList<Monster> monsters = this.context.getMonsters();
        ArrayList<Player> players = this.context.getPlayers();
        monsters.removeAll(monsters);
        monsters.addAll(context.getMonsters());
        players.removeAll(players);
        players.addAll(context.getPlayers());
        players.add(session.getPlayer());
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.getInformationsGUI1().getLabyrinthGUI1().removeAll();
                gui.getInformationsGUI1().getLabyrinthGUI1().initJTree();
            }
        });
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

    public void addParticipant(Participant participant){
        if(participant instanceof Player) {
            context.addPlayer((Player)participant);
        }else if(participant instanceof Monster){
            context.addMonster((Monster)participant);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.getInformationsGUI1().getLabyrinthGUI1().ajouterParticipant(participant);
            }
        });
    };

    public void removePlayer(Player player){
        context.removePlayer(player);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.getInformationsGUI1().getLabyrinthGUI1().removePlayer(player);
            }
        });
    };

    public void setHall(String idHall){
        session.setIdHall(idHall);
    };


    public void setLabyrinthServer(Labyrinth server){
        session.setProxy(server);
    };

    public void startFight(Participant forward, Participant attacked){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.getInformationsGUI1().getLabyrinthGUI1().startFight(forward,attacked);
            }
        });
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.getInformationsGUI1().getLabyrinthGUI1().hitpoints(forward,attacked,hitpoints);
            }
        });
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
        // TODO : Ne pas supprimer si on veux pouvoir réanimer
        Participant participant=context.getParticipant(looser.getName());
        context.removeParticipant(participant);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.getInformationsGUI1().getLabyrinthGUI1().endFight(winners,looser);
            }
        });
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.getInformationsGUI1().getLabyrinthGUI1().alertRunnaway(forward,runner);
            }
        });
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.getInformationsGUI1().getLabyrinthGUI1().heal();
                ArrayList<Participant> participants=new ArrayList<Participant>();
                participants.addAll(players);
                participants.addAll(monsters);
                for(Participant participant:participants){
                    gui.getInformationsGUI1().getLabyrinthGUI1().actualiserJTree(participant,1);
                }            }
        });
    };

    public void changeHall(Pole direction){
        String where;
        List<String> res;
        String idHall=session.getIdHall();
        Player player=session.getPlayer();
        String name=player.getName();
        Labyrinth labyrinth=session.getProxy();
         try {
            int answer=labyrinth.changeHall(idHall,name,direction);
            int x, y;
            switch (answer){
                case -1 :
                    gui.getInformationsGUI1().getLabyrinthGUI1().append("Il n'y à pas de porte dans cette direction.");
                    break;
                case 0 :
                    gui.getInformationsGUI1().getLabyrinthGUI1().append("Vous devez d'abord fuir le combat avant de changer de salle...");
                    break;
                case 1 :
                    gui.getInformationsGUI1().getLabyrinthGUI1().append("Vous venez de changer de salle.");
                    idHall = session.getIdHall();
                    where="idHall="+"\""+idHall+"\"";
                    myBDD.requeteSelect("*", "Hall", where);
                    res = myBDD.getResult().get(0);
                    x=Integer.parseInt(res.get(4));
                    y=Integer.parseInt(res.get(5));
                    gui.changeHall(x,y, direction);
                    break;
                case 2 :
                    gui.getInformationsGUI1().getLabyrinthGUI1().append("Vous venez de changer de salle.");
                    idHall = session.getIdHall();
                    where="idHall="+"\""+idHall+"\"";
                    myBDD.requeteSelect("*", "Hall", where);
                    res = myBDD.getResult().get(0);
                    x=Integer.parseInt(res.get(4));
                    y=Integer.parseInt(res.get(5));
                    gui.changeHall(x,y, direction);
                    break;
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void useBonus(Participant participant, Bonus bonus){
            Player myplayer = session.getPlayer();
            if(participant.getName().equals(myplayer.getName())){
                myplayer.useBonus(bonus);
                myplayer.removeBonus(bonus);
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        gui.getActionsGUI1().getInventory().removeBonus(bonus.getName());
                    }
                });
            }else {
                Player player = context.getPlayer(participant.getName());
                if (player != null) {
                    player.useBonus(bonus);
                    player.removeBonus(bonus);
                }
            }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.getInformationsGUI1().getLabyrinthGUI1().useBonus(participant, bonus);
            }
        });
    }

    public void chooseBonus(String bonus){
        String idHall=session.getIdHall();
        String idPlayer=session.getPlayer().getName();
        Labyrinth labyrinth=session.getProxy();
        try {
            labyrinth.chooseBonus(idHall,idPlayer,bonus);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void receiveDrop(Bonus bonus){
        Player player=session.getPlayer();
        player.addBonus(bonus);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gui.getInformationsGUI1().getLabyrinthGUI1().receiveBonus(bonus);
                gui.getActionsGUI1().getInventory().addBonus(bonus.getName());
            }
        });
    }

    public void showInfosBonus(String bonusName){
        String where="name="+"\""+bonusName+"\"";
        myBDD.requeteSelect("*", "Bonus", where);
        List<String> ligne2 = myBDD.getResult().get(0);
        int life = Integer.parseInt(ligne2.get(2));
        int attack = Integer.parseInt(ligne2.get(3));
        int resilience = Integer.parseInt(ligne2.get(4));
        int chance = Integer.parseInt(ligne2.get(5));
        int maxlife = Integer.parseInt(ligne2.get(6));
        gui.getInformationsGUI1().getLabyrinthGUI1().showInfosBonus(bonusName,life,attack,resilience,chance,maxlife);
    }


    public void showInfosBonus(int idBonus){
        String where="idBonus="+"\""+idBonus+"\"";
        myBDD.requeteSelect("*", "Bonus", where);
        List<String> ligne2 = myBDD.getResult().get(0);
        String bonusName = ligne2.get(1);
        int life = Integer.parseInt(ligne2.get(2));
        int attack = Integer.parseInt(ligne2.get(3));
        int resilience = Integer.parseInt(ligne2.get(4));
        int chance = Integer.parseInt(ligne2.get(5));
        int maxlife = Integer.parseInt(ligne2.get(6));
        gui.getInformationsGUI1().getLabyrinthGUI1().showInfosBonus(bonusName,life,attack,resilience,chance,maxlife);
    }

    public void showBonusDonjon(){
        String idLabyrinth=session.getIdLabyrinth();
        String where="idlabyrinth="+"\""+idLabyrinth+"\"";
        myBDD.requeteSelect("*", "bonusestdanslabyrinth", where);
        //myBDD.printResultData();
        List<List<String>> resultats = myBDD.getResult();
        for (List<String> ligne : resultats) {
            int idBonus = Integer.parseInt(ligne.get(0));
            showInfosBonus(idBonus);
        }
    }

    public void showMonstersHall() {
        ArrayList<Monster> monsters = context.getMonsters();
        for(Monster monster : monsters){
            showInfosParticipant(monster);
        }
    }

    public void showMonstersDonjon(){

    }

    public GestionBDDI getMyBDD() {
        return myBDD;
    }

    public void setMyBDD(GestionBDDI myBDD) {
        this.myBDD = myBDD;
    }

    public void showInfosMonster(String monsterName) {
        String where="Name="+"\""+monsterName+"\"";
        myBDD.requeteSelect("*", "Monstre", where);
        List<String> ligne2 = myBDD.getResult().get(0);
        String name = ligne2.get(1);
        int maxlife = Integer.parseInt(ligne2.get(2));
        int attack = Integer.parseInt(ligne2.get(3));
        int resilience = Integer.parseInt(ligne2.get(4));
        int chance = Integer.parseInt(ligne2.get(5));
        Monster monster=new Monster("",name,attack,resilience,chance,maxlife,0);
        showInfosParticipant(monster);
    }

    public void showInfosParticipant(Participant participant){
        gui.getInformationsGUI1().getLabyrinthGUI1().showInfosParticipant(participant);
    }

    public void nocConnection(){
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

    public void identification(String name){
        try {
           noc.identification(name,this.proxy);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void receiveSession(Session session){
        this.session=session;
    }

    public void messagingConnection(){
        String name=session.getPlayer().getName();
        int numPort=session.getNumPortMessaging();
        String host=session.getHostMessaging();
        MessagerieGUI messagerieGUI=gui.getInformationsGUI1().getMessagerieGUI1();
        messaging=new Messaging(messagerieGUI,numPort,host,name);
        messaging.start();
    }


    public void labyrinthLogout(){
        Player player=session.getPlayer();
        String idPlayer=player.getName();
        String idHall=session.getIdHall();
        Labyrinth labyrinth=session.getProxy();
        try {
            labyrinth.logout(idHall,idPlayer);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void newGame(){
        Player player=session.getPlayer();
        String idPlayer=player.getName();
        String idHall=session.getIdHall();
        Labyrinth labyrinth=session.getProxy();
        try {
            labyrinth.newGame(idHall,idPlayer);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        idHall = session.getIdHall();
        String where="idHall="+"\""+idHall+"\"";
        myBDD.requeteSelect("*", "Hall", where);
        List<String> res = myBDD.getResult().get(0);
        int x=Integer.parseInt(res.get(4));
        int y=Integer.parseInt(res.get(5));
        gui.changeHall(x,y, Pole.EAST);
    }

    public void sendMessage(String message){
        messaging.sendMessage(message);
    }


    // Tests clients
   /* public static void main(String[] args) throws RemoteException {

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
            System.out.println("go!");
            client.changeHall(Pole.SOUTH);
           /*client.newFight(str2);
            try {
                sleep(9000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            client.runnaway(str2);/*
        }
    }*/

}
