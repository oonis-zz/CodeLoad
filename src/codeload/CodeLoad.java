/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package codeload;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 *
 * @author Sam
 */
public class CodeLoad extends javax.swing.JFrame {

    /**
     * Creates new form CodeLoad
     */
    public CodeLoad() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        menuBar = new javax.swing.JMenuBar();
        fileMenuButton = new javax.swing.JMenu();
        openMenuButton = new javax.swing.JMenuItem();
        saveMenuButton = new javax.swing.JMenuItem();
        quitMenuButton = new javax.swing.JMenuItem();
        editMenuButton = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        copyMenuButton = new javax.swing.JMenuItem();
        pasteMenuButton = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        fileMenuButton.setText("File");

        openMenuButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openMenuButton.setText("Open");
        openMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuButtonActionPerformed(evt);
            }
        });
        fileMenuButton.add(openMenuButton);

        saveMenuButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveMenuButton.setText("Save");
        saveMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuButtonActionPerformed(evt);
            }
        });
        fileMenuButton.add(saveMenuButton);

        quitMenuButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        quitMenuButton.setText("Exit");
        quitMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitMenuButtonActionPerformed(evt);
            }
        });
        fileMenuButton.add(quitMenuButton);

        menuBar.add(fileMenuButton);

        editMenuButton.setText("Edit");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Cut");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        editMenuButton.add(jMenuItem1);

        copyMenuButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        copyMenuButton.setText("Copy");
        copyMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyMenuButtonActionPerformed(evt);
            }
        });
        editMenuButton.add(copyMenuButton);

        pasteMenuButton.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        pasteMenuButton.setText("Paste");
        pasteMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasteMenuButtonActionPerformed(evt);
            }
        });
        editMenuButton.add(pasteMenuButton);

        menuBar.add(editMenuButton);
        editMenuButton.getAccessibleContext().setAccessibleDescription("");

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 71, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_openMenuButtonActionPerformed

    private void quitMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitMenuButtonActionPerformed
        // TODO add your handling code here:
        //TODO: make it so that (if it should) prompt the user to save the current progress
        System.exit(0);
    }//GEN-LAST:event_quitMenuButtonActionPerformed

    /*Save Menu Button*/
    private void saveMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_saveMenuButtonActionPerformed

    private void copyMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyMenuButtonActionPerformed
        // TODO add your handling code here:
        String selection = textArea.getSelectedText();  //store selected text as a string
        StringSelection data = new StringSelection(selection);  //converts the string to StringSelection
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(data, null);   //Transfers that to the clipboard
    }//GEN-LAST:event_copyMenuButtonActionPerformed

    private void pasteMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasteMenuButtonActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_pasteMenuButtonActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        //1)copy selected text
        //2)remove selected text from textArea
        //3)Profit
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(CodeLoad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CodeLoad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CodeLoad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CodeLoad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CodeLoad().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem copyMenuButton;
    private javax.swing.JMenu editMenuButton;
    private javax.swing.JMenu fileMenuButton;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuButton;
    private javax.swing.JMenuItem pasteMenuButton;
    private javax.swing.JMenuItem quitMenuButton;
    private javax.swing.JMenuItem saveMenuButton;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables
}
