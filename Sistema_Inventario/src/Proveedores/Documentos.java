/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proveedores;

import ConexionBDD.Timer1;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import static Proveedores.Gestion_Proveedor.codigo_proveedor;
import static Proveedores.Gestion_Proveedor.nombre_proveedor;
import static Proveedores.proveedoresx.proveedor_scroll;
import insumos.Prueba;
import java.awt.Cursor;
import static java.awt.Cursor.HAND_CURSOR;
import java.awt.event.ItemEvent;
import static sistema_inventario.Menu_principal.pantalla1;

/**
 *
 * @author windows
 */
public class Documentos extends javax.swing.JPanel {

    /**
     * Creates new form Documentos
     */
    int codigo = 0;
    int codigo_archivo = 0;
    String ruta_archivo = "";
    String fecha_aux = "";
    private String nombre_p = "";
    
    private Timer1 t = new Timer1();
    
    ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
    byte[] almacenamiento_archivo = null;
    public Documentos() {
        initComponents();
        
        codigo = codigo_proveedor;
        imprimirArchivos("WHERE fk_proveedor is null and estado_doc = 1");
        this.c_busqueda.addItem("Sin Proveedor");
        llenarCombo();
        
        this.c_todos.setSelected(true);
        
        
    }
    
    private void llenarCombo(){
        proveedores = MainProveedores.comprobarProveedor();
        Proveedor p = new Proveedor();
        for(int i = 0; i < proveedores.size(); i++){
            p = proveedores.get(i);
            combo_proveedor.addItem(p.getCodigo()+">"+p.getEmpresa());
            c_busqueda.addItem(p.getCodigo() + ">" + p.getEmpresa());
        }
       
    }
    
    
    
