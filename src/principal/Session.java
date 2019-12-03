/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import Alerts.Information;
import ds.desktop.notify.DesktopNotify;
import java.awt.Color;
import java.awt.Frame;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

/**
 *
 * @author Najera10
 */
public class Session extends javax.swing.JFrame {

int x,y;
    public Session() {
        initComponents();
        this.setBackground(new Color(0,0,0,0));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        txtusuario.requestFocus();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelRound1 = new LIB.JPanelRound();
        jLabel1 = new javax.swing.JLabel();
        fSButtonMD1 = new LIB.FSButtonMD();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtusuario = new jcmouse.materialdesign.TextInput();
        txtcontrasena = new LIB.FSPasswordFieldMD();
        fSGradientPanel1 = new LIB.FSGradientPanel();
        jEImagePanel1 = new LIB.JEImagePanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelRound1.setBackground(new java.awt.Color(255, 255, 255));
        jPanelRound1.setOpaque(true);
        jPanelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel1.setText("LOGIN ");
        jPanelRound1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 90, 60));

        fSButtonMD1.setBackground(new java.awt.Color(0, 205, 248));
        fSButtonMD1.setText("Finish");
        fSButtonMD1.setColorHover(new java.awt.Color(0, 205, 248));
        fSButtonMD1.setColorNormal(new java.awt.Color(211, 134, 233));
        fSButtonMD1.setColorPressed(new java.awt.Color(188, 198, 233));
        fSButtonMD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fSButtonMD1ActionPerformed(evt);
            }
        });
        jPanelRound1.add(fSButtonMD1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/icons8-multiplicar-50.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanelRound1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, -1, 40));

        jLabel5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel5MouseDragged(evt);
            }
        });
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });
        jPanelRound1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 40));

        txtusuario.setColorPrimary(new java.awt.Color(183, 193, 133));
        txtusuario.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        txtusuario.setHint("Username");
        jPanelRound1.add(txtusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 230, -1));

        txtcontrasena.setForeground(new java.awt.Color(0, 0, 0));
        txtcontrasena.setBordeColorFocus(new java.awt.Color(183, 193, 133));
        txtcontrasena.setBordeColorNoFocus(new java.awt.Color(153, 153, 153));
        txtcontrasena.setCaretColor(new java.awt.Color(153, 153, 153));
        txtcontrasena.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        txtcontrasena.setFocusAccelerator('0');
        txtcontrasena.setFocusTraversalPolicyProvider(true);
        txtcontrasena.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        txtcontrasena.setMaterialDesing(true);
        txtcontrasena.setPlaceholder("Password");
        txtcontrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcontrasenaActionPerformed(evt);
            }
        });
        txtcontrasena.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcontrasenaKeyReleased(evt);
            }
        });
        jPanelRound1.add(txtcontrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 220, -1));

        getContentPane().add(jPanelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 11, 300, 440));

        fSGradientPanel1.setFSEndColor(new java.awt.Color(0, 205, 248));
        fSGradientPanel1.setFSStartColor(new java.awt.Color(186, 204, 233));

        jEImagePanel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/icons8-usuario-masculino-en-círculo-80.png"))); // NOI18N

        javax.swing.GroupLayout jEImagePanel1Layout = new javax.swing.GroupLayout(jEImagePanel1);
        jEImagePanel1.setLayout(jEImagePanel1Layout);
        jEImagePanel1Layout.setHorizontalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jEImagePanel1Layout.setVerticalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel2.setText("Bienvenido");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("SISTEMA DE CONTROL DE INVENTARIO ");

        javax.swing.GroupLayout fSGradientPanel1Layout = new javax.swing.GroupLayout(fSGradientPanel1);
        fSGradientPanel1.setLayout(fSGradientPanel1Layout);
        fSGradientPanel1Layout.setHorizontalGroup(
            fSGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fSGradientPanel1Layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addGroup(fSGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fSGradientPanel1Layout.createSequentialGroup()
                        .addComponent(jEImagePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fSGradientPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(109, 109, 109))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fSGradientPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(30, 30, 30))))
        );
        fSGradientPanel1Layout.setVerticalGroup(
            fSGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fSGradientPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jEImagePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        getContentPane().add(fSGradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, 330, 280));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
 cerrar();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
          x = evt.getX();
          y = evt.getY();
    }//GEN-LAST:event_jLabel5MousePressed

    private void jLabel5MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseDragged
        Point mueve = MouseInfo.getPointerInfo().getLocation();
        this.setLocation(mueve.x - x, mueve.y - y);
    }//GEN-LAST:event_jLabel5MouseDragged

    private void fSButtonMD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fSButtonMD1ActionPerformed
    String Usuario = txtusuario.getText();
        String Pass = txtcontrasena.getText();
        
            if(Usuario.equals("") || Pass.equals("") ){
                //JOptionPane.showMessageDialog(null, "Verificar Datos ");
                  Information info  = new Information(new Frame(), true);
                info.jLabel1.setText("iformacion !!!");
                info.textos.setText("Verifique los datos");
                info.setVisible(true);
            }else{
                BD.login lo = new BD.login();
                lo.validar_ingreso(Usuario, Pass);
                this.setVisible(false);
               
                 
            }        // TODO add your handling code here:
    }//GEN-LAST:event_fSButtonMD1ActionPerformed

    private void txtcontrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcontrasenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcontrasenaActionPerformed

    private void txtcontrasenaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcontrasenaKeyReleased
     if(evt.getKeyCode() == KeyEvent.VK_ENTER){
        String Usuario = txtusuario.getText();
        String Pass = txtcontrasena.getText();
        
            if(Usuario.equals("") || Pass.equals("") ){
                Information info  = new Information(new Frame(), true);
                info.jLabel1.setText("iformacion !!!");
                info.textos.setText("Verifique los datos");
                info.setVisible(true);
            }else{
                BD.login lo = new BD.login();
                lo.validar_ingreso(Usuario, Pass);
                this.setVisible(false);
                
               
                 
            }
     }// TODO add your handling code here:
    }//GEN-LAST:event_txtcontrasenaKeyReleased
 public void cerrar(){
 Object [] opciones ={"Aceptar","Cancelar"};
 int eleccion = JOptionPane.showOptionDialog(rootPane,"En realidad desea realizar cerrar la aplicacion","Mensaje de Confirmacion",
 JOptionPane.YES_NO_OPTION,
 JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
 if (eleccion == JOptionPane.YES_OPTION)
 {
  
 DesktopNotify.showDesktopMessage("Gracias","Hasta Luego", DesktopNotify.INFORMATION, 3000);
 
 this.dispose();
 
 }else{
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
            java.util.logging.Logger.getLogger(Session.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Session.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Session.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Session.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Session().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private LIB.FSButtonMD fSButtonMD1;
    private LIB.FSGradientPanel fSGradientPanel1;
    private LIB.JEImagePanel jEImagePanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private LIB.JPanelRound jPanelRound1;
    private LIB.FSPasswordFieldMD txtcontrasena;
    private jcmouse.materialdesign.TextInput txtusuario;
    // End of variables declaration//GEN-END:variables
}
