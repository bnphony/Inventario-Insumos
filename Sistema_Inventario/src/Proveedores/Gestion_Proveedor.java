/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proveedores;

import ConexionBDD.Timer1;
import Proveedores.Prov_Render;
import clases.MainNotificacion;
import clases.Notificacion;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static Proveedores.proveedoresx.proveedor_scroll;
import insumos.Prueba;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author windows
 */
public class Gestion_Proveedor extends javax.swing.JPanel {

    /**
     * Creates new form Gestion_Proveedor
     */
    public static int codigo_proveedor = 0;
    public static String nombre_proveedor = "";
    int id = -1;
    int codigo = 0;
    ImageIcon img_pdf = new ImageIcon(getClass().getResource("/Imagenes_insumos/pdf.png"));
    
    private Timer1 t = new Timer1();
    
    public Gestion_Proveedor() {
        initComponents();
        setPreferredSize(new Dimension(1020,520));
        
        imprimirDatos(jTable1, "WHERE estado_prov = 1");
        this.c_todos.setSelected(true);
        
       
    }
    
    private void imprimirDatos(JTable tabla, String consulta){
        tabla.setDefaultRenderer(Object.class, new Prov_Render());
        DefaultTableModel modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        modelo.addColumn("#");
        modelo.addColumn("Cedula");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Empresa");
        modelo.addColumn("Email");
        modelo.addColumn("Direccion");
        modelo.addColumn("Telefono");
        modelo.addColumn("Archivos");
        
        Object[] fila = new Object[modelo.getColumnCount()];
        ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
        Proveedor p = new Proveedor();
        proveedores = MainProveedores.listaProveedores(consulta);
        this.label_todos.setText("Proveedores encontrados: " + proveedores.size());
             
           
        for (int i = 0; i < proveedores.size(); i++) {
            p = proveedores.get(i);
            JButton btn_archivos = new JButton();
            btn_archivos.setContentAreaFilled(true);
            btn_archivos.setIcon(img_pdf);
            btn_archivos.setOpaque(true);
            
            btn_archivos.setBackground(new Color(255,42,20));

           
            
            int codigo_aux = p.getCodigo();
            fila[0] = p.getCodigo();
            fila[1] = p.getCedula();
            fila[2] = p.getNombres();
            fila[3] = p.getApellidos();
            fila[4] = p.getEmpresa();
            fila[5] = p.getEmail();
            fila[6] = p.getDireccion();
            fila[7] = p.getTelefono();
            fila[8] = btn_archivos;
            modelo.addRow(fila);

            tabla.setModel(modelo);
            tabla.setRowHeight(30);
            
            
        }
        
        if(proveedores.size() == 0){
            limpiarTabla();
        }
        
        alinearTabla();    
    }

     
    
    private void alinearTabla(){
        DefaultTableCellRenderer alinear = new DefaultTableCellRenderer();
        alinear.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        this.jTable1.getColumnModel().getColumn(0).setCellRenderer(alinear);
        this.jTable1.getColumnModel().getColumn(1).setCellRenderer(alinear);
        this.jTable1.getColumnModel().getColumn(2).setCellRenderer(alinear);
        this.jTable1.getColumnModel().getColumn(3).setCellRenderer(alinear);
        this.jTable1.getColumnModel().getColumn(4).setCellRenderer(alinear);
        this.jTable1.getColumnModel().getColumn(5).setCellRenderer(alinear);
        this.jTable1.getColumnModel().getColumn(6).setCellRenderer(alinear);
        this.jTable1.getColumnModel().getColumn(7).setCellRenderer(alinear);
        
        this.jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
        this.jTable1.getColumnModel().getColumn(1).setPreferredWidth(30);
        this.jTable1.getColumnModel().getColumn(2).setPreferredWidth(30);
        this.jTable1.getColumnModel().getColumn(3).setPreferredWidth(30);
        this.jTable1.getColumnModel().getColumn(4).setPreferredWidth(70);
        this.jTable1.getColumnModel().getColumn(5).setPreferredWidth(30);
        this.jTable1.getColumnModel().getColumn(6).setPreferredWidth(30);
        this.jTable1.getColumnModel().getColumn(7).setPreferredWidth(30);
        this.jTable1.getColumnModel().getColumn(8).setPreferredWidth(20);
        
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        p_busqueda = new javax.swing.JTextField();
        btn_buscar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        p_cedula = new javax.swing.JTextField();
        p_nombre = new javax.swing.JTextField();
        p_apellido = new javax.swing.JTextField();
        p_empresa = new javax.swing.JTextField();
        p_email = new javax.swing.JTextField();
        p_direccion = new javax.swing.JTextField();
        p_telefono = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        label_todos = new javax.swing.JLabel();
        c_todos = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(252, 249, 249));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Digite el Nombre de la Empresa");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 20));

