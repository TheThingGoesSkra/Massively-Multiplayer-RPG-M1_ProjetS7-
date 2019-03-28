package Messaging;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class clientThread extends Thread {

    private String clientName;
    private DataInputStream is = null;
    private PrintStream os = null;
    private Socket clientSocket = null;
    private clientThread[] threads;
    private ArrayList<clientThread> hallThreads;
    private HashMap<String,ArrayList<clientThread>> relationsClHalls;
    private int maxClientsCount;


    public clientThread(Socket clientSocket, clientThread[] allthreads, HashMap<String,ArrayList<clientThread>> relationsClHalls) {
        this.clientSocket = clientSocket;
        this.threads=allthreads;
        this.hallThreads=new ArrayList<>();
        maxClientsCount = allthreads.length;
        this.clientName="null";
        this.relationsClHalls=relationsClHalls;
    }

    public clientThread(ArrayList<clientThread> threads, clientThread[] allthreads, String name) {
        this.threads=allthreads;
        this.hallThreads = threads;
        maxClientsCount = allthreads.length;
        this.clientName=name;
        this.relationsClHalls=new HashMap<String,ArrayList<clientThread>>();
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setThreads(clientThread[] threads){
        this.threads=threads;
    }

    public synchronized void setHallThreads(ArrayList<clientThread> hallThreads){
        this.hallThreads=hallThreads;
    }

    public ArrayList<clientThread> getHallThreads() {
        return hallThreads;
    }

    public clientThread[] getThreads() {
        return threads;
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

            synchronized (this){
                System.out.println("l'enfoiré :"+name);
                for(String hall : relationsClHalls.keySet()){
                    ArrayList<clientThread> joueurs = relationsClHalls.get(hall);
                    for (clientThread joueur : joueurs) {
                        String namej=joueur.getClientName();
                        System.out.println("l'enfoiré2 :"+namej);
                        if (namej.equals(name)) {
                            System.out.println("Theliste1 : "+joueurs);
                            int idx=joueurs.indexOf(joueur);
                            joueurs.remove(idx);
                            System.out.println("Theliste2 : "+joueurs);
                            this.clientName=name;
                            joueurs.add(this);
                            System.out.println("Theliste3 : "+joueurs);
                            hallThreads=joueurs;
                        }
                    }
                }
            }
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
                        for(clientThread thread : hallThreads){
                            thread.os.println("<font color=blue>" + name + "</font> : " + line);
                        }
                    }
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