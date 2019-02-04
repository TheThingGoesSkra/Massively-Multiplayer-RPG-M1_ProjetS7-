package Labyrinth;

import Game.*;

import static java.lang.Thread.sleep;

import java.rmi.RemoteException;

import Client.Session;

public class LabyrinthImpl implements Labyrinth{
	
	public  Context login(Session s) throws RemoteException {
		return null;
	}
	public Context changeHall(String Hall, String player, Pole direction) throws RemoteException {
		return null;
	}
	public void newFight(Participant forward, Participant attacked) throws RemoteException {
	}
	public void runnaway(Participant forward, Participant attacked) throws RemoteException {
	}
	public void logOut(String player) throws RemoteException {
	}


}