        p_busqueda.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        p_busqueda.setForeground(new java.awt.Color(153, 153, 153));
        p_busqueda.setText("Nombre de una Empresa");
        p_busqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p_busquedaMouseClicked(evt);
            }
        });
        p_busqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_busquedaActionPerformed(evt);
            }
        });
        add(p_busqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 170, -1));

        btn_buscar.setBackground(new java.awt.Color(29, 45, 80));
        btn_buscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_buscar.setForeground(new java.awt.Color(255, 255, 255));
        btn_buscar.setText("Buscar");
        btn_buscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });
        add(btn_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Cedula", "Nombre", "Apellido", "Empresa", "Email", "Direccion", "Telefono", "Archivos"
            }
        ));
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 680, 380));

        jPanel2.setBackground(new java.awt.Color(252, 249, 249));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Gestion de Proveedor");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Cedula");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Nombres");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Apellidos");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Empresa");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Email");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Direccion");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Telefono");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        jButton2.setBackground(new java.awt.Color(29, 45, 80));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Actualizar");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 100, 30));

        jButton3.setBackground(new java.awt.Color(29, 45, 80));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Crer Nuevo");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, -1, 30));

        jButton5.setBackground(new java.awt.Color(199, 0, 49));
        jButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Eliminar");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, 100, 30));

        p_cedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                p_cedulaKeyTyped(evt);
            }
        });
        jPanel2.add(p_cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 210, -1));
        jPanel2.add(p_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 210, -1));
        jPanel2.add(p_apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 210, -1));
        jPanel2.add(p_empresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 210, -1));
        jPanel2.add(p_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 210, -1));
        jPanel2.add(p_direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 210, -1));
        jPanel2.add(p_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 210, -1));

        jButton1.setBackground(new java.awt.Color(29, 45, 80));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Nuevo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 100, 30));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 50, 320, 400));

        label_todos.setText("jLabel1");
        add(label_todos, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 230, -1));

        c_todos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        c_todos.setText("Mostrar Todos los Proveedores");
        c_todos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        c_todos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_todosActionPerformed(evt);
            }
        });
        add(c_todos, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int column = jTable1.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / jTable1.getRowHeight();
             
        if (row < jTable1.getRowCount() && row >= 0 && column < jTable1.getColumnCount() && column >= 0) {
            id = (int) jTable1.getValueAt(row, 0);
            
            Object value = jTable1.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton) value).doClick();
                JButton boton = (JButton) value;

                if (boton.getText().equals("Vacio")) {
                    JOptionPane.showMessageDialog(null, "No hay archivo");
                } else {
                    codigo_proveedor = id;
                    nombre_proveedor = jTable1.getValueAt(row, 4).toString();
                    Prov_Archivo p = new Prov_Archivo();
                    proveedor_scroll.setViewportView(p);
                    
                }

            } else {
                String name = "" + jTable1.getValueAt(row, 1);
                
            }
        }
        String codigo_aux = jTable1.getValueAt(row, 0).toString();
        codigo = Integer.parseInt(codigo_aux);
