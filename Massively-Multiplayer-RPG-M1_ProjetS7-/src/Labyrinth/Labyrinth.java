package Labyrinth;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import Game.*;
import Client.*;
public interface Labyrinth extends Remote {

	void login(Session s, Client proxy) throws RemoteException;
	void login(String idHall, Player player, Client proxy) throws RemoteException;
	int changeHall(String Hall, String player, Pole direction) throws RemoteException;
	void newFight(String idHall, String forward, String attacked) throws RemoteException;
	void runnaway(String Hall, String forward, String runner) throws RemoteException;
	void logout(String Hall, String player) throws RemoteException;
	void setReponsabiities(HashMap<Labyrinth,ArrayList<String>> resp) throws RemoteException;
	void chooseBonus(String idHall, String namePlayer, String bonus) throws RemoteException;
	void newGame(String idHall, String idPlayer) throws RemoteException;
	}
