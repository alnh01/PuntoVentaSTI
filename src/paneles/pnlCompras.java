/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paneles;

import java.awt.event.KeyListener;
import javax.swing.UIManager;

public class pnlCompras extends javax.swing.JPanel  {

    /**
     * Creates new form pnlHome
     */
    public pnlCompras() {
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

        jLabel7 = new javax.swing.JLabel();
        contenedorModulos = new javax.swing.JDesktopPane();
        fSButtonMD1 = new LIB.FSButtonMD();
        fSButtonMD2 = new LIB.FSButtonMD();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(null);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(128, 128, 131));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("COMPRAS");
        add(jLabel7);
        jLabel7.setBounds(0, 0, 260, 51);

        javax.swing.GroupLayout contenedorModulosLayout = new javax.swing.GroupLayout(contenedorModulos);
        contenedorModulos.setLayout(contenedorModulosLayout);
        contenedorModulosLayout.setHorizontalGroup(
            contenedorModulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1130, Short.MAX_VALUE)
        );
        contenedorModulosLayout.setVerticalGroup(
            contenedorModulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );

        add(contenedorModulos);
        contenedorModulos.setBounds(0, 54, 1130, 610);

        fSButtonMD1.setBackground(new java.awt.Color(102, 204, 255));
        fSButtonMD1.setForeground(new java.awt.Color(0, 0, 0));
        fSButtonMD1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/service (1).png"))); // NOI18N
        fSButtonMD1.setText("Proveedores");
        fSButtonMD1.setColorHover(new java.awt.Color(102, 204, 255));
        fSButtonMD1.setColorNormal(new java.awt.Color(102, 204, 255));
        fSButtonMD1.setColorPressed(new java.awt.Color(102, 204, 255));
        fSButtonMD1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        fSButtonMD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fSButtonMD1ActionPerformed(evt);
            }
        });
        add(fSButtonMD1);
        fSButtonMD1.setBounds(300, 0, 160, 50);

        fSButtonMD2.setBackground(new java.awt.Color(144, 209, 0));
        fSButtonMD2.setForeground(new java.awt.Color(0, 0, 0));
        fSButtonMD2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/product.png"))); // NOI18N
        fSButtonMD2.setText("Ingresos");
        fSButtonMD2.setColorNormal(new java.awt.Color(144, 209, 0));
        fSButtonMD2.setColorPressed(new java.awt.Color(144, 209, 0));
        fSButtonMD2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        fSButtonMD2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fSButtonMD2ActionPerformed(evt);
            }
        });
        add(fSButtonMD2);
        fSButtonMD2.setBounds(460, 0, 170, 50);
    }// </editor-fold>//GEN-END:initComponents
panelingresos  pnlingreso;pnlproveedores panleprov;
    private void fSButtonMD2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fSButtonMD2ActionPerformed
       if(pnlingreso==null){
            pnlingreso = new panelingresos();
            contenedorModulos.add(pnlingreso);
            contenedorModulos.getDesktopManager().maximizeFrame(pnlingreso);
            pnlingreso.setVisible(true);
            pnlingreso.limpiarCategoria();
            pnlingreso.cargarModeloProv();
        }
        else{
            contenedorModulos.getDesktopManager().maximizeFrame(pnlingreso);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_fSButtonMD2ActionPerformed

    private void fSButtonMD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fSButtonMD1ActionPerformed
  if(panleprov==null){
            panleprov = new pnlproveedores();
            contenedorModulos.add(panleprov);
            contenedorModulos.getDesktopManager().maximizeFrame(panleprov);
            panleprov.setVisible(true);
            
        }
        else{
            contenedorModulos.getDesktopManager().maximizeFrame(panleprov);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_fSButtonMD1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane contenedorModulos;
    private LIB.FSButtonMD fSButtonMD1;
    private LIB.FSButtonMD fSButtonMD2;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
