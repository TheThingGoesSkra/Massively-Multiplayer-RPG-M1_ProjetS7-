package Labyrinth;
import java.rmi.Remote;
import java.rmi.RemoteException;
import Game.*;
import Client.*;
public interface Labyrinth extends Remote {
	
	public  Context login(Session s) throws RemoteException;
	public Context changeHall(String Hall, String player, Pole direction) throws RemoteException;
	public void newFight(Participant forward, Participant attacked) throws RemoteException;
	public void runnaway(Participant forward, Participant attacked) throws RemoteException;
	public void logOut(String player) throws RemoteException;

}
