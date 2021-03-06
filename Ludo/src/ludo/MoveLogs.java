/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ludo;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author Shreyas
 */
public class MoveLogs extends javax.swing.JFrame {

    /**
     * Creates new form MoveLogs
     */
    public MoveLogs() {
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
        txtDisplay = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(33, 35, 37));
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ludo/images/icon.png")));

        txtDisplay.setEditable(false);
        txtDisplay.setColumns(20);
        txtDisplay.setRows(5);
        txtDisplay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDisplayMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(txtDisplay);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDisplayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDisplayMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDisplayMouseClicked

    public void showData(String str) {
        String arr[]=str.trim().split(" ");
        txtDisplay.setText(txtDisplay.getText()+"Red ("+arr[0]+","+arr[1]+","+arr[2]+","+arr[3]+")   "+
                                                "Green ("+arr[4]+","+arr[5]+","+arr[6]+","+arr[7]+")   "+
                                                "Yellow ("+arr[8]+","+arr[9]+","+arr[10]+","+arr[11]+")   "+
                                                "Blue ("+arr[12]+","+arr[13]+","+arr[14]+","+arr[15]+")   "+
                                                "Dice = "+arr[17]+"\n");
    }
    
    public void file() {
        try
        {
            FileReader fr = new FileReader("move.log");
            BufferedReader br = new BufferedReader(fr);
            String s="";
            while ((s=br.readLine())!=null)
            {
                System.out.println(s);
                txtDisplay.append("\n"+s);
            }
        }
        catch (Exception e)
        {
            System.out.println("Reading error");
            txtDisplay.setText("No Debug data / move log found");
        }
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
            java.util.logging.Logger.getLogger(MoveLogs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MoveLogs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MoveLogs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MoveLogs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MoveLogs().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtDisplay;
    // End of variables declaration//GEN-END:variables
}
