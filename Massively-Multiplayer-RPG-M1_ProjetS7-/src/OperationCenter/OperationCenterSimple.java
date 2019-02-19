package OperationCenter;

import Client.Session;
import Game.*;
import Labyrinth.Labyrinth;
import Labyrinth.LabyrinthSimple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OperationCenterSimple {

    private LabyrinthSimple labyrinth;
    HashMap<Labyrinth,ArrayList<String>> resp;
    private ArrayList<String> halls;
    private DAO myBDD;

    public OperationCenterSimple(){
        String url = "jdbc:mysql://localhost:3306/projets7";
        url += "?autoReconnect=true&useSSL=false&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
        String user = "root";
        String passwd = "";
        myBDD = new DAO(url,user,passwd);
    }

    public void initialisation(String labyrinthName){

        // Récupération des infos dans la table labyrinthe
        String where="name="+"\""+labyrinthName+"\"";
        myBDD.requeteSelect("*", "labyrinth", where);
        List<String> Res = myBDD.getResult().get(0);
        String idLabyrinth=Res.get(0);
        String labyrinthname=Res.get(1);
        myBDD.printResultData();
        // Création du labyrinthe
        this.labyrinth=new LabyrinthSimple(idLabyrinth,labyrinthName);
        // Savoir quels bonus sont présents dans le labyrinthe
        where="idlabyrinth="+"\""+idLabyrinth+"\"";
        myBDD.requeteSelect("*", "bonusestdanslabyrinth", where);
        myBDD.printResultData();
        List<List<String>> resultats = myBDD.getResult();
        for (List<String> ligne : resultats) {
            // Récupération des infos pour chaque bonus
            // TODO création bonus
        }

        // Savoir quels monstre sont présents dans le labyrinthe
        where="idlabyrinth="+"\""+idLabyrinth+"\"";
        myBDD.requeteSelect("*", "monstreestdanslabyrinth", where);
        myBDD.printResultData();
        resultats = myBDD.getResult();
        List<Monster> tamponMonsters=new ArrayList<Monster>();
        for (List<String> ligne : resultats) {
            // Récupération des infos pour chaque Monstre
            String idMonstre=ligne.get(0);
            where="idmonstre="+"\""+idMonstre+"\"";
            myBDD.requeteSelect("*", "monstre", where);
            myBDD.printResultData();
            List<String> infosMonstre= myBDD.getResult().get(0);
            // Création du monstre
            Monster monster = new Monster(infosMonstre.get(0), infosMonstre.get(1), Integer.parseInt(infosMonstre.get(2)), Integer.parseInt(infosMonstre.get(3)),
                    Integer.parseInt(infosMonstre.get(4)), Integer.parseInt(infosMonstre.get(5)), Integer.parseInt(infosMonstre.get(2)));
            // Savoir quels bonus sont distribués par le monstre
            where="idmonstre="+"\""+idMonstre+"\"";
            myBDD.requeteSelect("*", "monstredistribue", where);
            myBDD.printResultData();
            resultats = myBDD.getResult();
            for (List<String> ligne2 : resultats) {
                // insertion des bonus dans le monstre
                // TODO Insérer les bonus (getBonus(...))
            }
            tamponMonsters.add(monster);
        }

        // Récupératon des infos pour chaque porte présente dans le labyrinthe
        where="idlabyrinth="+"\""+idLabyrinth+"\"";
        myBDD.requeteSelect("*", "porte", where);
        myBDD.printResultData();
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
        myBDD.printResultData();
        resultats = myBDD.getResult();
        List<Hall> tamponHalls=new ArrayList<Hall>();
        for (List<String> ligne : resultats) {
            // Création des Halls
            Hall hall=new Hall(ligne.get(0), ligne.get(1), ligne.get(2));
            tamponHalls.add(hall);
        }

        for (List<String> ligne : resultats) {
            String idHall=ligne.get(0);
            where="idHall="+"\""+idHall+"\"";
            myBDD.requeteSelect("*", "hallestcompose", where);
            myBDD.printResultData();
            resultats = myBDD.getResult();
            for (List<String> ligne2 : resultats) {
                // TODO mettre portes dans hall
            }
            String idTypeHall=ligne.get(2);
            where="idtypehall="+"\""+idTypeHall+"\"";
            myBDD.requeteSelect("*", "monstreestdans", where);
            myBDD.printResultData();
            resultats = myBDD.getResult();
            for (List<String> ligne2 : resultats) {
                // TODO mettre monstres dans hall
            }
        }
    }

    public Bonus getBonus(String idBonus, List<Bonus> bonus){
        for (Bonus tampon : bonus ) {
            if(tampon.getIdBonus()==idBonus){
                return tampon;
            }
        }
        return null;
    };

    public Monster getMonster(String idMonster, List<Monster> monsters){
        for (Monster tampon : monsters ) {
            if(tampon.getIdMonster()==idMonster){
                return tampon;
            }
        }
        return null;
    };

    public Door getDoor(String idDoor, List<Door> doors){
        for (Door tampon : doors ) {
            if(tampon.getIdDoor()==idDoor){
                return tampon;
            }
        }
        return null;
    };

    public Session identification(String name) {return null;};
    public void save(ArrayList<Player> players, String labyrinth, String Hall) {};
    public LabyrinthSimple recordLabyrinth(Labyrinth server) {return labyrinth;};
    public ArrayList<String> recordMessagerie(String ip,int numPort) {return halls;};

    public static void main(String[] args) {
        OperationCenterSimple test=new OperationCenterSimple();
        test.initialisation("The Nebias labyrinth");
    }

    }
