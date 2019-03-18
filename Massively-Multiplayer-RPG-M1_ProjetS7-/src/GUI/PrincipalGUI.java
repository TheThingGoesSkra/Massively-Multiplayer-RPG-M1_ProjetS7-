package GUI;

import Client.ClientSimple;
import Client.Session;
import Game.Context;
import Game.Monster;
import Game.Player;
import Game.Pole;
import Labyrinth.Labyrinth;
import Labyrinth.LabyrinthImpl;

import java.rmi.RemoteException;

/**
 *
 * @author Thomas
 */
public class PrincipalGUI extends javax.swing.JFrame {


    // Variables declaration - do not modify
    private ActionsGUI actionsGUI1;
    private InformationsGUI informationsGUI1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration

    /**
     * Creates new form NewJFrame1
     */
    public PrincipalGUI(ClientSimple client) {
        initComponents(client);
    }

    public ActionsGUI getActionsGUI1() {
        return actionsGUI1;
    }

    public void setActionsGUI1(ActionsGUI actionsGUI1) {
        this.actionsGUI1 = actionsGUI1;
    }

    public InformationsGUI getInformationsGUI1() {
        return informationsGUI1;
    }

    public void setInformationsGUI1(InformationsGUI informationsGUI1) {
        this.informationsGUI1 = informationsGUI1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents(ClientSimple client) {

        jPanel1 = new javax.swing.JPanel();
        Context context=client.getContext();
        Session session=client.getSession();
        Player player=session.getPlayer();
        String myplayer=player.getName();
        informationsGUI1 = new InformationsGUI(context,myplayer);
        actionsGUI1 = new ActionsGUI(client);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(informationsGUI1, java.awt.BorderLayout.CENTER);
        jPanel1.add(actionsGUI1, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
        this.setIconImage(new javax.swing.ImageIcon(getClass().getResource("/GUI/Icones/Start.png")).getImage());
        pack();
    }// </editor-fold>

    public void changeHall(int x, int y, Pole direction){
        actionsGUI1.changeHall(x, y, direction);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrincipalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        try {
            Context context=new Context();
            Labyrinth labyrinth = new LabyrinthImpl();
            context.addPlayer(new Player("Antonio", 10,1,1,1,10));
            context.addMonster(new Monster("0", "Chupacabra", 10,1,1,1,10));
            ClientSimple clientSimple=new ClientSimple();
            clientSimple.setContextSimple(context);
            new PrincipalGUI(clientSimple).setVisible(true);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}