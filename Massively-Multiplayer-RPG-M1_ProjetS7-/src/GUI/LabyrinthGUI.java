package GUI;

import Game.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTMLDocument;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

/**
 *
 * @author Thomas
 */
public class LabyrinthGUI extends javax.swing.JPanel {

    // Variables declaration - do not modify
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;

    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private MyTextPane jTextPane1;
    private javax.swing.JTree jTree1;
    private static Context context;
    private String myplayer;
    private String debutTexte;
    private String finTexte;

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
                label.setText("<html><font color=gray>" + node + "</font></html>");
            }else if(node.equals("Players")){
                label.setIcon(new ImageIcon(getClass().getResource("/GUI/Icones/Players.png")));
                label.setText("<html><font color=gray>" + node +"</font></html>");
            }else{
                // Paint the node in red
                setForeground(new Color(223, 24 ,24));
                label.setIcon(new ImageIcon(getClass().getResource("/GUI/Icones/life.png")));
                String[] resultats = node.split(" ");
                System.out.println(node+" = "+myplayer);
                int temp=node.split(" ")[0].length();
                System.out.println(node.substring(temp+1));
                if(node.substring(temp+1).equals(myplayer)){
                    label.setText("<html><font color=red>"+resultats[0]+"</font> <font color=orange>"+resultats[1]+"</font></html>");
                }else{
                    label.setText("<html><font color=red>"+resultats[0]+"</font> <font color=gray>"+resultats[1]+"</font></html>");
                }
            }
            label.setFont(new java.awt.Font("Tahoma", 0, 16));
            label.setPreferredSize(new Dimension(200,23));
            return label;
        }
    }

    /**
     * Creates new form LabyrinthGUI
     */
    public LabyrinthGUI(Context context, String myplayer) {
        this.myplayer=myplayer;
        this.context=context;
        initComponents();
        initJTree();
    }

    public JTree getjTree1() {
        return jTree1;
    }

    public void setjTree1(JTree jTree1) {
        this.jTree1 = jTree1;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        LabyrinthGUI.context = context;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    private class MyTextPane extends JTextPane {
        public MyTextPane() {
            super();
            setOpaque(false);
            // this is needed if using Nimbus L&F - see http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6687960
            //setBackground(new Color(120,120,120,120));
        }

        @Override
        public void paintComponent(Graphics g) {
            // set background green - but can draw image here too
            g.setColor(Color.GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
            super.paintComponent(g);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();

        jScrollPane3 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jScrollPane6 = new javax.swing.JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane7 = new javax.swing.JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jTextPane1 = new MyTextPane();

        setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jScrollPane3.setBackground(new java.awt.Color(102, 102, 102));

        jScrollPane7.setBackground(new java.awt.Color(102, 102, 102));
        jScrollPane7.setBorder(null);

        jTree1.setBackground(new java.awt.Color(52, 52, 52));
        jTree1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTree1.setForeground(new java.awt.Color(255,255,255));
        jTree1.setMaximumSize(new java.awt.Dimension(107, 84));
        jTree1.setPreferredSize(new java.awt.Dimension(180, 64));
        DefaultMutableTreeNode treeNode1 = new DefaultMutableTreeNode("root");
        jTree1.setModel(new DefaultTreeModel(treeNode1));
        jTree1.setRootVisible(false);
        jTree1.setToggleClickCount(1);
        jTree1.setCellRenderer(new MyTreeCellRenderer());
        BasicTreeUI basicTreeUI = (BasicTreeUI) jTree1.getUI();
        basicTreeUI.setRightChildIndent(2);
        basicTreeUI.setLeftChildIndent(4);
        jTree1.setFocusable(false);
        jScrollPane3.setViewportView(jTree1);

        jPanel5.add(jScrollPane3, java.awt.BorderLayout.LINE_START);

        jScrollPane6.setBackground(new java.awt.Color(102, 102, 102));
        jScrollPane6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextPane1.setFocusable(false);
        jTextPane1.setBorder(null);
        jTextPane1.setContentType("text/html"); // NOI18N
        jTextPane1.setStyledDocument(new HTMLDocument());
        jTextPane1.setBackground(Color.getHSBColor(102,102,102));
        debutTexte="<html><body><p style=\"margin-top: 5 ; margin-left: 7\">\r<font size=\"+1\">C'est partie !<font size=\"0\"><br>\n<br>\n</font>";
        finTexte="</font></p>\n</body>\n</html>\n";
        jTextPane1.setText(debutTexte+finTexte);
        jScrollPane7.setViewportView(jTextPane1);
        jPanel6.add(jScrollPane7,BorderLayout.CENTER);
        jScrollPane6.setViewportView(jPanel6);
        jPanel5.add(jScrollPane6, java.awt.BorderLayout.CENTER);

        add(jPanel5, java.awt.BorderLayout.CENTER);



        jScrollPane7.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));


    }// </editor-fold>

    public JScrollPane getjScrollPane7() {
        return jScrollPane7;
    }

    public JPanel getjPanel5() {
        return jPanel5;
    }

    public void setjPanel5(JPanel jPanel5) {
        this.jPanel5 = jPanel5;
    }

    public JScrollPane getjScrollPane6() {
        return jScrollPane6;
    }

    public void setjScrollPane6(JScrollPane jScrollPane6) {
        this.jScrollPane6 = jScrollPane6;
    }

    public JPanel getjPanel6() {
        return jPanel6;
    }

    public MyTextPane getjTextPane1() {
        return jTextPane1;
    }

    public void setjTextPane1(MyTextPane jTextPane1) {
        this.jTextPane1 = jTextPane1;
    }

    public DefaultMutableTreeNode removeAllChildren(DefaultMutableTreeNode rootNode){
        while(rootNode.getChildCount() > 0){
            DefaultMutableTreeNode t = (DefaultMutableTreeNode)rootNode.getChildAt(0);
            rootNode.remove(t);
        }
        return rootNode;
    }

    public void removeAll(){
        DefaultMutableTreeNode myRoot = ((DefaultMutableTreeNode) jTree1.getModel().getRoot());
        myRoot = removeAllChildren(myRoot);
        ((DefaultTreeModel) jTree1.getModel()).setRoot(myRoot);
    }

    public void initJTree(){
        ArrayList<Player> players = context.getPlayers();
        ArrayList<Monster> monsters = context.getMonsters();
        for(Player player:players) {
            this.ajouterPlayer(player,0);
        }
        for(Monster monster:monsters) {
            this.ajouterMonster(monster,0);
        }
        DefaultTreeModel model = (DefaultTreeModel)this.jTree1.getModel();
        model.reload();
        expandAll(jTree1);
    }

    public void expandAll(JTree tree) {
        for (int i = 0; i < tree.getRowCount(); i++) {
            tree.expandRow(i);
        }
    }

    public DefaultMutableTreeNode ajouterCategorie(String nomCategorie){
        DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(nomCategorie);
        DefaultTreeModel model = (DefaultTreeModel)this.jTree1.getModel();
        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode)model.getRoot();
        rootNode.add(treeNode);
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
        else if(mode==1) {
            DefaultMutableTreeNode temp = searchNode(groupNode,name);
            if(temp != null) {
                temp.setUserObject(name);
            }else{
                groupNode.insert(treeNode, 0);
            }
        }
        model.reload();
        return treeNode;
    }

    public void removePlayer(Player player){
        append("<font color=blue>"+player.getName()+"</font> vient de quitter la salle.");
        removeParticipant(player);
    }

    public void removeParticipant(Participant participant){
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

        if(groupNode == null)
            return;
        String name=participant.getLife()+" "+participant.getName();
        DefaultMutableTreeNode temp = searchNode(groupNode,name);
        if(temp != null) {
            DefaultMutableTreeNode parent=((DefaultMutableTreeNode) temp.getParent());
            parent.remove(temp);
            if(parent.getChildCount()==0)
                ((DefaultMutableTreeNode) parent.getParent()).remove(parent);

        }
        model.reload();
        expandAll(jTree1);
    }

    public void ajouterParticipant(Participant participant){
        if(participant instanceof Player){
            append("<font color=blue>"+participant.getName()+"</font> vient de rentrer dans la salle.");
            ajouterPlayer((Player)participant,0);
        }else if(participant instanceof Monster){
            append("<font color=red>"+participant.getName()+"</font> vient de faire apparition dans la salle.");
            ajouterMonster((Monster)participant,0);
        }
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
        expandAll(jTree1);
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
        expandAll(jTree1);
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
            if (child.isLeaf()){
                System.out.println(child.getUserObject().toString()+" et "+childNode);
                if (child.getUserObject().toString().split(" ")[1].equals(childNode.toString().split(" ")[1])) {
                    temp = child;
                    break;
                }
            }else{
                if (child.getUserObject().equals(childNode)) {
                    temp = child;
                    break;
                }
            }
        }
        return temp;
    }

    public static Enumeration saveExpansionState(JTree tree) {
        return tree.getExpandedDescendants(new TreePath(tree.getModel().getRoot()));
    }
    public static void loadExpansionState(JTree tree, Enumeration enumeration) {
        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                TreePath treePath = (TreePath) enumeration.nextElement();
                tree.expandPath(treePath);
            }
        }
    }

    public void actualiserJTree(Participant participant , int mode){
        if(mode==0) {
            DefaultTreeModel model = (DefaultTreeModel) this.jTree1.getModel();
            DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) model.getRoot();
        }else if (mode==1) {
            DefaultMutableTreeNode temp = this.ajouterParticipant(participant, 1);
        }
        expandAll(jTree1);
    }

    public void append(String string){
        debutTexte=debutTexte+string+"<font size=\"0\"><br>\n<br>\n</font>";
        jTextPane1.setText(debutTexte+finTexte);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jScrollPane7.getVerticalScrollBar().setValue(jScrollPane7.getVerticalScrollBar().getMaximum());
            }
        });
    }

    public void startFight(Participant forward, Participant attacked) {
        String forwardName="";
        String attackedName="";
        if(attacked instanceof Monster){
            attackedName="<font color=red>"+attacked.getName()+"</font>";
        }else if(attacked instanceof Player){
            attackedName="<font color=blue>"+attacked.getName()+"</font>";
        }
        if(forward instanceof Monster){
            forwardName="<font color=red>"+forward.getName()+"</font>";
        }else if(forward instanceof Player){
            forwardName="<font color=blue>"+forward.getName()+"</font>";
        }
        String alert=forwardName+" attaque "+attackedName+" !";
        append(alert);
    }
    public void hitpoints(Participant forward, Participant attacked, int hitpoints) {
        String forwardName="";
        String attackedName="";
        if(attacked instanceof Monster){
            attackedName="<font color=red>"+attacked.getName()+"</font>";
        }else if(attacked instanceof Player){
            attackedName="<font color=blue>"+attacked.getName()+"</font>";
        }
        if(forward instanceof Monster){
            forwardName="<font color=red>"+forward.getName()+"</font>";
        }else if(forward instanceof Player){
            forwardName="<font color=blue>"+forward.getName()+"</font>";
        }
        String alert=forwardName+" retire "+hitpoints+" pts de vie à "+attackedName+".";
        append(alert);
        actualiserJTree(attacked,1);

    }
    public void endFight(ArrayList<Participant> winners, Participant looser) {
        String looserName="";
        if(looser instanceof Monster){
            looserName="<font color=red>"+looser.getName()+"</font>";
        }else if(looser instanceof Player){
            looserName="<font color=blue>"+looser.getName()+"</font>";
        }
        String alert="Perdant : "+looserName;
        if(winners.size()==1)
            alert=alert+" Gagnant : ";
        else
            alert=alert+" Gagnants : ";
        for (Iterator<Participant> ite = winners.iterator();ite.hasNext();){
            Participant winner=ite.next();
            if(winner instanceof Monster){
                alert=alert+"<font color=red>"+winner.getName()+"</font>";
            }else if(winner instanceof Player){
                alert=alert+"<font color=blue>"+winner.getName()+"</font>";
            }
            if(ite.hasNext())
                alert=alert+", ";
        }
        append(alert);
        removeParticipant(looser);
    }

    public void alertRunnaway(Participant forward, Participant runner) {
        String forwardName="";
        String runnerName="";
        if(runner instanceof Monster){
            runnerName="<font color=red>"+runner.getName()+"</font>";
        }else if(runner instanceof Player){
            runnerName="<font color=blue>"+runner.getName()+"</font>";
        }
        if(forward instanceof Monster){
            forwardName="<font color=red>"+forward.getName()+"</font>";
        }else if(forward instanceof Player){
            forwardName="<font color=blue>"+forward.getName()+"</font>";
        }
        String alert=runnerName+" fuit le combat face à "+forwardName+" !";
        append(alert);
    }
    public void heal() {
        String alert="Tous le monde est soigné !";
        append(alert);
    }
    public void useBonus(Participant participant, Bonus bonus){
        String alert = "<font color=blue>"+participant.getName()+"</font> utilise "+bonus.getName()+" !";
        append(alert);
        actualiserJTree(participant,1);
    }
    public void showInfosBonus(String name, int life, int attack, int resilience, int chance, int maxlife){
        String alert = "<font color=purple>"+name+"</font> [ Life : "+life+" Attack : "+attack+" Resilience : "+resilience+" Chance : "+chance+" Maxlife : "+maxlife+" ]";
        append(alert);
    }
    public void showInfosParticipant(Participant participant) {
        String name=participant.getName();
        int attack = participant.getAttack();
        int resilience=participant.getResilience();
        int chance=participant.getChance();
        int maxlife=participant.getMaxlife();
        String alert="";
        if(participant instanceof Player){
            alert = "<font color=blue>"+name+"</font> [ Attack : "+attack+" Resilience : "+resilience+" Chance : "+chance+" Maxlife : "+maxlife+" ]";
        }else if(participant instanceof Monster){
            alert = "<font color=blue>"+name+"</font> [ Attack : "+attack+" Resilience : "+resilience+" Chance : "+chance+" Maxlife : "+maxlife+" ]";
        }
        if(alert!="")
            append(alert);
    }

    public void receiveBonus(Bonus bonus){
        String name=bonus.getName();
        int life = bonus.getLife();
        int attack = bonus.getAttack();
        int resilience=bonus.getResilience();
        int chance=bonus.getChance();
        int maxlife=bonus.getMaxlife();
        String alert = "Vous venez de ramasser \"<font color=purple>"+name+"</font>\" [ Life : "+life+" Attack : "+attack+" Resilience : "+resilience+" Chance : "+chance+" Maxlife : "+maxlife+" ]";
        append(alert);
    }
}