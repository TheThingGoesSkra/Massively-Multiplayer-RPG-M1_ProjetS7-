package OperationCenter;

import Client.Session;
import Game.*;
import Labyrinth.Labyrinth;
import Labyrinth.LabyrinthSimple;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.util.*;

public class OperationCenterSimple {

    private LabyrinthSimple labyrinth;
    HashMap<Labyrinth,ArrayList<String>> resp;
    private ArrayList<String> halls;
    private  ArrayList<Bonus> tamponBonus;
    private GestionBDD myBDD;
    private int numPortMessaging;
    private String hostMessaging;

    public OperationCenterSimple(){
        String url = "jdbc:mysql://localhost:3306/projets7";
        url += "?autoReconnect=true&useSSL=false&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
        String user = "root";
        String passwd = "";
        myBDD = new GestionBDD(url,user,passwd);
        halls=new ArrayList<String>();
        resp=new HashMap<Labyrinth,ArrayList<String>>();
        tamponBonus=new ArrayList<Bonus>();
    }

    public void initialisation(String labyrinthName){

        // Récupération des infos dans la table labyrinthe
        String where="name="+"\""+labyrinthName+"\"";
        myBDD.requeteSelect("*", "labyrinth", where);
        List<String> Res = myBDD.getResult().get(0);
        String idLabyrinth=Res.get(0);
        String labyrinthname=Res.get(1);
        //myBDD.printResultData();
        // Création du labyrinthe
        this.labyrinth=new LabyrinthSimple(idLabyrinth,labyrinthName);
        // Savoir quels bonus sont présents dans le labyrinthe

        where="idlabyrinth="+"\""+idLabyrinth+"\"";
        myBDD.requeteSelect("*", "bonusestdanslabyrinth", where);
        //myBDD.printResultData();
        List<List<String>> resultats = myBDD.getResult();
        for (List<String> ligne : resultats) {
            String idBonus=ligne.get(0);
            // Récupération des infos pour chaque bonus
            where="idBonus="+"\""+idBonus+"\"";
            myBDD.requeteSelect("*", "bonus", where);
            //myBDD.printResultData();
            List<List<String>> resultatsBonus = myBDD.getResult();
            List<String> ligne2=resultatsBonus.get(0);
            String name = ligne2.get(1);
            int life = Integer.parseInt(ligne2.get(2));
            int attack = Integer.parseInt(ligne2.get(3));
            int resilience = Integer.parseInt(ligne2.get(4));
            int chance = Integer.parseInt(ligne2.get(5));
            int maxlife = Integer.parseInt(ligne2.get(6));
            int proba = Integer.parseInt(ligne2.get(7));
            Bonus bonus = new Bonus(idBonus,name,life,attack,resilience,chance,maxlife,proba);
            System.out.println(bonus);
            tamponBonus.add(bonus);
        }

        // Savoir quels monstres sont présents dans le labyrinthe
        where="idlabyrinth="+"\""+idLabyrinth+"\"";
        myBDD.requeteSelect("*", "monstreestdanslabyrinth", where);
        //myBDD.printResultData();
        resultats = myBDD.getResult();
        ArrayList<Monster> tamponMonsters=new ArrayList<Monster>();
        for (List<String> ligne : resultats) {
            // Récupération des infos pour chaque Monstre
            String idMonstre=ligne.get(0);
            where="idmonstre="+"\""+idMonstre+"\"";
            myBDD.requeteSelect("*", "monstre", where);
            //myBDD.printResultData();
            List<String> infosMonstre= myBDD.getResult().get(0);
            String idMonster = infosMonstre.get(0);
            String name = infosMonstre.get(1);
            int maxLife = Integer.parseInt(infosMonstre.get(2));
            int attack =Integer.parseInt(infosMonstre.get(3));
            int chance = Integer.parseInt(infosMonstre.get(4));
            int resilience = Integer.parseInt(infosMonstre.get(5));
            int boss = Integer.parseInt(infosMonstre.get(6));
            // Création du monstre
            Monster monster = new Monster(idMonster, name, attack, chance,
                    resilience, maxLife, boss);
            // Savoir quels bonus sont distribués par le monstre
            where="idmonstre="+"\""+idMonstre+"\"";
            myBDD.requeteSelect("*", "monstredistribue", where);
            //myBDD.printResultData();
            resultats = myBDD.getResult();
            for (List<String> ligne2 : resultats) {
                // insertion des bonus dans le monstre
                // TODO Insérer les bonus (getBonus(...))
                 String idBonus = ligne2.get(1);
                 Bonus bonus = getBonus(idBonus,tamponBonus);
                 int life = bonus.getLife();
                 name=bonus.getName();
                 attack = bonus.getAttack();
                 resilience = bonus.getResilience();
                 chance = bonus.getChance();
                 maxLife = bonus.getMaxlife();
                 int proba = bonus.getProba();
                 Bonus bonus2 = new Bonus(idBonus,name,life,attack,resilience,chance,maxLife,proba);
                 monster.addBonus(bonus2);
            }
            tamponMonsters.add(monster);
        }

        // Récupératon des infos pour chaque porte présente dans le labyrinthe
        where="idlabyrinth="+"\""+idLabyrinth+"\"";
        myBDD.requeteSelect("*", "porte", where);
        //myBDD.printResultData();
        resultats = myBDD.getResult();
        // Création d'une liste pour stocker les portes temporairement
        List<Door> tamponDoors=new ArrayList<Door>();
        for (List<String> ligne : resultats) {
            String idPorte=ligne.get(0);
            // Création de la porte
            Door door = new Door(idPorte);
            // Ajoute de la porte dans la liste
            tamponDoors.add(door);
        }

        // Récupération des infos pour chaque hall
        where="idlabyrinth="+"\""+idLabyrinth+"\"";
        myBDD.requeteSelect("*", "hall", where);
        //myBDD.printResultData();
        resultats = myBDD.getResult();
        List<Hall> tamponHalls=new ArrayList<Hall>();
        for (List<String> ligne : resultats) {
            // Création des Halls
            String idHall=ligne.get(0);
            String name=ligne.get(1);
            String idTypeHall=ligne.get(2);
            int x=Integer.parseInt(ligne.get(4));
            int y=Integer.parseInt(ligne.get(5));
            Hall hall=new Hall("", idHall, name, idTypeHall,x,y);
            tamponHalls.add(hall);
            halls.add(idHall);
        }

        // Trouver les portes correspondant à chaque hall
        for (Hall hall : tamponHalls) {
            String idHall = hall.getIdHall();
            where = "idHall=" + "\"" + idHall + "\"";
            myBDD.requeteSelect("*", "hallestcompose", where);
            //myBDD.printResultData();
            resultats = myBDD.getResult();
            for (List<String> ligne2 : resultats) {
                // Mettre les portes dans chaque hall
                String idDoor = ligne2.get(1);
                String tamponDirection = ligne2.get(2);
                Pole direction = null;
                switch (tamponDirection) {
                    case "North":
                        direction = Pole.NORTH;
                        break;
                    case "South":
                        direction = Pole.SOUTH;
                        break;
                    case "West":
                        direction = Pole.WEST;
                        break;
                    case "East":
                        direction = Pole.EAST;
                        break;
                }
                if (direction != null) {
                    Door door = getDoor(idDoor, tamponDoors);
                    door.addHall(hall);
                    hall.addDoor(door, direction);
                } else {
                    System.err.println("Error with the direction of a labyrinth door");
                }
            }
            String idTypeHall = hall.getIdType();
            where = "idtypehall=" + "\"" + idTypeHall + "\"";
            myBDD.requeteSelect("*", "monstreestdans", where);
            //myBDD.printResultData();
            resultats = myBDD.getResult();

            for (List<String> ligne2 : resultats) {
                // Mettre monstres dans chaque hall
                String idMonster = ligne2.get(0);
                Monster monster = getMonster(idMonster, tamponMonsters);
                if (monster != null) {
                    hall.addMonster(new Monster(monster.getIdMonster(), monster.getName(), monster.getAttack(), monster.getChance(), monster.getResilience(), monster.getMaxlife(), monster.getBoss(), monster.getListeBonus()));
                } else {
                    System.err.println("Error : no monster add in Hall " + idTypeHall);
                }
            }
            // Ajout du hall dans le labyrinth
            this.labyrinth.addHall(hall);
        }
            // Tests
            HashSet<Hall> halls2=this.labyrinth.getHalls();
            for (Hall hall2: halls2) {
                System.out.println("Hall : {"+hall2.getIdHall()+", "+hall2.getName()+", "+hall2.getIdType()+"}");
                HashMap<Pole,Door> doors=hall2.getDoors();
                for (Pole direction : doors.keySet()){
                    Door door=doors.get(direction);
                    Hall h1=door.getHall1();
                    String id1;
                    if(h1!=null) {
                        id1 = h1.getIdHall();
                    }else{
                        id1 = "null";
                    }
                    Hall h2=door.getHall2();
                    String id2;
                    if(h2!=null) {
                        id2 = h2.getIdHall();
                    }else{
                        id2 = "null";
                    }
                    System.out.println("Porte : {"+direction+", "+door.getIdDoor()+", "+id1+", "+id2+"}");
                }
                Context context = hall2.getContext();
                ArrayList<Monster> monsters= context.getMonsters();
                for (Monster monster : monsters) {
                    System.out.println("Monster : {"+monster.getIdMonster()+", "+monster.getName()+"}");
                }
            }
        }

