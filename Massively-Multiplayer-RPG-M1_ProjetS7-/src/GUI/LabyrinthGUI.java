package GUI;

import Game.Context;
import Game.Monster;
import Game.Participant;
import Game.Player;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 *
 * @author Thomas
 */
public class LabyrinthGUI extends javax.swing.JPanel {

    // Variables declaration - do not modify
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTree jTree1;
    private Context context;
    // End of variables declaration
    public class MyTreeCellRenderer extends DefaultTreeCellRenderer {

        private JLabel label;

        MyTreeCellRenderer() {
            label = new JLabel();
        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                      boolean sel, boolean exp, boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, sel, exp, leaf, row, hasFocus);

            // Assuming you have a tree of Strings
            String node = (String) ((DefaultMutableTreeNode) value).getUserObject();
            if (node.equals("root")) {

            }
            else if (node.equals("Monsters")) {
                label.setIcon(new ImageIcon(getClass().getResource("/GUI/Icones/Monsters.png")));
                label.setText("" + node);
            }else if(node.equals("Players")){
                label.setIcon(new ImageIcon(getClass().getResource("/GUI/Icones/Players.png")));
                label.setText("" + node);
            }else{
                // Paint the node in red
                setForeground(new Color(223, 24 ,24));
                label.setIcon(new ImageIcon(getClass().getResource("/GUI/Icones/life.png")));
                String[] resultats = node.split(" ");
                label.setText("<html><font color=red>"+resultats[0]+"</font> <font color=black>"+resultats[1]+"</font></html>");
            }
            label.setFont(new java.awt.Font("Tahoma", 0, 16));
            return label;
        }
    }

    /**
     * Creates new form LabyrinthGUI
     */
    public LabyrinthGUI(Context context) {
        this.context=context;
        initComponents();
        initJTree();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();

        setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jScrollPane3.setBackground(new java.awt.Color(102, 102, 102));

        jTree1.setBackground(new java.awt.Color(102, 102, 102));
        jTree1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTree1.setForeground(new java.awt.Color(204, 204, 255));
        jTree1.setMaximumSize(new java.awt.Dimension(107, 84));
        jTree1.setPreferredSize(new java.awt.Dimension(150, 64));
        DefaultMutableTreeNode treeNode1 = new DefaultMutableTreeNode("root");
        jTree1.setModel(new DefaultTreeModel(treeNode1));
        jTree1.setRootVisible(false);
        jTree1.setToggleClickCount(1);
        jTree1.setCellRenderer(new MyTreeCellRenderer());
        BasicTreeUI basicTreeUI = (BasicTreeUI) jTree1.getUI();
        basicTreeUI.setRightChildIndent(2);
        basicTreeUI.setLeftChildIndent(4);
        jScrollPane3.setViewportView(jTree1);

        jPanel5.add(jScrollPane3, java.awt.BorderLayout.LINE_START);

        jScrollPane6.setBackground(new java.awt.Color(102, 102, 102));
        jScrollPane6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextArea4.setEditable(false);
        jTextArea4.setBackground(new java.awt.Color(102, 102, 102));
        jTextArea4.setColumns(20);
        jTextArea4.setFont(new java.awt.Font("Monospaced", 0, 16)); // NOI18N
        jTextArea4.setForeground(new java.awt.Color(204, 204, 255));
        jTextArea4.setRows(5);
        jTextArea4.setRequestFocusEnabled(false);
        jScrollPane6.setViewportView(jTextArea4);
        jPanel5.add(jScrollPane6, java.awt.BorderLayout.CENTER);

        add(jPanel5, java.awt.BorderLayout.CENTER);
    }// </editor-fold>

    public void initJTree(){
        ArrayList<Player> players = context.getPlayers();
        ArrayList<Monster> monsters = context.getMonsters();
        for(Player player:players) {
            this.ajouterPlayer(player,0);
        }
        for(Monster monster:monsters) {
            this.ajouterMonster(monster,0);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                expandAll(jTree1);
            }
        });
    }

    private void expandAll(JTree tree) {
        for (int i = 0; i < tree.getRowCount(); i++) {
            tree.expandRow(i);
        }
    }

    public DefaultMutableTreeNode ajouterCategorie(String nomCategorie){
        DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(nomCategorie);
        DefaultTreeModel model = (DefaultTreeModel)this.jTree1.getModel();
        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode)model.getRoot();
        rootNode.add(treeNode);
        model.reload();
        return treeNode;
    }

    public DefaultMutableTreeNode ajouterParticipant(Participant participant, int mode){
        DefaultMutableTreeNode treeNode = null;
        DefaultTreeModel model = (DefaultTreeModel)this.jTree1.getModel();
        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode)model.getRoot();
        DefaultMutableTreeNode groupNode=null;
        if(participant instanceof Player){
            //Recherche du noeud
            groupNode = searchNode(rootNode,"Players");
        }else if(participant instanceof Monster){
            //Recherche du noeud
            groupNode = searchNode(rootNode,"Monsters");
        }

        //Si on ne le trouve pas on creer le groupe
        if(groupNode == null)
            if(participant instanceof Player){
                //Recherche du noeud
                groupNode = this.ajouterCategorie("Players");
            }else if(participant instanceof Monster){
                //Recherche du noeud
                groupNode = this.ajouterCategorie("Monsters");
            }

        //Creation du noeud
        String name=participant.getLife()+" "+participant.getName();
        treeNode = new DefaultMutableTreeNode(name);
        if(mode==0)
            groupNode.add(treeNode);
        else{
            DefaultMutableTreeNode temp = searchNode(groupNode,name);
            if(temp != null)
                groupNode.remove(temp);
            groupNode.insert(treeNode, 0);
        }
        model.reload();
        return treeNode;
    }

    public DefaultMutableTreeNode ajouterPlayer(Player participant, int mode){
        DefaultMutableTreeNode treeNode = null;
        DefaultTreeModel model = (DefaultTreeModel)this.jTree1.getModel();
        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode)model.getRoot();
        DefaultMutableTreeNode groupNode=null;
        //Recherche du noeud
        groupNode = searchNode(rootNode,"Players");
        //Si on ne le trouve pas on creer le groupe
        if(groupNode == null)
            groupNode = this.ajouterCategorie("Players");
        //Creation du noeud
        String name=participant.getLife()+" "+participant.getName();
        treeNode = new DefaultMutableTreeNode(name);
        if(mode==0)
            groupNode.add(treeNode);
        else{
            DefaultMutableTreeNode temp = searchNode(groupNode,name);
            if(temp != null)
                groupNode.remove(temp);
            groupNode.insert(treeNode, 0);
        }
        model.reload();
        return treeNode;
    }

    public DefaultMutableTreeNode ajouterMonster(Monster participant, int mode){
        DefaultMutableTreeNode treeNode = null;
        DefaultTreeModel model = (DefaultTreeModel)this.jTree1.getModel();
        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode)model.getRoot();
        DefaultMutableTreeNode groupNode=null;
        //Recherche du noeud
        groupNode = searchNode(rootNode,"Monsters");
        //Si on ne le trouve pas on creer le groupe
        if(groupNode == null)
            groupNode = this.ajouterCategorie("Monsters");
        //Creation du noeud
        String name=participant.getLife()+" "+participant.getName();
        treeNode = new DefaultMutableTreeNode(name);
        if(mode==0)
            groupNode.add(treeNode);
        else{
            DefaultMutableTreeNode temp = searchNode(groupNode,name);
            if(temp != null)
                groupNode.remove(temp);
            groupNode.insert(treeNode, 0);
        }
        model.reload();
        return treeNode;
    }

    public void resetJTree(){
        DefaultTreeModel model = (DefaultTreeModel)this.jTree1.getModel();
        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode)model.getRoot();
        rootNode.removeAllChildren();
        model.setRoot(rootNode);
    }

    public static DefaultMutableTreeNode searchNode(DefaultMutableTreeNode parentNode, Object childNode){
        DefaultMutableTreeNode temp = null;
        Enumeration<?> children = parentNode.children();
        while (children.hasMoreElements()) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) children.nextElement();
            if(child.getUserObject().equals(childNode)){
                temp = child;
                break;
            }
        }
        return temp;
    }

    public void startFight(Participant forward, Participant attacked) {
        String alert=forward.getName()+" attaque "+attacked.getName()+" !\n";
        jTextArea4.append(alert);
    }
    public void hitpoints(Participant forward, Participant attacked, int hitpoints) {
        String alert=forward.getName()+" fait perdre "+hitpoints+" pts de vie à "+attacked.getName()+" !\n";
        jTextArea4.append(alert);
    }
    public void endFight(ArrayList<Participant> winners, Participant looser) {
        String alert="Perdant : "+looser.getName()+" Gagnants : "+winners.toString()+"\n";
        jTextArea4.append(alert);
    }
    public void alertRunnaway(Participant forward, Participant runner) {
        String alert=runner.getName()+" fuit le combat face à "+forward.getName()+" !\n";
        jTextArea4.append(alert);
    }
    public void heal() {
        String alert="Tous le monde est soigné !\n";
        jTextArea4.append(alert);
    }
}
