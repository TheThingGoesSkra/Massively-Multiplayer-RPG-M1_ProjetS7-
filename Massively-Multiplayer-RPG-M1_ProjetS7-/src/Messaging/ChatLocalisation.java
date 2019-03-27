package Messaging;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatLocalisation extends Remote {

    void addPlayer(String idHall, String name) throws RemoteException;
    void removePlayer(String idHall, String name) throws RemoteException;
    void moovePlayer(String idHall, String name, String idHall2) throws RemoteException;

}
