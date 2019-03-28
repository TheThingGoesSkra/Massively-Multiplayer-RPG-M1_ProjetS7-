package Messaging;

import Game.Hall;
import Labyrinth.Labyrinth;
import OperationCenter.OperationCenter;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.ServerSocket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class ServerMsg {

  // Socket d'ecoute
  private static ServerSocket serverSocket;
  private int portNumber;
  //Creation des thread client ; Max client=10 pour test
  private int maxClientsCount = 10;
  private clientThread[] threads = new clientThread[maxClientsCount];
  private OperationCenter noc;
  private ChatLocalisation proxy;
  private ArrayList<String> halls;
  private HashMap<String,ArrayList<clientThread>> relationsClHalls;

  public ServerMsg(int portNumber){
    // Numero de port
    try {
      this.proxy = new ChatLocalisatonImpl(this);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    relationsClHalls=new HashMap<String,ArrayList<clientThread>>();
    this.portNumber = portNumber;
    try {
      this.serverSocket = new ServerSocket(portNumber);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  public static ServerSocket getServerSocket() {
    return serverSocket;
  }

  public static void setServerSocket(ServerSocket serverSocket) {
    ServerMsg.serverSocket = serverSocket;
  }

  public int getMaxClientsCount() {
    return maxClientsCount;
  }

  public void setMaxClientsCount(int maxClientsCount) {
    this.maxClientsCount = maxClientsCount;
  }

  public clientThread[] getThreads() {
    return threads;
  }

  public void setThreads(clientThread[] threads) {
    this.threads = threads;
  }

  public clientThread removePlayer(String name, String idHall){
    System.out.println("               ->"+" "+name+" "+idHall);
    // TODO
	  int indx = 0;
	  for(String hall : relationsClHalls.keySet()){
	    if(hall.equals(idHall)) {
          ArrayList<clientThread> joueurs = relationsClHalls.get(hall);
          if(!joueurs.isEmpty()) {
              System.out.println("liste1 : "+joueurs);
            for (clientThread joueur : joueurs) {
              String namej = joueur.getClientName();
              namej=namej.substring(1);
              if (namej.equals(name)) {
                indx = joueurs.indexOf(joueur);
                joueurs.remove(indx);
                System.out.println("liste2 : "+joueurs);
                return joueur;
              }
            }
          }else{
            System.out.println("JoueursHallIsEmpty");
          }
        }
      }
	  return null;
  }

  public void addPlayer(String idHall, String name){
    System.out.println("               ->"+" "+name+" "+idHall);
    for(String hall : relationsClHalls.keySet()) {
      if (hall.equals(idHall)) {
        ArrayList<clientThread> joueurs = relationsClHalls.get(hall);
        clientThread thread=new clientThread(joueurs,threads,name);
        joueurs.add(thread);
      }
    }
  }

  public void moovePlayer(String idHallout, String name, String idHallin){
    System.out.println("               ->"+idHallout+" "+name+" "+idHallin);
    clientThread thread=removePlayer(name,idHallout);
	  for(String hall : relationsClHalls.keySet()) {
        if (hall.equals(idHallin)) {
          ArrayList<clientThread> joueurs = relationsClHalls.get(hall);
          joueurs.add(thread);
          System.out.print(thread.getHallThreads());
          System.out.print(joueurs);
          thread.setHallThreads(joueurs);
          break;
        }
      }
  }

  public void nocConnection(){
    OperationCenter r = null;
    try {
      this.noc = (OperationCenter) Naming.lookup("rmi://localhost/ServerNocRMI");
    } catch (NotBoundException e) {
      e.printStackTrace();
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  public void recordServer() throws RemoteException {
    try {
      this.halls=noc.recordMessagerie("localhost",portNumber, proxy);
      for(String hall : halls){
        this.relationsClHalls.put(hall,new ArrayList<clientThread>());
      }
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  public void start(){
    nocConnection();
    try {
      recordServer();
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    while (true) {
      try {
        Socket clientSocket = serverSocket.accept();
        int i = 0;
        for (i = 0; i < getMaxClientsCount(); i++) {
          if (threads[i] == null) {
            threads[i] = new clientThread(clientSocket, threads, relationsClHalls);
            threads[i].start();
            break;
          }
        }
        if (i == getMaxClientsCount()) {
          PrintStream os = new PrintStream(clientSocket.getOutputStream());
          os.println("Serveur en surcharge !");
          os.close();
          clientSocket.close();
        }
      } catch (IOException e) {
        System.out.println(e);
      }
    }
  }

  public static void main(String args[]) {
    ServerMsg server = new ServerMsg(2222);
    server.start();;
  }

}
