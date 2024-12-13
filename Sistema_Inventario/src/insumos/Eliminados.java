/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insumos;

import insumos.ListaInsumos;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import static insumos.Pantalla_1.InsumoScroll;

/**
 *
 * @author windows
 */
public class Eliminados extends javax.swing.JPanel {

    /**
     * Creates new form Eliminados
     */
    E_Insumos e_in = new E_Insumos();
    E_Proveedores e_n = new E_Proveedores();
    
    public Eliminados() {
        initComponents();
        E_Insumos ei = new E_Insumos();
        e_scroll.setViewportView(ei);
        setPreferredSize(new Dimension(1039,475));
        setBackground(new Color(252,249,249));
        btn_einsumos.setBackground(new Color(29,45,80));
        
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btn_einsumos = new javax.swing.JButton();
        btn_enotificaciones = new javax.swing.JButton();
        e_scroll = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Insumos y Proveedores Eliminados");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        btn_einsumos.setBackground(new java.awt.Color(153, 255, 102));
        btn_einsumos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_einsumos.setForeground(new java.awt.Color(255, 255, 255));
        btn_einsumos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_insumos/insumos1.png"))); // NOI18N
        btn_einsumos.setText("Insumos");
        btn_einsumos.setBorder(null);
        btn_einsumos.setBorderPainted(false);
        btn_einsumos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_einsumos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_einsumosActionPerformed(evt);
            }
        });
        add(btn_einsumos, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, 140, 50));

        btn_enotificaciones.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_enotificaciones.setForeground(new java.awt.Color(255, 255, 255));
        btn_enotificaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_insumos/notificaciones.png"))); // NOI18N
        btn_enotificaciones.setText("Proveedores");
        btn_enotificaciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_enotificaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_enotificacionesActionPerformed(evt);
            }
        });
        add(btn_enotificaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, 170, 50));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1018, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );

        e_scroll.setViewportView(jPanel1);

        add(e_scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 1020, 340));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_einsumosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_einsumosActionPerformed
        // TODO add your handling code here:
        
        e_scroll.remove(this);
        e_scroll.setViewportView(e_in);
        this.btn_einsumos.setBackground(new Color(29,45,80));
        this.btn_enotificaciones.setBackground(Color.gray);
             
    }//GEN-LAST:event_btn_einsumosActionPerformed

    private void btn_enotificacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_enotificacionesActionPerformed
        // TODO add your handling code here:
        
        e_scroll.remove(this);
        e_scroll.setViewportView(e_n);
        this.btn_enotificaciones.setBackground(new Color(29,45,80));
        this.btn_einsumos.setBackground(Color.GRAY);
    }//GEN-LAST:event_btn_enotificacionesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_einsumos;
    private javax.swing.JButton btn_enotificaciones;
    public static javax.swing.JScrollPane e_scroll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