//        String nombre_aux = jTable1.getValueAt(row, 2).toString();
//        String[] nombre_array = nombre_aux.split(" ");
//        String nombre = nombre_array[0];
//        String apellido = nombre_array[1];
        this.p_cedula.setText(jTable1.getValueAt(row, 1).toString());
        this.p_nombre.setText(jTable1.getValueAt(row, 2).toString());
        this.p_apellido.setText(jTable1.getValueAt(row, 3).toString());
        this.p_empresa.setText(jTable1.getValueAt(row, 4).toString());
        this.p_email.setText(jTable1.getValueAt(row, 5).toString());
        this.p_direccion.setText(jTable1.getValueAt(row, 6).toString());
        this.p_telefono.setText(jTable1.getValueAt(row, 7).toString());
        
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void limpiarTabla(){
        DefaultTableModel modelo = (DefaultTableModel) this.jTable1.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
    }
    
    private void limpiar(){
        this.p_cedula.setText("");
        this.p_nombre.setText("");
        this.p_apellido.setText("");
        this.p_empresa.setText("");
        this.p_email.setText("");
        this.p_direccion.setText("");
        this.p_telefono.setText("");
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        String cedula = this.p_cedula.getText().toString().trim();
        String nombre = this.p_nombre.getText();
        String apellido = this.p_apellido.getText();
        String empresa = this.p_empresa.getText();
        String email = this.p_email.getText();
        String direccion = this.p_direccion.getText();
        String telefono = this.p_telefono.getText();
        
        if (!cedula.equals("") && !nombre.equals("") && !apellido.equals("") && !empresa.equals("")
                && !email.equals("") && !email.equals("") && !direccion.equals("") && !telefono.equals("")) {
            if (cedula.length() == 10) {
                if (validarCedula(cedula)) {
                    
                  
                    Proveedor p = new Proveedor();
                    p.setCedula(cedula);
                    p.setNombres(nombre);
                    p.setApellidos(apellido);
                    p.setEmpresa(empresa);
                    p.setEmail(email);
                    p.setDireccion(direccion);
                    p.setTelefono(telefono);
                    p.setEstado(true);

                    MainProveedores.insertarProveedor(p);
             
                    imprimirDatos(jTable1, "WHERE estado_prov = 1");
                    limpiar();

                } else {
                    JOptionPane.showMessageDialog(null, "El número de Cedula no es Valido");
                }

            }else{
                JOptionPane.showMessageDialog(null, "La Cedula debe constar de 10 dígitos");
            }
                     

        } else {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los Campos!");
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    
    private boolean validarCedula(String cedula){
        int c = 0, ultimo = 0; 
        int suma = 0;
        int acumulador = 0;
        int resta = 0;
        boolean valido = false;
        
        for(int i = 0; i < cedula.length()-1; i++){
            c = Integer.parseInt(cedula.charAt(i) + "");
            if(i%2 == 0){
                c = c * 2;
                if(c > 9){
                    c = c - 9;
                }
            }           
            suma = suma + c;
        }
        
        if(suma%10 != 0){
            acumulador = ((suma / 10) + 1) * 10;
            resta = acumulador - suma;
        }
        
        ultimo = Integer.parseInt(cedula.charAt(9) + "");
        if(ultimo == resta){
            valido = true;
        }
        
        return valido;
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (codigo != 0) {
            String cedula = this.p_cedula.getText().toString().trim();
            String nombre = this.p_nombre.getText();
            String apellido = this.p_apellido.getText();
            String empresa = this.p_empresa.getText();
            String email = this.p_email.getText();
            String direccion = this.p_direccion.getText();
            String telefono = this.p_telefono.getText();

            if (!cedula.equals("") && !nombre.equals("") && !apellido.equals("") && !empresa.equals("")
                    && !email.equals("") && !email.equals("") && !direccion.equals("") && !telefono.equals("")) {
                if (cedula.length() == 10) {
                    if (validarCedula(cedula)) {
                        
                   
                        Proveedor p = new Proveedor();
                        p.setCodigo(codigo);
                        p.setCedula(cedula);
                        p.setNombres(nombre);
                        p.setApellidos(apellido);
                        p.setEmpresa(empresa);
                        p.setEmail(email);
                        p.setDireccion(direccion);
                        p.setTelefono(telefono);

                        
                        MainProveedores.actualizarProveedor(p);
              
                        imprimirDatos(jTable1, "WHERE estado_prov = 1");
                        limpiar();
                    } else {
                        JOptionPane.showMessageDialog(null, "El número de Cedula no es Valido");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La Cedula debe constar de 10 dígitos");
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Debe llenar todos los Campos!");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar alguno Proveedor");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if(codigo != 0){
            int respuesta = JOptionPane.showConfirmDialog(null, "Esta seguro de Eliminar este Proveedor?",
                    "Confirmacin!",JOptionPane.YES_NO_OPTION);
            if(respuesta == 0){
                
                MainProveedores.cambiarEstadoProveedor(codigo, 0);
        
                imprimirDatos(jTable1, "WHERE estado_prov = 1");
                Prueba pru = new Prueba("<html>"+"Proveedor Eliminado <br> Correctamente!"+"</html>");
                pru.setVisible(true);
                limpiar();
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar algun Proveedor", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
        // TODO add your handling code here:
        String consulta = this.p_busqueda.getText().toString().trim();
        if(!consulta.equals("") && !consulta.equals("Nombre de una Empresa")){
            imprimirDatos(jTable1, "WHERE estado_prov = 1 and empresa_prov LIKE '%" + consulta + "%'");
            this.c_todos.setSelected(false);
        }else{
            JOptionPane.showMessageDialog(null, "Debe escribir una Consulta");
        }
        
    }//GEN-LAST:event_btn_buscarActionPerformed

    private void p_busquedaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p_busquedaMouseClicked
        // TODO add your handling code here:
        if(this.p_busqueda.getText().toString().equals("Nombre de una Empresa")){
            this.p_busqueda.setText("");
        }
    }//GEN-LAST:event_p_busquedaMouseClicked

    private void c_todosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_todosActionPerformed
        // TODO add your handling code here:
        if(this.c_todos.isSelected() == true){
            imprimirDatos(jTable1, "WHERE estado_prov = 1");
        }else{
            System.out.println("NADA");
        }
        
    }//GEN-LAST:event_c_todosActionPerformed

    private void p_busquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_busquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_busquedaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Component[] com = jPanel2.getComponents();
        for(int i = 0; i < com.length; i++){
            if(com[i] instanceof JTextField){
                JTextField txt = (JTextField) com[i];
                txt.setText("");
            }      
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void p_cedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_p_cedulaKeyTyped
        // TODO add your handling code here:
        if(p_cedula.getText().trim().length() >= 10){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_p_cedulaKeyTyped

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_buscar;
    private javax.swing.JCheckBox c_todos;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel label_todos;
    private javax.swing.JTextField p_apellido;
    private javax.swing.JTextField p_busqueda;
    private javax.swing.JTextField p_cedula;
    private javax.swing.JTextField p_direccion;
    private javax.swing.JTextField p_email;
    private javax.swing.JTextField p_empresa;
    private javax.swing.JTextField p_nombre;
    private javax.swing.JTextField p_telefono;
    // End of variables declaration//GEN-END:variables
}
