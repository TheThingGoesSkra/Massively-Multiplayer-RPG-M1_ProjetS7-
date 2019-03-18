package GUI;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author Thomas
 */
public class MessagerieGUI extends javax.swing.JPanel {

    // Variables declaration - do not modify
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextPane jTextPane1;
    private String debutTexte;
    private String finTexte;
    // End of variables declaration

    /**
     * Creates new form MessagerieGUI
     */
    public MessagerieGUI() {
        initComponents();
    }

    public JPanel getjPanel3() {
        return jPanel3;
    }

    public void setjPanel3(JPanel jPanel3) {
        this.jPanel3 = jPanel3;
    }

    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }

    public JScrollPane getjScrollPane6() {
        return jScrollPane6;
    }

    public void setjScrollPane6(JScrollPane jScrollPane6) {
        this.jScrollPane6 = jScrollPane6;
    }

    public JTextPane getjTextPane1() {
        return jTextPane1;
    }

    public void setjTextPane1(JTextPane jTextPane1) {
        this.jTextPane1 = jTextPane1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jPanel3 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jTextPane1 = new javax.swing.JTextPane();

        setBackground(new java.awt.Color(102, 102, 102));
        setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBackground(new java.awt.Color(102, 102, 102));
        jScrollPane1.setCursor(new java.awt.Cursor(Cursor.DEFAULT_CURSOR));



        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jScrollPane6.setBackground(new java.awt.Color(102, 102, 102));
        jScrollPane6.setBorder(null);

        jTextPane1.setFocusable(false);

        jTextPane1.setBackground(new java.awt.Color(102, 102, 102));
        jTextPane1.setBorder(null);
        jTextPane1.setContentType("text/html"); // NOI18N
        debutTexte="<html>\r\n<head>\r\n\r\n</head>\r\n<body bgcolor=\"#FFFFFF\">\r\n<p style=\"margin-top: 5 ; margin-left: 5\">\r<font size=\"+1\">Messagerie :<br>\n";
        finTexte="</font></p>\n</body>\n</html>\n";
        jTextPane1.setText(debutTexte+finTexte);
        jScrollPane6.setViewportView(jTextPane1);
        jPanel3.add(jScrollPane6, BorderLayout.CENTER);

        jScrollPane1.setViewportView(jPanel3);

        add(jScrollPane1, BorderLayout.CENTER);

        jScrollPane6.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

    }// </editor-fold>

    public void append(String string){
        debutTexte=debutTexte+string+"<font size=\"0\"><br>\n<br>\n</font>";
        jTextPane1.setText(debutTexte+finTexte);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                    jScrollPane6.getVerticalScrollBar().setValue(jScrollPane6.getVerticalScrollBar().getMaximum());
            }
        });
    }
}
