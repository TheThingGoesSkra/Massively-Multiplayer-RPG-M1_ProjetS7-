package GUI;

import Client.ClientSimple;
import Client.Session;
import Game.Bonus;
import Labyrinth.Labyrinth;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class InventoryGUI extends javax.swing.JFrame {

    private javax.swing.JButton jButton1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;

    private ArrayList<Bonus> listBonus;
    private ClientSimple client;


    public InventoryGUI(ArrayList<Bonus> listBonus, ClientSimple client) {
        this.listBonus=listBonus;
        this.client=client;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();

        jPanel26.setBackground(new java.awt.Color(102, 102, 102));
        jPanel26.setPreferredSize(new java.awt.Dimension(100, 400));
        jPanel26.setLayout(new java.awt.BorderLayout());

        jPanel27.setBackground(new java.awt.Color(102, 102, 102));
        jPanel27.setPreferredSize(new java.awt.Dimension(20, 20));

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
                jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 384, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
                jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel26.add(jPanel27, java.awt.BorderLayout.PAGE_START);

        jPanel21.setBackground(new java.awt.Color(102, 102, 102));
        jPanel21.setMinimumSize(new java.awt.Dimension(20, 100));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
                jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
                jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 351, Short.MAX_VALUE)
        );

        jPanel26.add(jPanel21, java.awt.BorderLayout.LINE_START);

        jPanel28.setBackground(new java.awt.Color(102, 102, 102));
        jPanel28.setPreferredSize(new java.awt.Dimension(20, 408));

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
                jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
                jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 351, Short.MAX_VALUE)
        );

        jPanel26.add(jPanel28, java.awt.BorderLayout.LINE_END);

        jPanel29.setBackground(new java.awt.Color(102, 102, 102));
        jPanel29.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("Utiliser");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 143, 16, 148);
        jPanel29.add(jButton1, gridBagConstraints);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel26.add(jPanel29, java.awt.BorderLayout.PAGE_END);

        DefaultListModel model=new DefaultListModel();
        for(Bonus bonus : listBonus){
            String name=bonus.getName();
            model.addElement(name);
        }

        jList2.setModel(model);
        jScrollPane7.setViewportView(jList2);

        jPanel26.add(jScrollPane7, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel26, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>

    private void jButton1ActionPerformed(ActionEvent evt) {
        String bonus = jList2.getSelectedValue();
        client.chooseBonus(bonus);
    }

    public void removeBonus(String name){
        DefaultListModel model= (DefaultListModel) jList2.getModel() ;
        int index=model.indexOf(name);
        model.remove(index);
        jList2.updateUI();
    }

    public void addBonus(String name){
        DefaultListModel model= (DefaultListModel) jList2.getModel() ;
        model.addElement(name);
        jList2.updateUI();
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
            java.util.logging.Logger.getLogger(LabyrinthChoiceGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LabyrinthChoiceGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LabyrinthChoiceGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LabyrinthChoiceGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LabyrinthChoiceGUI().setVisible(true);
            }
        });
    }

}
