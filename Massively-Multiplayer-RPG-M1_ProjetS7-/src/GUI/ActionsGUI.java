package GUI;

import Client.ClientSimple;
import Client.GestionBDDI;
import Client.Session;
import Game.Bonus;
import Game.Player;
import Game.Pole;
import OperationCenter.GestionBDD;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;


public class ActionsGUI extends javax.swing.JPanel {

    private ClientSimple client;
    private MapGUI map;
    private InventoryGUI inventory;
    // Variables declaration - do not modify
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane jTextPane3;
    // End of variables declaration

    public ActionsGUI(ClientSimple client) {
        this.client=client;
        Session session=this.client.getSession();
        String idHall = session.getIdHall();
        String where="idHall="+"\""+idHall+"\"";
        GestionBDDI myBDD=client.getMyBDD();
        myBDD.requeteSelect("*", "Hall", where);
        List<String> res = myBDD.getResult().get(0);
        int x=Integer.parseInt(res.get(4));
        int y=Integer.parseInt(res.get(5));
        this.map = new MapGUI(x, y);
        Player player = session.getPlayer();
        ArrayList<Bonus> listeBonus = player.getListeBonus();
        this.inventory=new InventoryGUI(listeBonus,client);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane3 = new javax.swing.JTextPane();

        setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));
        jPanel6.setForeground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(32, 32));
        jPanel6.setLayout(new java.awt.GridLayout());

        jButton12.setBackground(new java.awt.Color(204, 204, 204));
        jButton12.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton12.setText("Help");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton12);

        jButton13.setBackground(new java.awt.Color(204, 204, 204));
        jButton13.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton13.setText("Exit");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton13);

        jButton14.setBackground(new java.awt.Color(204, 204, 204));
        jButton14.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton14.setText("Restart");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jPanel6.add(jButton14);

        jButton5.setBackground(new java.awt.Color(204, 204, 204));
        jButton5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton5.setText("Change Labyrinth");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton5);

        jButton15.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton15.setText("Inventory");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton15);

        jButton16.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton16.setText("Map");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton16);

        jPanel5.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setPreferredSize(new java.awt.Dimension(35, 35));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jScrollPane3.setBackground(new java.awt.Color(102, 102, 102));

        jTextPane3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextPane3.addKeyListener(new KeyListener() {
            private boolean shift=false;
            private int cptLigneJTexteArea1=0;
            private int lineCount=2;
            @Override
            public void keyPressed(KeyEvent arg0) {
                if(arg0.getKeyCode()==KeyEvent.VK_SHIFT){
                    shift=true;
                }
                if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
                    if(shift){
                        jTextPane3.setText(jTextPane3.getText()+"\n");
                    }else{
                        arg0.consume();
                        String texte=jTextPane3.getText();
                        String[] commande=texte.split(" ");
                        switch (commande[0]){
                            case "n":
                                client.changeHall(Pole.NORTH);
                                break;
                            case "s":
                                client.changeHall(Pole.SOUTH);
                                break;
                            case "e":
                                client.changeHall(Pole.EAST);
                                break;
                            case "o":
                                client.changeHall(Pole.WEST);
                                break;
                            case "a":
                                if(commande.length>=2){
                                    String attacked=texte.substring(2);
                                    client.newFight(attacked);
                                }
                                break;
                            case "r":
                                if(commande.length>=2) {
                                    String forward = texte.substring(2);
                                    client.runnaway(forward);
                                }
                            case "i":
                                if(commande.length>2) {
                                    switch(commande[1]) {
                                        case "b":
                                            switch(commande[2]) {
                                                case "d":
                                                    client.showBonusDonjon();
                                                    break;
                                                default:
                                                    String bonusName = texte.substring(4);
                                                    client.showInfosBonus(bonusName);
                                                    break;
                                            }
                                        case "p":
                                            switch(commande[2]) {
                                                case "h":
                                                    // TODO
                                                    //client.showPlayersHall();
                                                    break;
                                                case "d":
                                                    // TODO
                                                    //client.showPlayersDonjon();
                                                    break;
                                                default :
                                                    // TODO
                                                    break;
                                            }
                                            break;
                                        case "m":
                                            switch(commande[2]) {
                                                case "h":
                                                    client.showMonstersHall();
                                                    break;
                                                case "d":
                                                    client.showMonstersDonjon();
                                                    break;
                                                default :
                                                    String MonsterName = texte.substring(4);
                                                    client.showInfosMonster(MonsterName);
                                                    break;
                                            }
                                            break;
                                    }
                                }
                                if(commande.length==2) {
                                    switch(commande[1]){
                                            case "d":
                                                client.showMonstersDonjon();
                                                client.showBonusDonjon();
                                                break;
                                            case "p":
                                                Session session=client.getSession();
                                                Player player=session.getPlayer();
                                                client.showInfosParticipant(player);
                                                break;
                                    }
                                }
                                break;
                            default :
                                client.sendMessage(texte);
                        }
                        jTextPane3.setText("");
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent arg0) {
                if(arg0.getKeyCode()==KeyEvent.VK_SHIFT){
                    shift=false;
                }
            }

            @Override
            public void keyTyped(KeyEvent arg0) {

            }

        });

        jScrollPane3.setViewportView(jTextPane3);

        jPanel2.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel1, java.awt.BorderLayout.SOUTH);

        add(jPanel5, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>

    private void jButton16ActionPerformed(ActionEvent evt) {
        this.map.setVisible(true);
    }

    private void jButton12ActionPerformed(ActionEvent evt) {
    }

    private void jButton5ActionPerformed(ActionEvent evt) {
        LabyrinthChoiceGUI choice=new LabyrinthChoiceGUI();
        choice.setVisible(true);
    }

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {
        inventory.setVisible(true);
    }

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {
        client.newGame();
    }

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {
        client.getGui().getActionsGUI1().getInventory().dispose();
        client.getGui().getActionsGUI1().getMap().dispose();
        client.getGui().dispose();
        client.labyrinthLogout();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginGUI newGui=new LoginGUI(client);
                newGui.setVisible(true);            }
        });
    }

    public void changeHall(int x,int y, Pole direction){
        MapGUI.MapJPanel panel=map.getjPanel1();
        panel.setx(x);
        panel.sety(y);
        if(direction==Pole.WEST){
            panel.setDirection(direction);

        }if(direction==Pole.EAST){
            panel.setDirection(direction);
        }
        panel.repaint();

    }

    public MapGUI getMap() {
        return map;
    }

    public void setMap(MapGUI map) {
        this.map = map;
    }

    public InventoryGUI getInventory() {
        return inventory;
    }

    public void setInventory(InventoryGUI inventory) {
        this.inventory = inventory;
    }
}
