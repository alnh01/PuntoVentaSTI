/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;


import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author najera10
 */
public class Login extends javax.swing.JFrame {

    Timer t = null; 

    public Login() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        panelogin.setBackground(new Color(204,204,204, 120)); 
        txtusuario.requestFocus();
      setIconImage(new ImageIcon(getClass().getResource("/img1/chip.png")).getImage());
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelogin = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtusuario = new javax.swing.JTextField();
        txtcontrasena = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        btnaceptar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 450));
        getContentPane().setLayout(null);

        panelogin.setBackground(new java.awt.Color(204, 204, 204));
        panelogin.setLayout(null);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/pointofsale.png"))); // NOI18N
        panelogin.add(jLabel6);
        jLabel6.setBounds(130, 110, 130, 170);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Bienvenido ");
        panelogin.add(jLabel7);
        jLabel7.setBounds(50, 310, 300, 50);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(null);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("LOGIN");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(140, 30, 180, 70);

        jLabel1.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Usuario");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(90, 140, 60, 30);

        txtusuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtusuario.setBorder(null);
        txtusuario.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel1.add(txtusuario);
        txtusuario.setBounds(160, 140, 170, 30);

        txtcontrasena.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcontrasena.setBorder(null);
        txtcontrasena.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcontrasenaKeyReleased(evt);
            }
        });
        jPanel1.add(txtcontrasena);
        txtcontrasena.setBounds(160, 210, 170, 30);
        txtcontrasena.getAccessibleContext().setAccessibleParent(this);

        jLabel2.setFont(new java.awt.Font("Yu Gothic", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Contraseña");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(60, 210, 90, 30);

        btnaceptar.setBackground(new java.awt.Color(102, 204, 255));
        btnaceptar.setFont(new java.awt.Font("Yu Gothic", 1, 11)); // NOI18N
        btnaceptar.setText("Aceptar");
        btnaceptar.setBorder(null);
        btnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarActionPerformed(evt);
            }
        });
        jPanel1.add(btnaceptar);
        btnaceptar.setBounds(110, 300, 90, 40);

        btncancelar.setBackground(new java.awt.Color(255, 51, 51));
        btncancelar.setFont(new java.awt.Font("Yu Gothic", 1, 11)); // NOI18N
        btncancelar.setText("Cancelar");
        btncancelar.setBorder(null);
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btncancelar);
        btncancelar.setBounds(270, 300, 90, 40);

        panelogin.add(jPanel1);
        jPanel1.setBounds(380, 0, 430, 440);

        getContentPane().add(panelogin);
        panelogin.setBounds(0, 0, 810, 440);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/fondo.jpg"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 0, 380, 440);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
    
        String Usuario = txtusuario.getText();
        String Pass = txtcontrasena.getText();
        
            if(Usuario.equals("") || Pass.equals("") ){
                JOptionPane.showMessageDialog(null, "Verificar Datos ");
            }else{
                BD.login lo = new BD.login();
                lo.validar_ingreso(Usuario, Pass);
                this.setVisible(false);
               
                 
            }
       
    }//GEN-LAST:event_btnaceptarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
       cerrar();
    }//GEN-LAST:event_btncancelarActionPerformed

    private void txtcontrasenaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcontrasenaKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         String Usuario = txtusuario.getText();
         String Pass = txtcontrasena.getText();
        
            if(Usuario.equals("") || Pass.equals("") ){
                JOptionPane.showMessageDialog(null, "Verificar Datos ");
            }else{
                BD.login lo = new BD.login();
                lo.validar_ingreso(Usuario, Pass);
                this.setVisible(false);
            }
        }            // TODO add your handling code here:
    }//GEN-LAST:event_txtcontrasenaKeyReleased

    
       public void cerrar() {
        Object[] opciones = {"Aceptar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(rootPane, "En realidad desea realizar cerrar la aplicacion", "Mensaje de Confirmacion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
        if (eleccion == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaceptar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelogin;
    private javax.swing.JPasswordField txtcontrasena;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}
