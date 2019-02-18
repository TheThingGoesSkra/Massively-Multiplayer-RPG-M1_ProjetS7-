package Labyrinth;

import Client.Client;
import Game.*;

import static java.lang.Thread.sleep;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

import Client.Session;

public class LabyrinthImpl extends UnicastRemoteObject implements Labyrinth{

	protected LabyrinthImpl() throws RemoteException {
	}

	public void login(Session s, Client proxy) throws RemoteException{};
	public void changeHall(String Hall, String player, Pole direction) throws RemoteException{};
	public void newFight(String forward, String attacked) throws RemoteException{};
	public void runnaway(String Hall, String forward, String runner) throws RemoteException{};
	public void logOut(String Hall, String player) throws RemoteException{};
	public void setReponsabiities(HashMap<Labyrinth,ArrayList<String>> resp) throws RemoteException{};


}
