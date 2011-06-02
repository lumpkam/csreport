/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * fErrores.java
 *
 * Created on 02/06/2011, 11:39:01
 */
package cskernelclient;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jdesktop.application.Action;

/**
 *
 * @author jalvarez
 */
public class fErrores extends javax.swing.JDialog {

    /** Creates new form fErrores */
    public fErrores(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setSize(535, 130);
        this.setLocationRelativeTo( null );
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmdOk = new javax.swing.JButton();
        cmdDetails = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance().getContext().getActionMap(fErrores.class, this);
        cmdOk.setAction(actionMap.get("cmdOkClick")); // NOI18N
        cmdOk.setText("Aceptar");
        cmdOk.setName("cmdOk"); // NOI18N
        getContentPane().add(cmdOk);
        cmdOk.setBounds(433, 27, 71, 23);

        cmdDetails.setAction(actionMap.get("cmdDetailsClick")); // NOI18N
        cmdDetails.setText("Detalles");
        cmdDetails.setName("cmdDetails"); // NOI18N
        getContentPane().add(cmdDetails);
        cmdDetails.setBounds(433, 56, 71, 23);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cskernelclient/resources/Warning-icon.png"))); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 11, 48, 48);

        jLabel2.setText("Ha ocurrido un error en el sistema.");
        jLabel2.setName("jLabel2"); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(76, 31, 166, 14);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 100, 494, 96);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                try {
                    // Set cross-platform Java L&F (also called "Metal")
                    UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName());
                } 
                catch (UnsupportedLookAndFeelException e) {
                   // handle exception
                }
                catch (ClassNotFoundException e) {
                   // handle exception
                }
                catch (InstantiationException e) {
                   // handle exception
                }
                catch (IllegalAccessException e) {
                   // handle exception
                }

                fErrores dialog = new fErrores(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    @Action
    public void cmdOkClick() {
        this.setVisible(false);
    }

    @Action
    public void cmdDetailsClick() {
        if (cmdDetails.getText().equals("Detalles")) {
            cmdDetails.setText("Ocultar");
            this.setSize(535, 250);
        }
        else {
            cmdDetails.setText("Detalles");
            this.setSize(535, 130);
        }
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdDetails;
    private javax.swing.JButton cmdOk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
