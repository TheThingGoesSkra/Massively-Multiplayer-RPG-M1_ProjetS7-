package Labyrinth;

import Client.Client;
import Game.*;

import static java.lang.Thread.sleep;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import Client.Session;
import OperationCenter.OperationCenter;

public class LabyrinthImpl extends UnicastRemoteObject implements Labyrinth, Serializable{

	private LabyrinthSimple labyrinth;
    private OperationCenter noc;


	public LabyrinthImpl() throws RemoteException {
	}

	public LabyrinthSimple getLabyrinth() {
		return labyrinth;
	}

	public void setLabyrinth(LabyrinthSimple labyrinth) {
		this.labyrinth = labyrinth;
	}

	public OperationCenter getNoc() {
		return noc;
	}

	public void setNoc(OperationCenter noc) {
		this.noc = noc;
	}

	public void login(Session session, Client proxy) throws RemoteException{
		 Player player;
		 String idHall;
		 idHall= session.getIdHall();
		 player = session.getPlayer();
		 player.setProxy(proxy);
		 labyrinth.addPlayer(idHall,player);
	}

	public void newFight(String idHall, String forward, String attacked) throws RemoteException{
		labyrinth.newFight(idHall, forward,attacked);
	};
	public void runnaway(String idHall, String forward, String runner) throws RemoteException{
		labyrinth.runnaway(idHall,forward,runner);
	};

	public int changeHall(String idHall, String player, Pole direction) throws RemoteException{
		int idHalls = labyrinth.changeHall(idHall, player, direction);
		return idHalls;
	};

	public void chooseBonus(String idHall, String namePlayer, String bonus) throws RemoteException {
		labyrinth.chooseBonus(idHall,namePlayer,bonus);
	}

	public void logout(String idHall, String player) throws RemoteException{};

	public void setReponsabiities(HashMap<Labyrinth,ArrayList<String>> resp) throws RemoteException{
		System.out.println("setresponsabilities");
		labyrinth.setReponsabiities(resp);
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

	public void recordServer() throws RemoteException {
		try {
			this.labyrinth=noc.recordLabyrinth(this);
			this.labyrinth.setNoc(this.noc);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws RemoteException {
		LabyrinthImpl impl=new LabyrinthImpl();
		impl.nocConnection();
		impl.recordServer();

		LabyrinthSimple labyrinth=impl.getLabyrinth();
		// Test
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir start lorsque le NOC aura terminer la distribution des responsabilités :");
		String str = sc.nextLine();
		if(str.equals("start")){
			HashSet<Hall> halls2=labyrinth.getHalls();
			for (Hall hall2: halls2) {
				System.out.println("Hall : {" + hall2.getIdHall() + ", " + hall2.getName() + ", " + hall2.getIdType()+", "+hall2.getProxy().toString()+"}");
				HashMap<Pole, Door> doors = hall2.getDoors();
				for (Pole direction : doors.keySet()) {
					Door door = doors.get(direction);
					Hall h1 = door.getHall1();
					String id1;
					if (h1 != null) {
						id1 = h1.getIdHall();
					} else {
						id1 = "null";
					}
					Hall h2 = door.getHall2();
					String id2;
					if (h2 != null) {
						id2 = h2.getIdHall();
					} else {
						id2 = "null";
					}
					System.out.println("Porte : {" + direction + ", " + door.getIdDoor() + ", " + id1 + ", " + id2 + "}");
				}
				Context context = hall2.getContext();
				ArrayList<Monster> monsters = context.getMonsters();
				for (Monster monster : monsters) {
					System.out.println("Monster : {" + monster.getIdMonster() + ", " + monster.getName() + "}");
				}
			}
		}

	}


	}
