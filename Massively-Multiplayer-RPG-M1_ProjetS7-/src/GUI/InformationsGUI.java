package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Game.Context;

/**
 *
 * @author Thomas
 */
public class InformationsGUI extends javax.swing.JPanel {

    // Variables declaration - do not modify
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSplitPane jSplitPane1;
    private LabyrinthGUI labyrinthGUI1;
    private MessagerieGUI messagerieGUI1;
    private Context context;
    // End of variables declaration

    /**
     * Creates new form InformationsGUI
     */
    public InformationsGUI(Context context) {
        this.context=context;
        initComponents();
    }

    public LabyrinthGUI getLabyrinthGUI1() {
        return labyrinthGUI1;
    }

    public void setLabyrinthGUI1(LabyrinthGUI labyrinthGUI1) {
        this.labyrinthGUI1 = labyrinthGUI1;
    }

    public MessagerieGUI getMessagerieGUI1() {
        return messagerieGUI1;
    }

    public void setMessagerieGUI1(MessagerieGUI messagerieGUI1) {
        this.messagerieGUI1 = messagerieGUI1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        labyrinthGUI1 = new LabyrinthGUI(this.context);
        messagerieGUI1 = new MessagerieGUI();

        setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setPreferredSize(new java.awt.Dimension(800, 300));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jSplitPane1.setBackground(new java.awt.Color(102, 102, 102));
        jSplitPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSplitPane1.setRightComponent(labyrinthGUI1);
        jSplitPane1.setLeftComponent(messagerieGUI1);

        jPanel2.add(jSplitPane1, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>

}