    public Bonus getBonus(String idBonus, List<Bonus> bonus){
        for (Bonus tampon : bonus ) {
            if(tampon.getIdBonus().equals(idBonus)){
                return tampon;
            }
        }
        return null;
    };

    public Monster getMonster(String idMonster, List<Monster> monsters){
        for (Monster tampon : monsters ) {
            if(tampon.getIdMonster().equals(idMonster)){
                return tampon;
            }
        }
        return null;
    };

    public LabyrinthSimple getLabyrinth() {
        return labyrinth;
    }

    public void setLabyrinth(LabyrinthSimple labyrinth) {
        this.labyrinth = labyrinth;
    }

    public Door getDoor(String idDoor, List<Door> doors){
        for (Door tampon : doors ) {
            if(tampon.getIdDoor().equals(idDoor)){
                return tampon;
            }
        }
        return null;
    };

    public void addServerResponsabilities(Labyrinth server) {
        resp.put(server, new ArrayList<String>());
    };

    public Session identification(String name) {
        Player player;
        Session session;
        String where = "idplayer=" + "\"" + name + "\"";
        this.myBDD.requeteSelect("*", "player", where);
        List<List<String>> res = this.myBDD.getResult();
        if (!res.isEmpty()) {
            List<String> res1 = res.get(0);
            this.myBDD.printResultData();
            player = new Player(res1.get(0), Integer.parseInt(res1.get(2)),
                    Integer.parseInt(res1.get(3)), Integer.parseInt(res1.get(4)), Integer.parseInt(res1.get(1)));
            String idLabyrinth = "0";
            where = "idplayer=" + "\"" + name + "\"";
            this.myBDD.requeteSelect("*", "session", where);
            res = this.myBDD.getResult();
            if (!res.isEmpty()) {
                res1 = res.get(0);
                String idHall = res1.get(2);
                Hall hall=labyrinth.getHall(idHall);
                int x=hall.getx();
                int y=hall.gety();
                String life = res1.get(3);
                player.setLife(Integer.parseInt(life));
                // TODO : Intégrer adresse serveur messagerie
                session = new Session(player, idLabyrinth, idHall, x, y);
            }else{
                String idHall = "0";
                int x=2;
                int y=1;
                player.setLife(player.getMaxlife());
                session = new Session(player, idLabyrinth, idHall, x, y);
            }
        } else {
                String values = "(\"" + name + "\",10,1,1,1,0)";
                this.myBDD.requeteInsertInto("player", values, "");
                player = new Player(name, 10, 1, 1, 1, 10);
                String idLabyrinth = "0";
                String idHall = "0";
                int x=2;
                int y=1;
                session = new Session(player, idLabyrinth, idHall, x , y);
        }
        where = "idplayer=" + "\"" + name + "\"";
        this.myBDD.requeteSelect("*", "playerdispose", where);
        res = this.myBDD.getResult();
        for(List<String> disp : res){
            String idBonus= disp.get(1);
            Bonus bonus = getBonus(idBonus,tamponBonus);
            player.addBonus(bonus);
        }
        String idHall = session.getIdHall();
        for(Labyrinth key : resp.keySet()){
            for(String hall : resp.get(key)){
                if(hall.equals(idHall)){
                    session.setProxy(key);
                    break;
                }
            }
        }
        session.setNumPortMessaging(this.numPortMessaging);
        session.setHostMessaging(this.hostMessaging);
        return session;
    }


