/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insumos;

import ConexionBDD.Timer1;
import clases.Accion;
import clases.Insumo;
import clases.MainNotificacion;
import clases.MainTransaccion;
import clases.Notificacion;
import clases.Render;
import clases.RenderNotificacion;
import clases.RenderPrueba;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import static sistema_inventario.Inicio.usuario_iniciado;
import static insumos.Pantalla_1.InsumoScroll;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author windows
 */
public class ListaNotificaciones extends javax.swing.JPanel {

    /**
     * Creates new form ListaNotificaciones
     */
    
    private ButtonGroup bg;
    private int codigo;
    private String nombre = "";
    TableRowSorter trs;
    
    private Timer1 t = new Timer1();
    
    public ListaNotificaciones() {
        initComponents();
              
        setPreferredSize(new Dimension(1039,475));
        imprimirDatos(jTable1, "WHERE n.estado = 1 and i.estado = 1");
           
        this.c_todos.setSelected(true);
        
        
          
    }
    
    private void imprimirDatos(JTable tabla, String condicion){
        tabla.setDefaultRenderer(Object.class, new RenderPrueba());
        DefaultTableModel modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        modelo.addColumn("Codigo");
        modelo.addColumn("Insumo");
        modelo.addColumn("Cantidad Mínima");
        modelo.addColumn("Cantidad Preventiva");
        modelo.addColumn("Fecha de Creacion");
        modelo.addColumn("Cantidad Insumo");
        
        Object[] fila = new Object[modelo.getColumnCount()];
        ArrayList<Notificacion> notificaciones = new ArrayList<Notificacion>();
        Notificacion n = new Notificacion();
        notificaciones = MainNotificacion.listaNotificaciones(condicion);
        for (int i = 0; i < notificaciones.size(); i++) {
            n = notificaciones.get(i);
            if (n.getEstado() == true) {
                fila[0] = n.getId();
                fila[1] = n.getNombre_insumo();
                fila[2] = n.getCantidadBase();   
                fila[3] = n.getCantidadBase() + ((n.getCantidadBase() * 25) / 100);
                fila[4] = n.getF_creacion();
                fila[5] = n.getCantidad_insumo();
                modelo.addRow(fila);
            }
            
       
            tabla.setModel(modelo);
            tabla.setRowHeight(30);
        }
        
        if(notificaciones.size() == 0){
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
        
        this.jTable1.getColumnModel().getColumn(0).setPreferredWidth(5);
        
    }
    
    private void limpiar(){
        this.ni_codigo.setText("Codigo: "); 
        this.ni_fecha_c.setText("Fecha de Creacion: ");
        this.ni_insumo.setText("Insumo al que pertenece: ");
        this.ni_condicion.setText("");
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        ni_fecha_c = new javax.swing.JLabel();
        ni_insumo = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ni_actualizar = new javax.swing.JButton();
        ni_condicion = new javax.swing.JTextField();
        ni_codigo = new javax.swing.JLabel();
        edt_buscar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        c_todos = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(252, 249, 249));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Lista Notificaciones");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 290, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Insumo", "Cantidad Minima", "Cantidad Preventiva", "Fecha", "Cantidad Insumo"
            }
        ));
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 620, 320));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_insumos/aceptar1.png"))); // NOI18N
        jLabel2.setText("Click en la fila que desea editar informacion");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 310, -1));

        jPanel1.setBackground(new java.awt.Color(15, 76, 117));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Administrar Notificación");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 230, 40));

        ni_fecha_c.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ni_fecha_c.setForeground(new java.awt.Color(255, 255, 255));
        ni_fecha_c.setText("Fecha de Creacion: ");
        jPanel1.add(ni_fecha_c, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 300, 20));

        ni_insumo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ni_insumo.setForeground(new java.awt.Color(255, 255, 255));
        ni_insumo.setText("Insumo:");
        jPanel1.add(ni_insumo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 300, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("<html>"+"Cantidad <br> Mínima"+"</html>");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 90, 50));

        ni_actualizar.setBackground(new java.awt.Color(221, 44, 0));
        ni_actualizar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ni_actualizar.setForeground(new java.awt.Color(255, 255, 255));
        ni_actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_insumos/aceptar2.png"))); // NOI18N
        ni_actualizar.setText("Actualizar");
        ni_actualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ni_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ni_actualizarActionPerformed(evt);
            }
        });
        jPanel1.add(ni_actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 130, 50));
        jPanel1.add(ni_condicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 150, 30));

        ni_codigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ni_codigo.setForeground(new java.awt.Color(255, 255, 255));
        ni_codigo.setText("Codigo:");
        jPanel1.add(ni_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 90, 30));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 90, 360, 380));

        edt_buscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edt_buscar.setForeground(new java.awt.Color(153, 153, 153));
        edt_buscar.setText("Nombre del Insumo");
        edt_buscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                edt_buscarMouseClicked(evt);
            }
        });
        edt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                edt_buscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                edt_buscarKeyTyped(evt);
            }
        });
        add(edt_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 140, -1));

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, -1, -1));

        c_todos.setText("Mostrar Todos las Notificaciones");
        c_todos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_todosActionPerformed(evt);
            }
        });
        add(c_todos, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        DefaultTableModel modelo = (DefaultTableModel) this.jTable1.getModel();
        int sRow = this.jTable1.getSelectedRow();
     
        
        this.ni_codigo.setText("Codigo: " + modelo.getValueAt(sRow, 0).toString());
        String codigo_string = modelo.getValueAt(sRow, 0).toString();
        codigo = Integer.parseInt(codigo_string);
        
        this.ni_fecha_c.setText("Fecha de Creacion: " + modelo.getValueAt(sRow, 4).toString());
        this.ni_insumo.setText("Insumo al que pertenece: " + modelo.getValueAt(sRow, 1).toString());
        this.ni_condicion.setText(modelo.getValueAt(sRow, 2).toString());
        
        nombre = modelo.getValueAt(sRow, 1).toString();
              
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void ni_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ni_actualizarActionPerformed
        // TODO add your handling code here:
        String condicion_string = this.ni_condicion.getText();
        if (codigo != 0) {
            if (validarEntero(condicion_string)) {
                double condicion = Double.parseDouble(condicion_string);
                
                
                Notificacion n = new Notificacion();
                n.setId(codigo);
                n.setCantidadBase(condicion);             
                MainNotificacion.actuaizarNotificacion(n);
               
                /*
                Accion a = new Accion();
                a.setProducto(nombre);
                a.setDescripcion("Actualizacion de Notificacion");
                a.setCantidad(0.0);
                a.setUsuario(usuario_iniciado);
                MainTransaccion.registrarAccion(a, codigo);
                */
                imprimirDatos(jTable1, "WHERE n.estado = 1 and i.estado = 1");                              
                limpiar();
                
                
            } else {
                JOptionPane.showMessageDialog(null, "La condicion debe ser un Numero");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar alguna Notificacion");
        }
        
       
        
    }//GEN-LAST:event_ni_actualizarActionPerformed

    private void limpiarTabla(){
        DefaultTableModel modelo = (DefaultTableModel) this.jTable1.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        String busqueda = this.edt_buscar.getText().toString().trim();
        if(!busqueda.equals("Nombre del Insumo") && !busqueda.equals("")){
            imprimirDatos(jTable1, "WHERE i.nombre LIKE '%" + busqueda + "%' and n.estado = 1 and i.estado = 1");
            this.c_todos.setSelected(false);
        }else{
            JOptionPane.showMessageDialog(null, "Debe ingresar el Nombre del Insumo al que pertenece la notificación");
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void c_todosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_todosActionPerformed
        // TODO add your handling code here:
        if(this.c_todos.isSelected() == true){
            imprimirDatos(jTable1, "WHERE n.estado = 1 and i.estado = 1");
            this.edt_buscar.setText("Nombre del Insumo");
        }else{
            System.out.println("Nada");
        }
        
    }//GEN-LAST:event_c_todosActionPerformed

    private void edt_buscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edt_buscarMouseClicked
        // TODO add your handling code here:
        if(this.edt_buscar.getText().toString().equals("Nombre del Insumo")){
            this.edt_buscar.setText("");
        }
    }//GEN-LAST:event_edt_buscarMouseClicked

    private void edt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edt_buscarKeyReleased
        // TODO add your handling code here:
        
       
        
    }//GEN-LAST:event_edt_buscarKeyReleased

    private void edt_buscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edt_buscarKeyTyped
        // TODO add your handling code here:
//        DefaultTableModel dtm = (DefaultTableModel) this.jTable1.getModel();
//        edt_buscar.addKeyListener(new KeyAdapter(){
//            @Override
//            public void keyReleased(KeyEvent ke){
//                trs.setRowFilter(RowFilter.regexFilter("(?i)"+edt_buscar.getText().toString(), 1));
//            }
//            
//        });
//        
//        trs = new TableRowSorter(dtm);
//        jTable1.setRowSorter(trs);
        
        
    }//GEN-LAST:event_edt_buscarKeyTyped

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseEntered
    
    private boolean validarEntero(String x){
        try{
            Double.parseDouble(x);
            return true;
        }catch(Exception e){
            return false;
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox c_todos;
    private javax.swing.JTextField edt_buscar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton ni_actualizar;
    private javax.swing.JLabel ni_codigo;
    private javax.swing.JTextField ni_condicion;
    private javax.swing.JLabel ni_fecha_c;
    private javax.swing.JLabel ni_insumo;
    // End of variables declaration//GEN-END:variables

   
}