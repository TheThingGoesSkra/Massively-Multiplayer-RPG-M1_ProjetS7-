package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Game.Context;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
    // End of variables declaration

    /**
     * Creates new form InformationsGUI
     */
    public InformationsGUI(Context context,String myplayer) {
        initComponents(context,myplayer);
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
    private void initComponents(Context context, String myplayer) {

        jPanel2 = new JPanel();
        jSplitPane1 = new JSplitPane();
        labyrinthGUI1 = new LabyrinthGUI(context,myplayer);
        messagerieGUI1 = new MessagerieGUI();

        setLayout(new BorderLayout());

        labyrinthGUI1.setBackground(new Color(102, 102, 102));
        jPanel2.setBackground(new Color(102, 102, 102));
        jPanel2.setPreferredSize(new Dimension(800, 300));
        jPanel2.setLayout(new BorderLayout());

        jSplitPane1.setBackground(new Color(102, 102, 102));
        jSplitPane1.setBorder(BorderFactory.createEtchedBorder());
        jSplitPane1.setRightComponent(labyrinthGUI1);
        jSplitPane1.setLeftComponent(messagerieGUI1);
        jSplitPane1.setDividerLocation(300);
        jSplitPane1.setResizeWeight(0.5);
        jSplitPane1.setContinuousLayout(true);

        jPanel2.add(jSplitPane1, BorderLayout.CENTER);

        add(jPanel2, BorderLayout.CENTER);

        messagerieGUI1.getjScrollPane1().setPreferredSize(new Dimension(15,0));
        messagerieGUI1.getjScrollPane6().setPreferredSize(new Dimension(240,0));
        messagerieGUI1.getjTextPane1().setPreferredSize(new Dimension(240,0));

        //labyrinthGUI1.getjScrollPane7().setMinimumSize(new Dimension(1000,0));
        labyrinthGUI1.getjScrollPane7().setPreferredSize(new Dimension(280,0));
        ((JTextPane) labyrinthGUI1.getjTextPane1()).setPreferredSize(new Dimension(280, 0));
        labyrinthGUI1.getjPanel5().setPreferredSize(new Dimension(280, 0));
        labyrinthGUI1.getjPanel6().setPreferredSize(new Dimension(280, 0));



        //labyrinthGUI1.getjPanel5().setPreferredSize(new Dimension(800, 0));
        //labyrinthGUI1.getjScrollPane7().setPreferredSize(new Dimension(800,0));
        //labyrinthGUI1.getjTextPane1().setPreferredSize(new Dimension(800, 0));

    }// </editor-fold>

}