    public void save(ArrayList<Player> players, String labyrinth, String Hall) {};

    public ArrayList<String> recordMessagerie(String host,int numPort) {
        this.numPortMessaging=numPort;
        this.hostMessaging=host;
        return halls;
    };

    public void buildDistribution() throws RemoteException {
        // distribuer chaque hall à un serveur  dans la structure
        Iterator<Labyrinth> iterator=resp.keySet().iterator();
        for(String hall : halls){
            if (!iterator.hasNext()) {
                iterator=resp.keySet().iterator();
            }
            Labyrinth tampon=iterator.next();
            resp.get(tampon).add(hall);
        }
        for(Labyrinth tampon : resp.keySet()){
            try {
                tampon.setReponsabiities(resp);
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws RemoteException {
        OperationCenterSimple noc=new OperationCenterSimple();
        noc.initialisation("The Nebias labyrinth");
        try {
            LocateRegistry.createRegistry(1099);
            OperationCenterImpl impl=new OperationCenterImpl(noc);
            String url = "rmi://localhost/ServerNocRMI";
            System.out.println("Enregistrement de l'objet avec l'url : " + url);
            Naming.rebind(url, impl);
            System.out.println("Serveur lancé");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir start lorsque vous aurez terminé d'enregistrer les serveurs de jeux :");
        String str = sc.nextLine();
        if(str.equals("start")){
            noc.buildDistribution();
        }
        while(true){

        }
    }
    }

