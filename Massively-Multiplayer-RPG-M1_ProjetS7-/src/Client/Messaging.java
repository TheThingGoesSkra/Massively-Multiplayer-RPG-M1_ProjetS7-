package Client;

import GUI.MessagerieGUI;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Messaging extends Thread {

    // Socket de service
    private Socket clientSocket;
    // canal de sortie
    private PrintStream os;
    // canal d'entrée
    private DataInputStream is;
    private boolean closed = false;
    int portNumber;
    private String host;
    private String name;
    private MessagerieGUI gui;

    public Messaging(MessagerieGUI gui, int portNumber, String host, String name){
        this.gui=gui;
        // Num de port.
        this.portNumber = portNumber;
        this.host = host;
        this.name=name;
        connection();
    }

    public void connection(){
        /*ouverture socket et cannaux*/
        try {
            clientSocket = new Socket(host, portNumber);
            os = new PrintStream(clientSocket.getOutputStream());
            is = new DataInputStream(clientSocket.getInputStream());
            os.println(name);
        } catch (UnknownHostException e) {
            System.err.println("Erreur host " + host);
        } catch (IOException e) {
            System.err.println("Erreur de connection "
                    + host);
        }
    }

    public void sendMessage(String message){
                os.println(message);
    }

    public void run() {
        String responseLine;
        try {
            while (true) {
                responseLine = is.readLine();
                if(responseLine != null) {
                    System.out.println(responseLine);
                    gui.append(responseLine);
                }else if (responseLine.indexOf("** Au revoir ") != -1)
                    break;
            }
            closed = true;
            os.close();
            is.close();
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
        return;
    }
}