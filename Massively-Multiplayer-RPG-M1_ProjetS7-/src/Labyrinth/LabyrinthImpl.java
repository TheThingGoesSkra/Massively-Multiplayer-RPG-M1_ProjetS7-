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


	protected LabyrinthImpl() throws RemoteException {
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

	public void login(Session s, Client proxy) throws RemoteException{
		 Player player;
		 String idhall;
		 idhall= s.getHall();
		 player = s.getPlayer();
		player.setProxy(proxy);
		labyrinth.addPlayer(idhall,player);
	}
	public void setProxy(Client proxy){

	}
	public void changeHall(String Hall, String player, Pole direction) throws RemoteException{};
	public void newFight(String forward, String attacked) throws RemoteException{};
	public void runnaway(String Hall, String forward, String runner) throws RemoteException{};
	public void logOut(String Hall, String player) throws RemoteException{};
	public void setReponsabiities(HashMap<Labyrinth,ArrayList<String>> resp) throws RemoteException{
		System.out.println("setresponsabilities");
		labyrinth.setReponsabiities(resp);
	}

	public void recordServer() throws RemoteException {
		try {
			this.labyrinth=noc.recordLabyrinth(this);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws RemoteException {
		LabyrinthImpl impl;
		try {
			impl=new LabyrinthImpl();
			OperationCenter r = (OperationCenter)Naming.lookup("rmi://localhost/ServerNocRMI");
			impl.setNoc((OperationCenter)r);
			LabyrinthSimple labyrinth=r.recordLabyrinth(impl);
			impl.setLabyrinth(labyrinth);
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

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}


	}
