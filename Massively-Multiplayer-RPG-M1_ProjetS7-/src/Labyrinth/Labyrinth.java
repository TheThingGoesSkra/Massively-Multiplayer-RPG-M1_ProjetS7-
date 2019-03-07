package Labyrinth;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import Game.*;
import Client.*;
public interface Labyrinth extends Remote {

	public void login(Session s, Client proxy) throws RemoteException;
	public int changeHall(String Hall, String player, Pole direction) throws RemoteException;
	public void newFight(String idHall, String forward, String attacked) throws RemoteException;
	public void runnaway(String Hall, String forward, String runner) throws RemoteException;
	public void logout(String Hall, String player) throws RemoteException;
	public void setReponsabiities(HashMap<Labyrinth,ArrayList<String>> resp) throws RemoteException;

}
