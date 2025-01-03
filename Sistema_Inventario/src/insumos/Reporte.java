/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insumos;

import clases.Accion;
import clases.MainReportes;
import clases.Render;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import static insumos.Pantalla_1.InsumoScroll;
import sistema_inventario.Menu_principal;

/**
 *
 * @author windows
 */
public class Reporte extends javax.swing.JPanel {

    /**
     * Creates new form Reporte
     */
    String fecha = "";
    private ArrayList<String> arrayYears;
    private int currentYear = 0;
    
    public Reporte() {
        initComponents();
        setBounds(0, 0, 1050, 540);
        setPreferredSize(new Dimension(1030, 475));
        setBackground(new Color(252, 249, 249));
        
        this.combo_acciones.addItem("Nuevos Insumos");
        this.combo_acciones.addItem("Actualizaciones");        
        this.combo_acciones.addItem("Solo Ingresos");
        this.combo_acciones.addItem("Solo Retiros");
        this.combo_acciones.addItem("Eliminaciones");
        this.combo_acciones.addItem("Reactivacion Insumos");
        
        arrayYears = new ArrayList<>();
        for (int i=0; i < 10; i++) {
            arrayYears.add(String.valueOf(2020 + i));
            this.combo_year.addItem(String.valueOf(2020 + i));
        }
       
        
        imprimirTabla(tabla_reportes, "");
        
        
        combo_dias.setEnabled(false);
        llenarMeses();
        
     
        
        conseguirFecha();
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
        tabla_reportes = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        combo_acciones = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        combo_year = new javax.swing.JComboBox<>();
        combo_meses = new javax.swing.JComboBox<>();
        combo_dias = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Generar Reporte");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, -1));

        tabla_reportes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Producto", "Cantidad", "Descripcion", "Fecha", "Usuario", "prueba"
            }
        ));
        tabla_reportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(tabla_reportes);
        if (tabla_reportes.getColumnModel().getColumnCount() > 0) {
            tabla_reportes.getColumnModel().getColumn(5).setResizable(false);
        }

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 740, 220));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Generar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 90, 110, 80));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Ver Acciones:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, -1, -1));

        combo_acciones.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        combo_acciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas las Acciones" }));
        combo_acciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(combo_acciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, -1, -1));

        jButton2.setBackground(new java.awt.Color(252, 249, 249));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/atras.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 60, 70));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Seleccionar Fecha:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, -1, 20));

        combo_year.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        combo_year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Año" }));
        combo_year.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        combo_year.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_yearItemStateChanged(evt);
            }
        });
        add(combo_year, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, -1, -1));

        combo_meses.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        combo_meses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mes" }));
        combo_meses.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        combo_meses.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_mesesItemStateChanged(evt);
            }
        });
        add(combo_meses, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 110, -1, -1));

        combo_dias.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        combo_dias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dia" }));
        combo_dias.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(combo_dias, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 110, -1, -1));

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("Fecha de Hoy");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 150, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String dia = this.combo_dias.getSelectedItem().toString();
        String mes_string = this.combo_meses.getSelectedItem().toString();
        String year = this.combo_year.getSelectedItem().toString();
        
      
        String tipo_accion = this.combo_acciones.getSelectedItem().toString();
        String condicion = "";
       
        
        if(!year.equals("Año")){
            condicion = "WHERE year(fecha_accion) = " + year;
            if(!mes_string.equals("Mes")){                
                condicion += " and month(fecha_accion) = " + this.combo_meses.getSelectedIndex();
                if(!dia.equals("Dia")){
                    condicion += " and day(fecha_accion) = " + dia;                
                }          
            }
            establecerAccion(tipo_accion, condicion);
        }else{
            establecerAccion(tipo_accion, condicion);
        }
        
       
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void establecerAccion(String tipo_accion, String condicion){
        if (condicion.isEmpty()) {
            switch (tipo_accion) {
                case "Todas las Acciones":
                    imprimirTabla(tabla_reportes, "");
                    break;
                case "Nuevos Insumos":
                    imprimirTabla(tabla_reportes, "WHERE descripcion_accion = 'Ingreso Nuevo Producto'");
                   
                    break;
                case "Actualizaciones":
                    imprimirTabla(tabla_reportes, "WHERE descripcion_accion = 'Actualizacion de Insumo'");

                    break;
                case "Eliminaciones":
                    imprimirTabla(tabla_reportes, "WHERE descripcion_accion = 'Eliminacion de Insumo'");
                    break;
                case "Solo Ingresos":
                    imprimirTabla(tabla_reportes, "WHERE cantidad_accion > 0");
                    break;
                case "Solo Retiros":
                    imprimirTabla(tabla_reportes, "WHERE cantidad_accion < 0");
                    break;
                case "Reactivacion Insumos":
                    imprimirTabla(tabla_reportes, "WHERE descripcion_accion = 'Reactivacion de Insumo'");
                    break;
                default:
                    System.out.println("No se encontro coincidencias");
                    break;
            }
        }else{
            switch (tipo_accion) {
                case "Todas las Acciones":
                    imprimirTabla(tabla_reportes, condicion);
                    break;
                case "Nuevos Insumos":
                    imprimirTabla(tabla_reportes, condicion + " and descripcion_accion = 'Ingreso Nuevo Producto'");
                    
                    break;
                case "Actualizaciones":
                    imprimirTabla(tabla_reportes, condicion + " and descripcion_accion = 'Actualizacion de Insumo'");

                    break;
                case "Eliminaciones":
                    imprimirTabla(tabla_reportes, condicion + " and descripcion_accion = 'Eliminacion de Insumo'");
                    break;
                case "Solo Ingresos":
                    imprimirTabla(tabla_reportes, condicion + " and cantidad_accion > 0");
                    break;
                case "Solo Retiros":
                    imprimirTabla(tabla_reportes, condicion + " and cantidad_accion < 0");
                    break;
                case "Reactivacion Insumos":
                    imprimirTabla(tabla_reportes, condicion + " and descripcion_accion = 'Reactivacion de Insumo'");
                    break;
                default:
                    System.out.println("No se encontro coincidencias");
                    break;
            }
        }
        
    }
    
    private void llenarMeses(){
        String meses[] = {"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre",
        "Octubre","Noviembre","Diciembre"};
        
        for(int i = 0; i < meses.length; i++){
            combo_meses.addItem(meses[i]);
        }
        
    }
    
    private void llenarDias(String mes, int year){               
        int dias[];
        int cont = 0;
        combo_dias.setEnabled(true);
        combo_dias.removeAllItems();
        combo_dias.addItem("Dia");
        boolean isLeapYear = (currentYear % 4 == 0 && currentYear % 100 != 0 ) || currentYear % 400 == 0;
        switch(mes){
            case "Enero":
                cont = 31;             
                break;
            case "Febrero":
                cont = isLeapYear ? 29 : 28;
                break;
            case "Marzo":
                cont = 31;
                
                break;
            case "Abril":
                cont = 30;               
                break;
            case "Mayo":
                cont = 31;                
                break;
            case "Junio":
                cont = 30;
                
                break;
            case "Julio":
                cont = 31;
                
                break;
            case "Agosto":
                cont = 31;
                
                break;
            case "Septiembre":
                cont = 30;
                
                break;
            case "Octubre":
                cont = 31;
                
                break;
            case "Noviembre":
                cont = 30;
                
                break;
            case "Diciembre":
                cont = 31;
                
                break;       
        }    
        for(int j = 0; j < cont; j++){
            this.combo_dias.addItem(String.valueOf(j+1));
        }
        
    }
    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        Gestion_Reportes g = new Gestion_Reportes();
        InsumoScroll.remove(this);
        InsumoScroll.setViewportView(g);
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void combo_mesesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_mesesItemStateChanged
        // TODO add your handling code here:
        
        if(evt.getStateChange() == ItemEvent.SELECTED){
            String aux = evt.getItem().toString();
            if(!evt.getItem().toString().equals("Mes") && !this.combo_year.getSelectedItem().toString().equals("Año")){
                String y_string = this.combo_year.getSelectedItem().toString();
                int y = Integer.parseInt(y_string);
                llenarDias(aux, y);
            
            
            }else{
                combo_dias.removeAllItems();
                combo_dias.addItem("Dia");
            }
        }
        
    }//GEN-LAST:event_combo_mesesItemStateChanged

    private void combo_yearItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_yearItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.combo_meses.setSelectedItem("Mes");
            currentYear = Integer.valueOf(evt.getItem().toString());
        }
    }//GEN-LAST:event_combo_yearItemStateChanged

    private void conseguirFecha(){
        Date fecha_defecto = new Date();
        System.out.println(fecha_defecto);
        //String formato_fecha = "hh:mm:ss a dd-MM-yyyy";
        String formato_fecha = "dd-MM-yyyy";
        SimpleDateFormat formato = new SimpleDateFormat(formato_fecha);
        System.out.println(formato.format(fecha_defecto));
        fecha = formato.format(fecha_defecto);
        
    }
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
       
        String[] fecha_aux = fecha.split("-");
        String aux_year = fecha_aux[2];
        String aux_mes = fecha_aux[1];
        String aux_dia = fecha_aux[0];
        
        if (arrayYears.contains(aux_year)) {
            this.combo_year.setSelectedItem(aux_year);
        } else {
            arrayYears.add(aux_year);
            this.combo_year.addItem(aux_year);
            this.combo_year.setSelectedItem(aux_year);
        }
        
        this.combo_meses.setSelectedIndex(Integer.parseInt(aux_mes));
        this.combo_dias.setSelectedIndex(Integer.parseInt(aux_dia));
    }//GEN-LAST:event_jButton3ActionPerformed

    private void imprimirTabla(JTable tabla, String sql){
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        modelo.addColumn("Codigo");
        modelo.addColumn("Producto");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Fecha");
        modelo.addColumn("Usuario xd");
        
        if(this.combo_acciones.getSelectedIndex() == 3 || this.combo_acciones.getSelectedIndex() == 4){
            modelo.addColumn("Documento");
        }
  
        Object[] fila = new Object[modelo.getColumnCount()];
        
        ArrayList<Accion> acciones = new ArrayList<Accion>();
        Accion a = new Accion();
        System.out.println("LA CONSULTA ES: " + sql);
        acciones = MainReportes.consultarReportes(sql);
        System.out.println("LOS RESULTADOS ENCONTRADOS: " + acciones.size());
        for(int i = 0; i < acciones.size(); i++){
            a = acciones.get(i);
            
            fila[0] = a.getId();
            fila[1] = a.getProducto();
            fila[2] = a.getCantidad();
            fila[3] = a.getDescripcion();
            fila[4] = a.getFecha();
            fila[5] = a.getUsuario();
            if(this.combo_acciones.getSelectedIndex() == 3 || this.combo_acciones.getSelectedIndex() == 4){
                fila[6] = a.getFactura();
            }
            
      
            
            modelo.addRow(fila);
            
            tabla.setModel(modelo);
            tabla.setRowHeight(30);
            
        }
        if(acciones.size() == 0){
           limpiarTabla();
        }
        
        
        editarTabla();
    }
    
    private void limpiarTabla(){
        DefaultTableModel modelo = (DefaultTableModel) this.tabla_reportes.getModel();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
        
    }
    
    private void editarTabla(){
        TableColumnModel columna = tabla_reportes.getColumnModel();
        columna.getColumn(0).setPreferredWidth(30);
        columna.getColumn(1).setPreferredWidth(100);
        columna.getColumn(2).setPreferredWidth(100);
        columna.getColumn(3).setPreferredWidth(200);
        columna.getColumn(4).setPreferredWidth(100);
        columna.getColumn(5).setPreferredWidth(100);
        if(this.combo_acciones.getSelectedIndex() == 3 || this.combo_acciones.getSelectedIndex() == 4){
            columna.getColumn(6).setPreferredWidth(200);
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combo_acciones;
    private javax.swing.JComboBox<String> combo_dias;
    private javax.swing.JComboBox<String> combo_meses;
    private javax.swing.JComboBox<String> combo_year;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_reportes;
    // End of variables declaration//GEN-END:variables
}
