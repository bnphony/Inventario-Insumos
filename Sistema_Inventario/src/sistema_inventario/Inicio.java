/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema_inventario;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Usuario
 */
public class Inicio extends javax.swing.JFrame {

    alertas alerta = new alertas();

    CardLayout vista;
    int con = 0;

    public static String tipo_usuario = "";
    public static String usuario_iniciado = "";

    alertas configura;

    public Inicio() {

        initComponents();
        this.setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes_insumos/sello_prolase.png")).getImage());
        //iconoFOrm();

    }

    public void iconoFOrm() {
        URL url = getClass().getResource("/imagenes/PROLASEa.png");
        ImageIcon icono_inicio = new ImageIcon(url);
        setIconImage(icono_inicio.getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        i_password = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        inombre_usuario = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        panel_config1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 1050, 20));

        jPanel2.setBackground(new java.awt.Color(252, 249, 249));
        jPanel2.setForeground(new java.awt.Color(133, 150, 240));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 160, 170));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(5, 50, 50));
        jLabel2.setText("Contraseña:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, 110, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(5, 50, 50));
        jLabel3.setText("Usuario:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 190, 110, 30));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 350, 210, 10));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, 210, 10));

        i_password.setBackground(new java.awt.Color(102, 102, 102));
        i_password.setForeground(new java.awt.Color(255, 255, 255));
        i_password.setText("jPasswordField1");
        i_password.setBorder(null);
        i_password.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                i_passwordMouseClicked(evt);
            }
        });
        i_password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                i_passwordActionPerformed(evt);
            }
        });
        jPanel2.add(i_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 310, 170, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/use.png"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, 40, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/contra_dos.png"))); // NOI18N
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 310, 40, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/entrar_one.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/entrar.png"))); // NOI18N
        jButton1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/entrar.png"))); // NOI18N
        jButton1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/entrar.png"))); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 370, 160, 40));

        inombre_usuario.setBackground(new java.awt.Color(102, 102, 102));
        inombre_usuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inombre_usuario.setForeground(new java.awt.Color(255, 255, 255));
        inombre_usuario.setText("   Ingrese usuario ");
        inombre_usuario.setBorder(null);
        inombre_usuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inombre_usuarioMouseClicked(evt);
            }
        });
        inombre_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inombre_usuarioActionPerformed(evt);
            }
        });
        jPanel2.add(inombre_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 220, 170, 30));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 460, 460));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 1060, 480));

        panel_config1.setBackground(new java.awt.Color(252, 249, 249));
        panel_config1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/minimizar.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        panel_config1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 30, 50, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salir.png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        panel_config1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 30, 40, 30));

        jLabel9.setFont(new java.awt.Font("Narkisim", 0, 48)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(5, 66, 108));
        jLabel9.setText("Sistema de Gestion de Inventario ");
        panel_config1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 750, 50));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes_login/LOGO.png"))); // NOI18N
        panel_config1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 150, 120));

        getContentPane().add(panel_config1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 100));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void i_passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_i_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_i_passwordActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        this.setState(Inicio.ICONIFIED);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        int dialog = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "Desea Salir del Programa", "Exit", dialog);
        if (result == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_jLabel8MouseClicked

    private void i_passwordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_i_passwordMouseClicked
        i_password.setText("");
    }//GEN-LAST:event_i_passwordMouseClicked

    private void inombre_usuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inombre_usuarioMouseClicked
        if (con == 0) {

            con++;
            inombre_usuario.setText("");
            i_password.setText("");
        }


    }//GEN-LAST:event_inombre_usuarioMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked

    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Inicio de Sesion

        String nombre = this.inombre_usuario.getText().trim();
        String password = new String(this.i_password.getPassword());
        Usuario u = null;
        if (!nombre.equals("") && !password.equals("")) {

            u = MainInicio.validarUsuario(nombre, password);
            if (u != null) {
                tipo_usuario = u.getTipo();
                usuario_iniciado = u.getNombre();
                System.out.println("Si existe el Usuario: " + tipo_usuario);
                Menu_principal Menu = new Menu_principal();
                Menu.setVisible(true);

                
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "No existe el Usuario");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void inombre_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inombre_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inombre_usuarioActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField i_password;
    private javax.swing.JTextField inombre_usuario;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel panel_config1;
    // End of variables declaration//GEN-END:variables
}