    private void imprimirArchivos(String consulta){
        ArrayList<Documento> documentos = new ArrayList<Documento>();
        Documento d = new Documento();
        
        documentos = MainProveedores.listaDocumentos(consulta);        
        this.encontrados.setText("Documentos Encontrados: " + documentos.size());
        
        for(int i = 0; i < documentos.size(); i++){
            d = documentos.get(i);
            JLabel label = new JLabel();
            label.setText(d.getNombre());
            label.setFont(new Font("Consolar", Font.PLAIN, 24));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setOpaque(true);
            label.setBackground(new Color(199,0,49));
            label.setForeground(Color.white);
            
            JLabel btn_pdf = new JLabel();
            btn_pdf.setText("   PDF   ");
            btn_pdf.setFont(new Font("Consolas", Font.PLAIN, 24));
            btn_pdf.setHorizontalAlignment(SwingConstants.CENTER);
            btn_pdf.setOpaque(true);
            btn_pdf.setBackground(new Color(29,45,80));
            btn_pdf.setForeground(Color.white);
            
            JButton btn_actualizar = new JButton();
            btn_actualizar.setText("  Actualizar  ");
            btn_actualizar.setFont(new Font("Consolar", Font.PLAIN, 24));
            
            JButton btn_eliminar = new JButton();
            btn_eliminar.setText("  Eliminar  ");
            btn_eliminar.setFont(new Font("Consolas", Font.PLAIN, 24));
            
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            panel.setBackground(new Color(29,45,80));
            
            GridBagConstraints g = new GridBagConstraints();
            
            g.gridx = 0;
            g.gridy = 0;
            g.gridwidth = 2;
            g.gridheight = 1;
            g.weightx = 1.0;
            g.weighty = 1.0;
            g.fill = GridBagConstraints.BOTH;
            panel.add(label, g);
            
            g.gridx = 3;
            g.gridy = 0;
            g.gridwidth = 1;
            g.gridheight = 1;
            g.weightx = 0.0;
            panel.add(btn_pdf, g);
            
            g.gridx = 4;
            g.gridy = 0;
            g.gridwidth = 1;
            g.gridheight = 1;     
            g.weightx = 0.0;
            panel.add(btn_actualizar, g);
            
            g.gridx = 5;
            g.gridy = 0;
            g.gridwidth = 1;
            g.gridheight = 1;
            g.weightx = 0.0;
            panel.add(btn_eliminar, g);
            
            jPanel1.add(panel);
            int archivo_aux = d.getCodigo();
            String nombre_aux = d.getNombre();
            String fecha_auxiliar = d.getFecha();
            byte[] documento_aux = d.getArchivo();
            int codigo_prov = d.getFk_proveedor();
            btn_eliminar.setCursor(new Cursor(HAND_CURSOR));
            btn_actualizar.setCursor(new Cursor(HAND_CURSOR));
            btn_pdf.setCursor(new Cursor(HAND_CURSOR));
            
            btn_eliminar.addMouseListener(new MouseListener(){
                @Override
                public void mouseClicked(MouseEvent e) { 
                    codigo_archivo = archivo_aux;
                    jDialog3.setBounds(0,0,400,230);
                    jDialog3.setLocationRelativeTo(null);
                    jDialog3.setResizable(false);
                    
                    jDialog3.setVisible(true);
                    
                }
                @Override
                public void mousePressed(MouseEvent e) {                 
                }
                @Override
                public void mouseReleased(MouseEvent e) {                 
                }
                @Override
                public void mouseEntered(MouseEvent e) {                 
                }
                @Override
                public void mouseExited(MouseEvent e) {                 
                }
            });
            btn_actualizar.addMouseListener(new MouseListener(){
                @Override
                public void mouseClicked(MouseEvent e) {   
                    ruta_archivo = "";
                    codigo_archivo = archivo_aux;
                    jDialog2.setBounds(0,0,400,400);
                    jDialog2.setLocationRelativeTo(null);
                    jDialog2.setResizable(false);
                                      
                    actualizar_codigo.setText(codigo_archivo + "");
                    actualizar_nombre.setText(nombre_aux);
                    actualizar_fecha.setText(fecha_auxiliar);
                    
                       
                    
                   
                    almacenamiento_archivo = documento_aux;
                    
                    jDialog2.setVisible(true);
                  
                }
                @Override
                public void mousePressed(MouseEvent e) {                    
                }
                @Override
                public void mouseReleased(MouseEvent e) {                    
                }
                @Override
                public void mouseEntered(MouseEvent e) {                    
                }
                @Override
                public void mouseExited(MouseEvent e) {                 
                }
            });
            
            btn_pdf.addMouseListener(new MouseListener(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    codigo_archivo = archivo_aux;
                        System.out.println("Codigo archivo: " + codigo_archivo);
                        if (btn_pdf.getText().equals("PDFfsds")) {
                            JOptionPane.showMessageDialog(null, "No hay archivo");
                        } else {
                            MainProveedores.ejecutarPdf(codigo_archivo);
                            try {
                                Desktop.getDesktop().open(new File("new.pdf"));
                            } catch (Exception ex) {
                            }
                        }
                }
                @Override
                public void mousePressed(MouseEvent e) {                 
                }
                @Override
                public void mouseReleased(MouseEvent e) {                    
                }
                @Override
                public void mouseEntered(MouseEvent e) {    
                    btn_pdf.setBackground(Color.red);
                }
                @Override
                public void mouseExited(MouseEvent e) {  
                    btn_pdf.setBackground(new Color(29,45,80));
                }
            });
            
        }
        
    }

    private void conseguirFecha(){
        Date fecha_defecto = new Date();
        System.out.println(fecha_defecto);
        //String formato_fecha = "hh:mm:ss a dd-MM-yyyy";
        String formato_fecha = "dd-MM-yyyy";
        SimpleDateFormat formato = new SimpleDateFormat(formato_fecha);
        System.out.println(formato.format(fecha_defecto));
        fecha_aux = formato.format(fecha_defecto);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog(pantalla1, "Registrar Nuevo Archivo", true);
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        archivo_nombre = new javax.swing.JTextField();
        archivo_fecha = new javax.swing.JTextField();
        jDialog2 = new javax.swing.JDialog(pantalla1, "Actualizar Archivo", true);
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        actualizar_nombre = new javax.swing.JTextField();
        actualizar_fecha = new javax.swing.JTextField();
        actualizar_codigo = new javax.swing.JLabel();
        btn_actualizar_archivo = new javax.swing.JButton();
        btn_cancelar_actualizacion = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        combo_proveedor = new javax.swing.JComboBox<>();
        jDialog3 = new javax.swing.JDialog(pantalla1, "Eliminar Archivo", true);
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        scroll_documentos = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        encontrados = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_busqueda = new javax.swing.JTextField();
        btn_buscar = new javax.swing.JButton();
        c_busqueda = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        c_todos = new javax.swing.JCheckBox();

        jDialog1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nuevo Archivo");
        jDialog1.getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 200, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nombre: ");
        jDialog1.getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 70, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Fecha:");
        jDialog1.getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Archivo:");
        jDialog1.getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, -1, -1));

        jButton2.setText("Seleccionar Archivo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, -1, -1));

        jButton3.setText("Guardar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 90, 30));

        jButton4.setText("Cancelar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, 100, 30));
        jDialog1.getContentPane().add(archivo_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 190, 30));
        jDialog1.getContentPane().add(archivo_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 190, 30));

        jDialog2.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Actualizar Archivo");
        jDialog2.getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Codigo:");
        jDialog2.getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Nombre:");
        jDialog2.getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Fecha:");
        jDialog2.getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Archivo:");
        jDialog2.getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jButton6.setText("Seleccionar Archivo");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jDialog2.getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, -1, -1));
        jDialog2.getContentPane().add(actualizar_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 220, -1));
        jDialog2.getContentPane().add(actualizar_fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 220, -1));

        actualizar_codigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        actualizar_codigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        actualizar_codigo.setText("Codigo del archivo");
        jDialog2.getContentPane().add(actualizar_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, -1, -1));

        btn_actualizar_archivo.setText("Actualizar");
        btn_actualizar_archivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizar_archivoActionPerformed(evt);
            }
        });
        jDialog2.getContentPane().add(btn_actualizar_archivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, -1, -1));

        btn_cancelar_actualizacion.setText("Cancelar");
        btn_cancelar_actualizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar_actualizacionActionPerformed(evt);
            }
        });
        jDialog2.getContentPane().add(btn_cancelar_actualizacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Proveedor:");
        jDialog2.getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        combo_proveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un proveedor" }));
        jDialog2.getContentPane().add(combo_proveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 190, -1));

        jDialog3.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel12.setText("Eliminar Archivo");
        jDialog3.getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 210, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Esta seguro que quiere Eliminar este Archivo?");
        jDialog3.getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 320, 60));

        jButton7.setText("SI");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jDialog3.getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 70, 40));

        jButton8.setText("NO");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jDialog3.getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 70, 40));

        setBackground(new java.awt.Color(252, 249, 249));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(252, 249, 249));
        jPanel1.setPreferredSize(new java.awt.Dimension(0, 0));
        jPanel1.setLayout(new java.awt.GridLayout(0, 1, 0, 10));
        scroll_documentos.setViewportView(jPanel1);

        add(scroll_documentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 960, 370));

        jButton1.setBackground(new java.awt.Color(29, 45, 80));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Nuevo Documento");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, 190, 50));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Documentos");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 200, 50));

        encontrados.setText("jLabel14");
        add(encontrados, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 230, 20));

        jLabel14.setText("Busqueda por Título");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 140, -1));
        add(txt_busqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 160, -1));

        btn_buscar.setBackground(new java.awt.Color(29, 45, 80));
        btn_buscar.setForeground(new java.awt.Color(255, 255, 255));
        btn_buscar.setText("Buscar");
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });
        add(btn_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, -1, -1));

        c_busqueda.setBackground(new java.awt.Color(252, 249, 249));
        c_busqueda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                c_busquedaItemStateChanged(evt);
            }
        });
        add(c_busqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 200, -1));

        jLabel15.setText("Busqueda por Proveedor");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 4, 150, 20));

        c_todos.setText("Documentos Generales");
        c_todos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_todosActionPerformed(evt);
            }
        });
        add(c_todos, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 180, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        conseguirFecha();
        this.archivo_fecha.setText(fecha_aux);

        this.jDialog1.setBounds(0,0,400,400);
        this.jDialog1.setLocationRelativeTo(null);
        this.jDialog1.setResizable(false);
        this.jDialog1.setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        JFileChooser j = new JFileChooser();
        FileNameExtensionFilter f = new FileNameExtensionFilter("pdf", "pdf");
        j.setFileFilter(f);
        int s = j.showOpenDialog(this);
        if(s == 0){
            this.archivo_nombre.setText("" + j.getSelectedFile().getName());
            ruta_archivo = j.getSelectedFile().getAbsolutePath();
            System.out.println(ruta_archivo);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:

        String nombre = this.archivo_nombre.getText().trim();
        String fecha = this.archivo_fecha.getText().toString();

        if (!ruta_archivo.equals("")) {

            File ruta = new File(ruta_archivo);
            Documento d = new Documento();
            d.setNombre(nombre);
            d.setFecha(fecha);
            try {
                byte[] pdf = new byte[(int) ruta.length()];
                InputStream input = new FileInputStream(ruta);
                input.read(pdf);
                d.setArchivo(pdf);
            } catch (IOException ex) {
                d.setArchivo(null);
            }
            d.setEstado(true);
            d.setFk_proveedor(codigo);
          
            MainProveedores.guardarPdf2(d);
           
            this.jDialog1.dispose();
            Documentos doc = new Documentos();
            proveedor_scroll.setViewportView(doc);
            Prueba pru = new Prueba("PDF Guardado Exitosamente!");
            pru.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un Archivo");
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.jDialog1.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        JFileChooser j = new JFileChooser();
        FileNameExtensionFilter f = new FileNameExtensionFilter("pdf", "pdf");
        j.setFileFilter(f);
        int s = j.showOpenDialog(this);
        if(s == 0){
            this.actualizar_nombre.setText("" + j.getSelectedFile().getName());
            ruta_archivo = j.getSelectedFile().getAbsolutePath();
            System.out.println(ruta_archivo);
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void btn_actualizar_archivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizar_archivoActionPerformed

        // MEotod para ACtualizar

        String codigo_string = this.actualizar_codigo.getText().trim();
        int codigo_p = Integer.parseInt(codigo_string);
        System.out.println("codigo: " + codigo_p);
        String nombre = this.actualizar_nombre.getText().trim();
        String fecha = this.actualizar_fecha.getText().toString();
        
        String[] datos = this.combo_proveedor.getSelectedItem().toString().split(">");
        
        String cproveedor_string = datos[0];
        int cproveedor = 0;
        if(this.combo_proveedor.getSelectedIndex() != 0){
            cproveedor = Integer.parseInt(cproveedor_string);
        }
        
        

        if (almacenamiento_archivo != null) {

            Documento d = new Documento();
            d.setCodigo(codigo_p);
            d.setNombre(nombre);
            d.setFecha(fecha);
            if (!ruta_archivo.equals("")) {
                File ruta = new File(ruta_archivo);
                try {
                    byte[] pdf = new byte[(int) ruta.length()];
                    InputStream input = new FileInputStream(ruta);
                    input.read(pdf);
                    d.setArchivo(pdf);
                } catch (IOException ex) {
                    d.setArchivo(null);
                }

            }else{
                d.setArchivo(almacenamiento_archivo);
            }
            d.setFk_proveedor(cproveedor);
            if(this.combo_proveedor.getSelectedIndex() != 0){
               
                MainProveedores.actualizarPdf(d);
               
            }else{
               
                MainProveedores.actualizarPdf2(d);
               
            }
            
            this.jDialog1.dispose();
            Prueba pru = new Prueba("<html>"+"PDF Actualizado <br> Correctamente!"+"</html>");
            pru.setVisible(true);

        }else{
            System.out.println("El archivo esta nulo");
        }
        jDialog2.dispose();
        
        Documentos d = new Documentos();
        proveedor_scroll.setViewportView(d);
        
    }//GEN-LAST:event_btn_actualizar_archivoActionPerformed

    private void btn_cancelar_actualizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar_actualizacionActionPerformed
        // TODO add your handling code here:
        this.jDialog2.dispose();
    }//GEN-LAST:event_btn_cancelar_actualizacionActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        System.out.println("Cosigo enivaaod par eliminar: "+ codigo_archivo);
        
        MainProveedores.eliminarPdf(codigo_archivo);
        
        jDialog3.dispose();
        Documentos d = new Documentos();
        proveedor_scroll.setViewportView(d);
        Prueba pru = new Prueba("<html>"+" Se elimino el PDF <br> correctamente!"+"</html>");
        pru.setVisible(true);
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        this.jDialog3.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
        // TODO add your handling code here:
        String consulta = this.txt_busqueda.getText().toString().trim();
        
        if(!consulta.isEmpty()){
            this.jPanel1.removeAll();
            imprimirArchivos("WHERE estado_doc = 1 and nombre_doc LIKE '%"+ consulta +"%'");
            this.jPanel1.updateUI();
            this.c_todos.setSelected(false);
        }else{
            JOptionPane.showMessageDialog(null, "Debe ingresar el Título de algún Documento");
        }
    }//GEN-LAST:event_btn_buscarActionPerformed

    private void c_todosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_todosActionPerformed
        // TODO add your handling code here:
        if(this.c_todos.isSelected() == true){
            jPanel1.removeAll();
            imprimirArchivos("WHERE fk_proveedor is null and estado_doc = 1");
            jPanel1.updateUI();
            if(this.c_busqueda.getSelectedIndex() != 0){
                this.c_busqueda.setSelectedIndex(0);
               
            }  
            this.txt_busqueda.setText("");
        }else{
            System.out.println("NADA");
        }
    }//GEN-LAST:event_c_todosActionPerformed

    private void c_busquedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_c_busquedaItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {

            String aux = c_busqueda.getSelectedItem().toString();
            String[] datos = aux.split(">");
            
            if (c_busqueda.getSelectedItem().toString().equals("Sin Proveedor")) {
                jPanel1.removeAll();
                imprimirArchivos("WHERE fk_proveedor is null and estado_doc = 1");
                jPanel1.updateUI();
                combo_proveedor.setSelectedIndex(0);
            } else {
               int codigo_p = Integer.parseInt(datos[0]);
               jPanel1.removeAll();
               imprimirArchivos("WHERE fk_proveedor = "+codigo_p+" and estado_doc = 1");
               jPanel1.updateUI();
               nombre_p = datos[1];
               combo_proveedor.setSelectedItem(codigo_p+">"+nombre_p);

            }
            
           

        }
    }//GEN-LAST:event_c_busquedaItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel actualizar_codigo;
    private javax.swing.JTextField actualizar_fecha;
    private javax.swing.JTextField actualizar_nombre;
    private javax.swing.JTextField archivo_fecha;
    private javax.swing.JTextField archivo_nombre;
    private javax.swing.JButton btn_actualizar_archivo;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_cancelar_actualizacion;
    private javax.swing.JComboBox<String> c_busqueda;
    private javax.swing.JCheckBox c_todos;
    private javax.swing.JComboBox<String> combo_proveedor;
    private javax.swing.JLabel encontrados;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JDialog jDialog3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane scroll_documentos;
    private javax.swing.JTextField txt_busqueda;
    // End of variables declaration//GEN-END:variables
}
