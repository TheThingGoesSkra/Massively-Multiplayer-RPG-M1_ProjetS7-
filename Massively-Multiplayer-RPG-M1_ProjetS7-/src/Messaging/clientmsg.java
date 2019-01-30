import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class client implements Runnable {

  // Socket de service 
  private static Socket clientSocket = null;
  // canal de sortie 
  private static PrintStream os = null;
  // canal d'entrée 
  private static DataInputStream is = null;

  private static BufferedReader inputLine = null;
  private static boolean closed = false;
  
  public static void main(String[] args) {

    // Num de port.
    int portNumber = 2222;
    String host = "localhost";

    if (args.length < 2) {
      System.out
          .println("Usage: java MultiThreadChatClient <host> <portNumber>\n"
              + "Now using host=" + host + ", portNumber=" + portNumber);
    } else {
      host = args[0];
      portNumber = Integer.valueOf(args[1]).intValue();
    }

    /*
     * ouverture socket et cannaux 
     */
    try {
      clientSocket = new Socket(host, portNumber);
      inputLine = new BufferedReader(new InputStreamReader(System.in));
      os = new PrintStream(clientSocket.getOutputStream());
      is = new DataInputStream(clientSocket.getInputStream());
    } catch (UnknownHostException e) {
      System.err.println("Erreur host " + host);
    } catch (IOException e) {
      System.err.println("Erreur de connection "
          + host);
    }

	/////////////////////////////////////////////////////////////////////////////
    if (clientSocket != null && os != null && is != null) {
      try {

        /* Thread de lecture sur le serveur. */
        new Thread(new client()).start();
        while (!closed) {
          os.println(inputLine.readLine().trim());
        }
        /*
         * Fermeture de la socket et des cannaux 
         */
        os.close();
        is.close();
        clientSocket.close();
      } catch (IOException e) {
        System.err.println("IOException:  " + e);
      }
    }
  }

  
  public void run() {
    /*
     * Keep on reading from the socket till we receive "Bye" from the
     * server. Once we received that then we want to break.
     */
    String responseLine;
    try {
      while ((responseLine = is.readLine()) != null) {
        System.out.println(responseLine);
        if (responseLine.indexOf("** Au revoir ") != -1)
          break;
      }
      closed = true;
    } catch (IOException e) {
      System.err.println("IOException:  " + e);
    }
  }
}