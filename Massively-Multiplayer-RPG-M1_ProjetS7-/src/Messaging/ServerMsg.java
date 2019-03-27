package Messaging;

import Game.Hall;
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


public class ServerMsg {

  // Socket d'ecoute
  private static ServerSocket serverSocket;
  private int portNumber;
  //Creation des thread client ; Max client=10 pour test
  private static int maxClientsCount = 10;
  private static clientThread[] threads = new clientThread[maxClientsCount];
  private OperationCenter noc;
  private ChatLocalisation proxy;
  private ArrayList<String> halls;
  private HashMap<String,clientThread[]> relationsClHalls;

  public ServerMsg(int portNumber){
    // Numero de port
    try {
      this.proxy = new ChatLocalisatonImpl(this);
    } catch (RemoteException e) {
      e.printStackTrace();
    }

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

  public void removePlayer(String idHall, String name){
    // TODO
  }

  public void addPlayer(String idHall, String name){
    // TODO
  }

  public void moovePlayer(String idHall, String name, String idHall2){
    // TODO
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
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  public class clientThread extends Thread {

    private String clientName = null;
    private DataInputStream is = null;
    private PrintStream os = null;
    private Socket clientSocket = null;
    private clientThread[] allThreads;
    private clientThread[] threads;
    private int maxClientsCount;


    public clientThread(Socket clientSocket, clientThread[] threads) {
      this.clientSocket = clientSocket;
      this.threads = threads;
      maxClientsCount = threads.length;
    }

    public void setThreads(clientThread[] threads){
      this.threads=threads;
    }



    public void run() {
      try {
        /*
         * Creation de canal.
         */
        is = new DataInputStream(clientSocket.getInputStream());
        os = new PrintStream(clientSocket.getOutputStream());
        String name;
        while (true) {
          name = is.readLine().trim();
          if (name.indexOf('@') == -1) {
            break;
          } else {
            os.println("Le pseudo ne doit pas contenir le caractére '@'.");
          }
        }

        /* Acceuillir nouveau joueur. */
        os.println("Bienvenue "+name+" !! \nUtilisez :  \n /quit pour quitter.<br>@pseudo pour un message prive.");

        synchronized (this) {
          for (int i = 0; i < maxClientsCount; i++) {
            if (threads[i] != null && threads[i] == this) {
              clientName = "@" + name;
              break;
            }
          }
          for (int i = 0; i < maxClientsCount; i++) {
            if (threads[i] != null && threads[i] != this) {
              threads[i].os.println(name + " rejoint la session.");
            }
          }
        }
        /* communication. */
        while (true) {
          String line = is.readLine();
          if (line.startsWith("/quit")) {
            break;
          }
          /* Gestion message privé entre joueurs  */
          if (line.startsWith("@")) {
            String[] words = line.split("\\s", 2);
            if (words.length > 1 && words[1] != null) {
              words[1] = words[1].trim();
              if (!words[1].isEmpty()) {
                synchronized (this) {
                  for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] != null && threads[i] != this
                            && threads[i].clientName != null
                            && threads[i].clientName.equals(words[0])) {
                      String message = "<font color=teal>"+name + "</font> : " + words[1];
                      threads[i].os.println(message);
                      this.os.println(message);
                      break;
                    }
                  }
                }
              }
            }
          } else {
//TODO A MODIFIER///////////////////////////////////////

        	  /* Messages publiques, broadcast. */
            synchronized (this) {
              System.out.println(line);
              for (int i = 0; i < maxClientsCount; i++) {
                if (threads[i] != null && threads[i].clientName != null) {
                  threads[i].os.println("<font color=blue>" + name + "</font> : " + line);
                }
              }
            }
///////////////////////////////////////////////////////
          }
        }

        synchronized (this) {
          for (int i = 0; i < maxClientsCount; i++) {
            if (threads[i] != null && threads[i] != this
                    && threads[i].clientName != null) {
              threads[i].os.println(name
                      + " quitte la session.");
            }
          }
        }
        os.println("Au revoir " + name +" !");

      /* preparation du thread pour un nouveau joueur  */
        synchronized (this) {
          for (int i = 0; i < maxClientsCount; i++) {
            if (threads[i] == this) {
              threads[i] = null;
            }
          }
        }
        /*
         * fermeture des cannaux et de la socket de service
         */
        is.close();
        os.close();
        clientSocket.close();
      } catch (IOException e) {
      }
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
            (threads[i] = new clientThread(clientSocket, threads)).start();
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
