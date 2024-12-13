/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insumos;

import ConexionBDD.Timer1;
import Proveedores.Documento;
import Proveedores.MainProveedores;
import Proveedores.Proveedor;
import clases.Accion;
import clases.Insumo;
import clases.MainNotificacion;
import clases.MainReportes;
import clases.MainTransaccion;
import clases.Notificacion;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import static java.awt.Cursor.HAND_CURSOR;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import static java.lang.Math.abs;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import static sistema_inventario.Inicio.tipo_usuario;
import static sistema_inventario.Inicio.usuario_iniciado;
import static insumos.Pantalla_1.InsumoScroll;

import static insumos.Pantalla_1.codigo;
import static insumos.Pantalla_1.imagen;
import static insumos.Pantalla_1.texto;
import sistema_inventario.Menu_principal;
import insumos.Crear;
import java.awt.event.ItemEvent;
import insumos.Eliminados;
import insumos.Gestion_Reportes;
import insumos.Prueba;
import static java.lang.Thread.sleep;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import static sistema_inventario.Menu_principal.pantalla1;

/**
 *
 * @author windows
 */
public class ListaInsumos extends javax.swing.JPanel {

    /**
     * Creates new form ListaInsumos
     */
    public static String texto = "", imagen = ""; 
    public static int codigo = 0;
    
    private Timer1 t = new Timer1();
    
    
    Border border = BorderFactory.createLineBorder(Color.black, 1);
 
    boolean registrar = false;
    String[] fecha;
    Insumo in = new Insumo();
    
    int apuntador = 0;
    private double aux_c = 0;
    

    // Variables provenientes de Detalle
    String nombre_insumo = "", ruta_imagen = "";
    double cantidad_insumo = 0;
    int codigo_insumo = 1;
    double cantidad_referencia = 0;
    
    
    ArrayList<Insumo> aux_in;
    ArrayList<Documento> aux_ps;
    String[] documentos;
    
    public ListaInsumos() {
        initComponents();
        setBounds(0,0,1050,500);
        setPreferredSize(new Dimension(1039,475));
        
        t.iniciar1();
        listaInsumos("WHERE i.estado = 1");
        
        this.jDialog1.getContentPane().setBackground(new Color(252, 249, 249));
        this.jDialog2.getContentPane().setBackground(new Color(252, 249, 249));
        if(tipo_usuario.equals("Usuario")){
            
            btn_eliminar.setEnabled(false);
        }
        
        conseguirFecha();
        registrar = MainReportes.validarControl(fecha);
        if(!registrar){
            registroDiario();
        }else{
            System.out.println("YA SE REGISTRO POR EL DIA DE HOY");
        }
        
       this.combo_tipos.addItem("Unidades (PZA)");
       this.combo_tipos.addItem("Kilogramos (Kg)");
       this.combo_tipos.addItem("Gramos (g)");
       this.combo_tipos.addItem("Miligramos (mg)");
       this.combo_tipos.addItem("Litros (l)");
       this.combo_tipos.addItem("Paquetes (PAQ)");
       this.combo_tipos.addItem("Cajas");
       this.combo_tipos.addItem("Botellas");
       this.combo_tipos.addItem("Otro...");
       
       
       this.combo_opciones.addItem("Unidades (PZA)");
       this.combo_opciones.addItem("Kilogramos (Kg)");
       this.combo_opciones.addItem("Gramos (g)");
       this.combo_opciones.addItem("Miligramos (mg)");
       this.combo_opciones.addItem("Litros (l)");
       this.combo_opciones.addItem("Paquetes (PAQ)");
       this.combo_opciones.addItem("Cajas");
       this.combo_opciones.addItem("Botellas");
       this.combo_opciones.addItem("Otro...");
       
       this.edt_otro.setVisible(false);
        
       
       this.btn_actualizar.setEnabled(false);
        
       this.panel_detalle.setVisible(false);
       
       this.label_insumo.setText("<html>"+"Por favor, Seleccione un Insumo <br> para editar su información <br>"
               + "o registrar Cantidades"+"</html>");
        
       this.c_todos.setSelected(true);
       
       this.combo_tipos.setPreferredSize(new Dimension(90,20));
       
       aux_ps = MainProveedores.consultarDocumentos();
       this.c_recomendacion.addItem("");
       this.combo_d_retiros.addItem("");
       llenarDocumentos();
       
       this.c_recomendacion.setEditable(true);
       AutoCompleteDecorator.decorate(c_recomendacion);
       this.combo_d_retiros.setEditable(true);
       AutoCompleteDecorator.decorate(combo_d_retiros);
       
       t.pausar("Imprimir Insumos");
    }
    
    private void editarBotones(){
        
    }
    
    private void conseguirFecha(){
        Date fecha_defecto = new Date();
        System.out.println(fecha_defecto);
        //String formato_fecha = "hh:mm:ss a dd-MM-yyyy";
        String formato_fecha = "yyyy-MM-dd";
        SimpleDateFormat formato = new SimpleDateFormat(formato_fecha);
        System.out.println(formato.format(fecha_defecto));
        String fecha_aux = formato.format(fecha_defecto);
        fecha = fecha_aux.split("-");
        
    }
    
    private void registroDiario(){
        ArrayList<Insumo> insumos = new ArrayList<Insumo>();
        
        Insumo insumo = new Insumo();
        
        insumos = MainTransaccion.consultarInsumos("");
                
        for(int i = 0; i < insumos.size(); i++ ){
            insumo = insumos.get(i);
            if (insumo.getEstado() == true) {
                Accion a = new Accion();
                a.setProducto(insumo.getNombre());
                a.setDescripcion("Control Diario");
                a.setCantidad(0);
                a.setUsuario("No Necesario");
                a.setUltima_cantidad(insumo.getCantidad());
                a.setUltimo_precio(insumo.getPrecio());
                MainTransaccion.registrarAccion(a, insumo.getId());
            }
            
        }
    
    }
    private void ejecutarActualizar(Insumo i, int codigo){
        int cont = 0;
        try{
               this.di_imagen_insumo.setText("");
         
                byte[] imagen = i.getImagen();
                BufferedImage b = null;
                InputStream in = new ByteArrayInputStream(imagen);
                b = ImageIO.read(in);
                ImageIcon img = new ImageIcon(b.getScaledInstance(this.di_imagen_insumo.getWidth(), this.di_imagen_insumo.getHeight(), Image.SCALE_DEFAULT));             
                this.di_imagen_insumo.setIcon(img);
               
            }catch(Exception ex){
                this.di_imagen_insumo.setIcon(null);
                
                this.di_imagen_insumo.setText("No hay Imagen");
               
               
            }
        
        this.jLabel1.setText("Actualizar " + i.getNombre());
        this.jCodigo.setText("Codigo del Insumo : " + i.getId());
        this.di_nombre_insumo.setText(i.getNombre());
        
        BigDecimal cantidad = new BigDecimal(i.getCantidad());
       
        //Object[] seleccion = combo_tipos.getSelectedObjects();
        for(int j = 0; j < combo_tipos.getItemCount(); j++){
            if(combo_tipos.getItemAt(j).toString().equals(i.getTipo())){
                cont++;
            }  
            
        }
        if(cont == 0){            
            this.combo_tipos.setSelectedItem("Otro...");
            this.edt_otro.setText(i.getTipo());
        }else{
            this.combo_tipos.setSelectedItem(i.getTipo());
        }
        
       
        
        
        
        this.di_cantidad_insumo.setText(String.format("%.2f", cantidad));
        this.di_descripcion_insumo.setText(i.getDescripcion());
        this.di_precio_insumo.setText(i.getPrecio() + "");
        
        cantidad_insumo = i.getCantidad();
    }
    
    private void listaInsumos(String consulta){
        ArrayList<Insumo> insumos = new ArrayList<Insumo>();
        ArrayList<Notificacion> notificaciones = new ArrayList<Notificacion>();
        
        Insumo insumo = new Insumo();
        Font fuente = new Font("Arial", Font.BOLD,15);
        insumos = MainTransaccion.consultarInsumos2(consulta);
               
        aux_in = insumos;
        
        this.label_resultados.setText("Insumos Encontrados: " + insumos.size());
        for(int i = 0; i < insumos.size(); i++){
            insumo = insumos.get(i);         
           
            if (insumo.getEstado() == true) {
                //JLabel jImagen = new JLabel();
               // jImagen.setSize(125, 110);
                
                JLabel jNombre = new JLabel();
                //jNombre.setOpaque(true);
               // jNombre.setBorder(border);
               //jNombre.setPreferredSize(new Dimension(150, 30));
                //jNombre.setBackground(new Color(199,0,49));
                
                JLabel jEstado = new JLabel();
                jEstado.setText("   ");
                jEstado.setOpaque(true);
                //jEstado.setPreferredSize(new Dimension(10,10));
                
                double calculo = ((insumo.getCondicion()*25)/100);
                calculo = calculo + insumo.getCondicion();
                
                jNombre.setForeground(Color.black);
                jNombre.setFont(new Font("Consolas", Font.PLAIN, 16));
                jNombre.setText(insumo.getNombre());
                jNombre.setHorizontalAlignment(SwingConstants.CENTER);

                JPanel panel = new JPanel();
                //panel.setSize(130, 170);
               

                panel.setPreferredSize(new Dimension(150, 50));
                
                panel.setLayout(new GridBagLayout());
                panel.setBackground(new Color(252,249,249));
                
                if(insumo.getCantidad() < insumo.getCondicion()){
                    jEstado.setBackground(Color.red);
                }else if(insumo.getCantidad() <= calculo && insumo.getCantidad() >= insumo.getCondicion()){
                    jEstado.setBackground(Color.yellow);
                }else{
                    jEstado.setBackground(Color.green);
                }
                
                panel.setCursor(new Cursor(HAND_CURSOR));
                
                GridBagConstraints glayout = new GridBagConstraints();
                
                glayout.gridx = 0;
                glayout.gridy = 0;
                glayout.gridwidth = 1;
                glayout.weightx = 0.0;
                glayout.weighty = 1;
                glayout.fill = GridBagConstraints.BOTH;
                
                panel.add(jEstado, glayout);
                
               
                glayout.gridx = 1;
                glayout.gridy = 0;
                glayout.gridwidth = 5;
                glayout.weightx = 1;
                glayout.weighty = 1;
              
                
                panel.add(jNombre, glayout);
                
            
                
                int codigo_auxiliar = insumo.getId();
               
                this.panelBoton.add(panel);
                             
                Insumo i_auxiliar = insumo;
                
                
                
                panel.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (apuntador == 0) {
                            limpiarBotones();
                            panel.setBackground(new Color(199, 0, 49));
                            jNombre.setForeground(Color.white);
                            codigo_insumo = codigo_auxiliar;
                            in = i_auxiliar;
                            ejecutarActualizar(in, codigo_insumo);
                           
                            btn_actualizar.setEnabled(true);
                            apuntador = 1;
                            panel_detalle.setVisible(true);
                            label_insumo.setVisible(false);
                        }else{
                            panel.setBackground(new Color(252,249,249));
                            jNombre.setForeground(Color.black);
                            apuntador = 0;
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
                        panel.setBackground(new Color(199,0,49));
                        jNombre.setForeground(Color.white);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if(apuntador != 1){
                            panel.setBackground(new Color(252,249,249));
                            jNombre.setForeground(Color.black);
                           
                        }else{
                             apuntador = 0;
                        }                      
                    }
                });               
            }           
        }      
    }
    
    private void limpiarBotones(){
        Component[] com = panelBoton.getComponents();
        for(int i = 0; i < com.length; i++){
            if(com[i] instanceof JPanel){
                JPanel panel = (JPanel) com[i];
                panel.setBackground(new Color(252,249,249));
                JLabel label = (JLabel) panel.getComponent(1);
                label.setForeground(Color.black);               
            }           
            
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog(pantalla1, "Insertar Cantidad", true);
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        d1_text_insertar = new javax.swing.JTextField();
        d1_btn_insertar = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        c_recomendacion = new javax.swing.JComboBox<>();
        jDialog2 = new javax.swing.JDialog(pantalla1, "Retirar Cantidad", true);
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        d2_text_retirar = new javax.swing.JTextField();
        d2_btn_retirar = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        combo_d_retiros = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        panelBoton = new javax.swing.JPanel();
        edt_buscar = new javax.swing.JTextField();
        btn_buscar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        label_insumo = new javax.swing.JLabel();
        panel_detalle = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        di_imagen_insumo = new javax.swing.JLabel();
        btn_editar_imagen = new javax.swing.JButton();
        jCodigo = new javax.swing.JLabel();
        labelNombre = new javax.swing.JLabel();
        di_nombre_insumo = new javax.swing.JTextField();
        label_descripcion = new javax.swing.JLabel();
        di_precio_insumo = new javax.swing.JTextField();
        label_descripcion1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        di_descripcion_insumo = new javax.swing.JTextArea();
        di_cantidad_insumo = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        btn_actualizar = new javax.swing.JButton();
        label_descripcion2 = new javax.swing.JLabel();
        combo_tipos = new javax.swing.JComboBox<>();
        edt_otro = new javax.swing.JTextField();
        btn_eliminar = new javax.swing.JButton();
        label_resultados = new javax.swing.JLabel();
        combo_opciones = new javax.swing.JComboBox<>();
        c_todos = new javax.swing.JCheckBox();

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(5, 50, 50));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_insumos/arriba.png"))); // NOI18N
        jLabel4.setText("Insertar");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(5, 50, 50));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Cantidad a Insertar : ");

        d1_btn_insertar.setBackground(new java.awt.Color(29, 45, 80));
        d1_btn_insertar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        d1_btn_insertar.setForeground(new java.awt.Color(255, 255, 255));
        d1_btn_insertar.setText("Registrar Entradas");
        d1_btn_insertar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        d1_btn_insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d1_btn_insertarActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(199, 0, 49));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Cancelar");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Identificador/Factura:");

        c_recomendacion.setBackground(new java.awt.Color(252, 249, 249));
        c_recomendacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                c_recomendacionItemStateChanged(evt);
            }
        });
        c_recomendacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c_recomendacionMouseClicked(evt);
            }
        });
        c_recomendacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                c_recomendacionKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(c_recomendacion, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(d1_btn_insertar)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDialog1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(d1_text_insertar, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDialog1Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(c_recomendacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(d1_text_insertar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(d1_btn_insertar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(5, 50, 50));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_insumos/abajo.png"))); // NOI18N
        jLabel8.setText("Retirar");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(5, 50, 50));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Cantidad a Retirar : ");

        d2_text_retirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d2_text_retirarActionPerformed(evt);
            }
        });

        d2_btn_retirar.setBackground(new java.awt.Color(29, 45, 80));
        d2_btn_retirar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        d2_btn_retirar.setForeground(new java.awt.Color(255, 255, 255));
        d2_btn_retirar.setText("Registrar Salidas");
        d2_btn_retirar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        d2_btn_retirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d2_btn_retirarActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(199, 0, 49));
        jButton7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Cancelar");
        jButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        combo_d_retiros.setBackground(new java.awt.Color(252, 249, 249));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Identificador/Documento:");

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(d2_btn_retirar)
                .addGap(34, 34, 34)
                .addComponent(jButton7)
                .addGap(61, 61, 61))
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog2Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialog2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDialog2Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(d2_text_retirar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(combo_d_retiros, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(combo_d_retiros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(d2_text_retirar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(d2_btn_retirar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        setBackground(new java.awt.Color(252, 249, 249));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelBoton.setBackground(new java.awt.Color(15, 76, 117));
        panelBoton.setLayout(new java.awt.GridLayout(0, 1, 10, 10));
        jScrollPane2.setViewportView(panelBoton);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 337, 340));

        edt_buscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        edt_buscar.setForeground(new java.awt.Color(153, 153, 153));
        edt_buscar.setText("Nombre de Insumo");
        edt_buscar.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        edt_buscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                edt_buscarMouseClicked(evt);
            }
        });
        add(edt_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 200, 30));

        btn_buscar.setBackground(new java.awt.Color(29, 45, 80));
        btn_buscar.setForeground(new java.awt.Color(255, 255, 255));
        btn_buscar.setText("Buscar");
        btn_buscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });
        add(btn_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 70, 30));

        jPanel1.setMinimumSize(new java.awt.Dimension(600, 460));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 460));
        jPanel1.setLayout(new java.awt.CardLayout());

        label_insumo.setBackground(new java.awt.Color(255, 237, 193));
        label_insumo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        label_insumo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_insumo.setText("Por favor, Seleccione un Insumo");
        label_insumo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        label_insumo.setOpaque(true);
        jPanel1.add(label_insumo, "card2");

        panel_detalle.setBackground(new java.awt.Color(252, 249, 249));
        panel_detalle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Actualizar Insumo");

        di_imagen_insumo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        di_imagen_insumo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        di_imagen_insumo.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_editar_imagen.setBackground(new java.awt.Color(29, 45, 80));
        btn_editar_imagen.setForeground(new java.awt.Color(255, 255, 255));
        btn_editar_imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_insumos/upload.png"))); // NOI18N
        btn_editar_imagen.setText("Cambiar Imagen");
        btn_editar_imagen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_editar_imagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editar_imagenActionPerformed(evt);
            }
        });

        jCodigo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jCodigo.setText("Codigo del Insumo: ");

        labelNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelNombre.setForeground(new java.awt.Color(5, 50, 50));
        labelNombre.setText("Nombre:");

        di_nombre_insumo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        di_nombre_insumo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        label_descripcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label_descripcion.setForeground(new java.awt.Color(5, 50, 50));
        label_descripcion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_descripcion.setText("Precio Unitario");

        di_precio_insumo.setBackground(new java.awt.Color(252, 249, 249));
        di_precio_insumo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        di_precio_insumo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        label_descripcion1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label_descripcion1.setForeground(new java.awt.Color(5, 50, 50));
        label_descripcion1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_descripcion1.setText("<html>" + "Descripción <br> del Insumo" + "</html>");

        di_descripcion_insumo.setBackground(new java.awt.Color(252, 249, 249));
        di_descripcion_insumo.setColumns(20);
        di_descripcion_insumo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        di_descripcion_insumo.setLineWrap(true);
        di_descripcion_insumo.setTabSize(0);
        di_descripcion_insumo.setWrapStyleWord(true);
        di_descripcion_insumo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        di_descripcion_insumo.setMinimumSize(new java.awt.Dimension(1, 1));
        jScrollPane1.setViewportView(di_descripcion_insumo);

        di_cantidad_insumo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        di_cantidad_insumo.setForeground(new java.awt.Color(5, 50, 50));
        di_cantidad_insumo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        di_cantidad_insumo.setText("Cantidad Existente ");
        di_cantidad_insumo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton5.setBackground(new java.awt.Color(29, 45, 80));
        jButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_insumos/arriba.png"))); // NOI18N
        jButton5.setText("Ingresar");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(29, 45, 80));
        jButton8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_insumos/abajo.png"))); // NOI18N
        jButton8.setText("Retirar");
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        btn_actualizar.setBackground(new java.awt.Color(199, 0, 49));
        btn_actualizar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_actualizar.setForeground(new java.awt.Color(255, 255, 255));
        btn_actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_insumos/guardar.png"))); // NOI18N
        btn_actualizar.setText("<html>"+"Actualizar <br> Información"+"</html>");
        btn_actualizar.setToolTipText("");
        btn_actualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizarActionPerformed(evt);
            }
        });

        label_descripcion2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label_descripcion2.setForeground(new java.awt.Color(5, 50, 50));
        label_descripcion2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_descripcion2.setText("Cantidad");

        combo_tipos.setBackground(new java.awt.Color(252, 249, 249));
        combo_tipos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tipo Cantidad" }));
        combo_tipos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        combo_tipos.setMaximumSize(new java.awt.Dimension(91, 21));
        combo_tipos.setMinimumSize(new java.awt.Dimension(90, 20));
        combo_tipos.setPreferredSize(new java.awt.Dimension(90, 20));
        combo_tipos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_tiposItemStateChanged(evt);
            }
        });

        edt_otro.setText("Tipo de Unidad");
        edt_otro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                edt_otroMouseClicked(evt);
            }
        });
        edt_otro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edt_otroActionPerformed(evt);
            }
        });

        btn_eliminar.setBackground(new java.awt.Color(29, 45, 80));
        btn_eliminar.setForeground(new java.awt.Color(255, 255, 255));
        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes_insumos/cancel.png"))); // NOI18N
        btn_eliminar.setText("Eliminar Insumo");
        btn_eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_detalleLayout = new javax.swing.GroupLayout(panel_detalle);
        panel_detalle.setLayout(panel_detalleLayout);
        panel_detalleLayout.setHorizontalGroup(
            panel_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_detalleLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(panel_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel_detalleLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))
                    .addGroup(panel_detalleLayout.createSequentialGroup()
                        .addGroup(panel_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panel_detalleLayout.createSequentialGroup()
                                .addComponent(di_imagen_insumo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(panel_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_detalleLayout.createSequentialGroup()
                                        .addGroup(panel_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(label_descripcion)
                                            .addComponent(label_descripcion1)
                                            .addComponent(labelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panel_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                                            .addComponent(di_nombre_insumo)
                                            .addComponent(di_precio_insumo)))
                                    .addGroup(panel_detalleLayout.createSequentialGroup()
                                        .addComponent(jCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(panel_detalleLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btn_editar_imagen)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(64, 64, 64))))
            .addGroup(panel_detalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_descripcion2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(di_cantidad_insumo, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(combo_tipos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(edt_otro, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(panel_detalleLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
        panel_detalleLayout.setVerticalGroup(
            panel_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_detalleLayout.createSequentialGroup()
                .addGroup(panel_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_detalleLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(di_imagen_insumo, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_editar_imagen))
                    .addGroup(panel_detalleLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(panel_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(di_nombre_insumo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(panel_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(di_precio_insumo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label_descripcion1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(panel_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_detalleLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(panel_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_descripcion2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(di_cantidad_insumo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(118, 118, 118))
                    .addGroup(panel_detalleLayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addGroup(panel_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_detalleLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel_detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(combo_tipos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edt_otro, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))))
        );

        jPanel1.add(panel_detalle, "card3");

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 600, 470));

        label_resultados.setText("jLabel2");
        add(label_resultados, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 240, 20));

        combo_opciones.setBackground(new java.awt.Color(252, 249, 249));
        combo_opciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tipo de Cantidad" }));
        combo_opciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        combo_opciones.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                combo_opcionesItemStateChanged(evt);
            }
        });
        add(combo_opciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, -1, -1));

        c_todos.setText("Todos los Insumos");
        c_todos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        c_todos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_todosActionPerformed(evt);
            }
        });
        add(c_todos, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    
    private void establecerApariencia(){
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
    }
    private void restablecerApariencia(){
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        } 
    }
   
    
    private void d1_btn_insertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d1_btn_insertarActionPerformed
        // TODO add your handling code here:

        String insertar_string = this.d1_text_insertar.getText().toString();
        String precio_insumo = this.di_precio_insumo.getText().toString();
        String identificador = this.c_recomendacion.getSelectedItem().toString().trim();
        
        if (!identificador.isEmpty()) {           
            if (validarEntero(insertar_string) && validarEntero(precio_insumo)) {
                double insertar = Double.parseDouble(insertar_string);
                double precio = Double.parseDouble(precio_insumo);
                cantidad_insumo = cantidad_insumo + abs(insertar);
                System.out.println("Antes ingreso referencia_ " + cantidad_referencia);
                cantidad_referencia = abs(insertar);
                System.out.println("Despues ingreso referenia.> " + cantidad_referencia);
                BigDecimal cantidad1 = new BigDecimal(cantidad_insumo);
                this.jDialog1.dispose();
                this.di_cantidad_insumo.setText(cantidad1.setScale(2, RoundingMode.HALF_UP) + "");
                this.d1_text_insertar.setText("");
                    
                
                
                MainTransaccion.actualizarCantidad(codigo_insumo, cantidad_insumo, precio);
                
                
                String nombre = this.di_nombre_insumo.getText().toString();
                Accion a = new Accion();
                a.setProducto(nombre);
                a.setDescripcion("Ingreso");
                a.setCantidad(cantidad_referencia);
                a.setUsuario(usuario_iniciado);
                a.setUltima_cantidad(cantidad_insumo);
                a.setUltimo_precio(precio);
                a.setFactura(identificador);

                MainTransaccion.registrarAccion3(a, codigo_insumo);

                this.panelBoton.removeAll();
                listaInsumos("WHERE i.estado = 1");
                this.c_todos.setSelected(true);
                this.combo_opciones.setSelectedIndex(0);
                this.panelBoton.updateUI();

                comprobarNotificacion();                               
                
            } else {
                JOptionPane.showMessageDialog(null, "Debe Ingresar un Numero en la Cantidad");
            }
        }else{
            JOptionPane.showMessageDialog(null, "<html>"+"Debe ingresar un <strong>Identificador(Código de Factura)</strong><br> para la Transacción"+"</html>");
        }
        
    }//GEN-LAST:event_d1_btn_insertarActionPerformed

    private void comprobarNotificacion(){
            int[] colores = {255,78,39};
            ArrayList<Notificacion> notificaciones = new ArrayList<Notificacion>();
            notificaciones = MainTransaccion.consultarNotificaciones(codigo_insumo);
            String mensaje = "";
            if (notificaciones.isEmpty()) {
                System.out.println("Este Insumo NO Tiene Notificaciones");
            } else {
                Notificacion n = new Notificacion();
                for (int j = 0; j < notificaciones.size(); j++) {
                    n = notificaciones.get(j);            
                    if (cantidad_insumo <= n.getCantidadBase() && (n.getEstado() == true)) {
                        if(cantidad_insumo <= 0){
                            mensaje = "<html><p align='center'>" + "Se acabaron las <br> Unidades" + "</p></html>";
                        }else{
                            mensaje = "<html><p align='center'>" + "Se estan acabando las<br> Unidades" + "</p></html>";
                        }
                        System.out.println("Lanzar la notificacion");
                        //FrameNotificacion nt = new FrameNotificacion();
                        Prueba2 p = new Prueba2(mensaje, colores);
                    } else {
                        System.out.println("NO se cumple la condicion");
                    }
                }
            }
    }
    
    
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        this.jDialog1.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void d2_text_retirarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d2_text_retirarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_d2_text_retirarActionPerformed

    private void d2_btn_retirarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d2_btn_retirarActionPerformed
        // TODO add your handling code here:
        String retirar_string = this.d2_text_retirar.getText().toString();
       
        double precio = 0;
        String identificador = this.combo_d_retiros.getSelectedItem().toString().trim();
        System.out.println("Este es el identificador: ["+identificador+"]");
        if (validarEntero(retirar_string)) {
            double retirar = Double.parseDouble(retirar_string);
            cantidad_insumo = Math.round(cantidad_insumo * 100.0) / 100.0;
            if (retirar <= cantidad_insumo) {
                cantidad_insumo = cantidad_insumo - abs(retirar);
                System.out.println("Antes referencia> " + cantidad_referencia);
                cantidad_referencia = -abs(retirar);
                System.out.println("Cantidad Referencia : " + cantidad_referencia);
                BigDecimal cantidad = new BigDecimal(cantidad_insumo);
                this.jDialog2.dispose();
                this.di_cantidad_insumo.setText(cantidad.setScale(2, RoundingMode.HALF_UP) + "");
                this.d2_text_retirar.setText("");
                
               
                MainTransaccion.actualizarCantidad2(codigo_insumo, cantidad_insumo);
               
                
                precio = MainTransaccion.conseguirUltimoPrecio(codigo_insumo);
                String nombre = this.di_nombre_insumo.getText().toString();
                Accion a = new Accion();
                a.setProducto(nombre);
                a.setDescripcion("Retiro");
                a.setCantidad(cantidad_referencia);
                a.setUsuario(usuario_iniciado);
                a.setUltima_cantidad(cantidad_insumo);
                a.setUltimo_precio(precio);
                if(identificador.equals("")){
                    identificador = "Sin Documento";
                }
                a.setFactura(identificador);

                MainTransaccion.registrarAccion3(a, codigo_insumo);

                this.panelBoton.removeAll();
                listaInsumos("WHERE i.estado = 1");
                this.c_todos.setSelected(true);
                this.combo_opciones.setSelectedIndex(0);
                this.panelBoton.updateUI();

                comprobarNotificacion();

            }else{
                JOptionPane.showMessageDialog(null, "Cantidad Superior a la Existente en el Inventario", "Error", JOptionPane.ERROR_MESSAGE);
            }         
        }else{
            JOptionPane.showMessageDialog(null, "Debe Ingresar un Numero");
        }
    }//GEN-LAST:event_d2_btn_retirarActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        this.jDialog2.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
        // TODO add your handling code here:
        String con = this.edt_buscar.getText().toString().trim();
        
        if (!con.isEmpty() && !con.equals("Nombre de Insumo")) {
            this.panel_detalle.setVisible(false);
            this.label_insumo.setVisible(true);
            this.panelBoton.removeAll();
            listaInsumos(" WHERE i.nombre LIKE '%" + con + "%' and i.estado = 1");
            this.c_todos.setSelected(false);
            if (this.panelBoton.getComponents().length == 0) {
                
                JLabel label = new JLabel();
                label.setText("<html>"+"No se Encontraron<br> Insumos con <br> el nombre <br> ["+con+"]"+"</hmtl>");
                label.setSize(150,50);
                label.setPreferredSize(new Dimension(150,50));
                
                label.setForeground(Color.white);
                label.setFont(new Font("Consolas", Font.PLAIN, 24));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                panelBoton.add(label);
                System.out.println("NO se encontro nada");
            } else {
                System.out.println("Se encontro " + panelBoton.getComponents().length);
            }
            this.panelBoton.updateUI();
        }else{
            JOptionPane.showMessageDialog(null, "Debe escribir el Nombre de un Insumo");
        }
        
    }//GEN-LAST:event_btn_buscarActionPerformed

    private void btn_editar_imagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editar_imagenActionPerformed
        // TODO add your handling code here:
        
        establecerApariencia();
        JFileChooser escoger = new JFileChooser();
        escoger.setDialogTitle("Seleccione una Imagen");
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        escoger.setFileFilter(filtro);

        int b = escoger.showOpenDialog(this);
        if(b == JFileChooser.APPROVE_OPTION){
            this.di_imagen_insumo.setText("");
            ruta_imagen = escoger.getSelectedFile().getAbsolutePath();
            rsscalelabel.RSScaleLabel.setScaleLabel(this.di_imagen_insumo, escoger.getSelectedFile().toString());

        }
       
        restablecerApariencia();
        
    }//GEN-LAST:event_btn_editar_imagenActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        this.jDialog1.setBounds(0,0,400,300);
        this.jDialog1.setLocationRelativeTo(null);
        this.jDialog1.setResizable(false);
        this.jDialog1.setVisible(true);
              
    }//GEN-LAST:event_jButton5ActionPerformed

    private void llenarDocumentos(){
        Documento d = new Documento();
       // documentos = new String[aux_ps.size()];
        for(int i = 0; i < aux_ps.size(); i++){
            d = aux_ps.get(i);           
            //documentos[i] = d.getCodigo() + "-" + d.getNombre();
            c_recomendacion.addItem(d.getNombre());
            combo_d_retiros.addItem(d.getNombre());
        }
        
    }
    
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        this.jDialog2.setBounds(0,0,400,290);
        this.jDialog2.setLocationRelativeTo(null);
        this.jDialog2.setResizable(false);
        this.jDialog2.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btn_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizarActionPerformed
        // TODO add your handling code here:
        int id = codigo_insumo;
        String nombre = this.di_nombre_insumo.getText();
        String descripcion = this.di_descripcion_insumo.getText();

        double cantidad = (double) Math.round(cantidad_insumo * 100d)/100d;
        File imagen = new File(ruta_imagen);
        String precio_string = this.di_precio_insumo.getText().toString();

        String tipo_cantidad = this.combo_tipos.getSelectedItem().toString();
        String aux_tipo =this.edt_otro.getText().toString();

        if (validarEntero(precio_string)) {
            double precio = Double.parseDouble(this.di_precio_insumo.getText());
            if (!tipo_cantidad.equals("Tipo Cantidad")) {
                Insumo i = new Insumo();
                i.setId(id);
                i.setNombre(nombre);
                i.setDescripcion(descripcion);
                i.setCantidad(cantidad);
                i.setPrecio(precio);

                if(tipo_cantidad.equals("Otro...")){
                    if(!aux_tipo.equals("Tipo de Unidad") && !aux_tipo.isEmpty()){
                        tipo_cantidad = aux_tipo;
                    }else{
                        JOptionPane.showMessageDialog(null, "Deber ingresar un Tipo de Unidad", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                i.setTipo(tipo_cantidad);

                cantidad_insumo = cantidad;

                Accion a = new Accion();
                a.setProducto(nombre);
                a.setDescripcion("Actualizacion de Insumo");
                a.setCantidad(0);
                a.setUsuario(usuario_iniciado);
                a.setUltima_cantidad(cantidad);
                a.setUltimo_precio(precio);

                if (!ruta_imagen.equals("")) {
                    try {
                        byte[] icono = new byte[(int) imagen.length()];
                        InputStream input = new FileInputStream(imagen);
                        input.read(icono);
                        i.setImagen(icono);
                    } catch (Exception ex) {
                        i.setImagen(null);
                    }
                    
                    
                    MainTransaccion.actualizarInsumo1(i);
                   
                    MainTransaccion.registrarAccion(a, codigo_insumo);

                    this.panelBoton.removeAll();
                    listaInsumos("WHERE i.estado = 1");
                    this.c_todos.setSelected(true);
                    this.combo_opciones.setSelectedIndex(0);
                    this.panelBoton.updateUI();

                } else {
                   
                    MainTransaccion.actualizarInsumo2(i);
                    
                    MainTransaccion.registrarAccion(a, codigo_insumo);
                    this.panelBoton.removeAll();
                    listaInsumos("WHERE i.estado = 1");
                    this.c_todos.setSelected(true);
                    this.combo_opciones.setSelectedIndex(0);
                    this.panelBoton.updateUI();

                }

                ArrayList<Notificacion> notificaciones = new ArrayList<Notificacion>();
                notificaciones = MainTransaccion.consultarNotificaciones(codigo_insumo);

                int[] colores = {255,78,39};
                String mensaje = "";
                if (notificaciones.isEmpty()) {
                    System.out.println("Este Insumo NO Tiene Notificaciones");
                } else {
                    Notificacion n = new Notificacion();
                    for (int j = 0; j < notificaciones.size(); j++) {
                        n = notificaciones.get(j);
                        System.out.println("Numero de Notificaciones de este Insumo :" + notificaciones.size());
                        System.out.println("Cantidad del Insumo:" + i.getCantidad() + " Condicion: " + n.getCantidadBase());
                        if (i.getCantidad() <= n.getCantidadBase() && (n.getEstado() == true)) {
                            if(i.getCantidad() <= 0){
                                mensaje = "<html><p align='center'>" + "Se acabaron las<br> Unidades" + "</p></html>";
                            }else{
                                mensaje = "<html><p align='center'>" + "Se estan acabando las<br> Unidades" + "</p></html>";
                            }
                            System.out.println("Lanzar la notificacion");
                            //FrameNotificacion nt = new FrameNotificacion();
                            Prueba2 p = new Prueba2(mensaje, colores);
                        } else {
                            System.out.println("NO se cumple la condicion");
                        }
                    }
                }

                cantidad_referencia = 0;

            } else {
                JOptionPane.showMessageDialog(null, "Debe asignar un Tipo de Cantidad", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "El Precio debe ser un Número", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_actualizarActionPerformed

    private void combo_tiposItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_tiposItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {

            String aux = combo_tipos.getSelectedItem().toString();

            if (combo_tipos.getSelectedItem().toString().equals("Otro...")) {
                edt_otro.setText("Tipo de Cantidad");
                System.out.println("Activado edt otro");
                
                edt_otro.setVisible(true);
                this.panel_detalle.updateUI();
                
            } else {
                edt_otro.setVisible(false);
                System.out.println("index " +combo_tipos.getSelectedIndex());
            }

        }
    }//GEN-LAST:event_combo_tiposItemStateChanged

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
        double aux = Math.round(cantidad_insumo * 100.0) / 100.0;
        if (aux == 0) {
            int respuesta = JOptionPane.showConfirmDialog(null, "Esta seguro de Eliminar este Insumo?",
                "Confirmación!", JOptionPane.YES_NO_OPTION);
            if (respuesta == 0) {

                String nombre = this.di_nombre_insumo.getText();

               
                MainTransaccion.cambiarEstadoInsumo(codigo_insumo);
                
                Accion a = new Accion();
                a.setProducto(nombre);
                a.setDescripcion("Eliminacion de Insumo");
                a.setCantidad(0.0);
                a.setUsuario(usuario_iniciado);
                a.setUltima_cantidad(cantidad_insumo);
                MainTransaccion.registrarAccion(a, codigo_insumo);

                ListaInsumos l = new ListaInsumos();
                InsumoScroll.setViewportView(l);
                InsumoScroll.remove(this);
                Prueba pru = new Prueba("<html>" + "Insumo Eliminado <br> Correctamente!" + "</html>");
                pru.setVisible(true);
            }
        }else{
            JOptionPane.showMessageDialog(null, "<html> NO se puede eliminar el Insumo debido a que <br> todavia cuenta "
                + "con ["+ aux + "] existencias en el Inventario </html>", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void c_todosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_todosActionPerformed
        // TODO add your handling code here:
        this.panelBoton.removeAll();
        this.combo_opciones.setSelectedIndex(0);
        listaInsumos("WHERE i.estado = 1");
        this.panelBoton.updateUI();
        this.panel_detalle.setVisible(false);
        this.label_insumo.setVisible(true);
        this.edt_buscar.setText("Nombre de Insumo");
    }//GEN-LAST:event_c_todosActionPerformed

    private void combo_opcionesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_combo_opcionesItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {

            String aux = combo_opciones.getSelectedItem().toString();

            if (!combo_opciones.getSelectedItem().toString().equals("Tipo de Cantidad")) {
                c_todos.setSelected(false);
                panelBoton.removeAll();
                this.panel_detalle.setVisible(false);
                this.label_insumo.setVisible(true);
                if (combo_opciones.getSelectedItem().toString().equals("Otro...")) {
                    listaInsumos("WHERE i.tipo NOT IN ('Unidades (PZA)', 'Kilogramos (Kg)','Gramos (g)',"
                            + "'Miligramos (mg)','Litros (l)','Paquetes (PAQ)','Cajas','Botellas','Tipo Cantidad') and i.estado = 1");
                } else {
                    
                    listaInsumos("WHERE i.tipo = '" + aux + "' and i.estado = 1");
                   
                }
                if (panelBoton.getComponents().length == 0) {
                    JLabel label = new JLabel();
                    label.setText("<html>" + "No se Encontraron<br> Insumos con <br> una cantidad de tipo <br> [" + aux + "]" + "</hmtl>");
                    label.setSize(150, 50);
                    label.setPreferredSize(new Dimension(150, 50));

                    label.setForeground(Color.white);
                    label.setFont(new Font("Consolas", Font.PLAIN, 24));
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    panelBoton.add(label);
                    System.out.println("NO se encontro nada");
                }
                panelBoton.updateUI();
                
            } else {
                
            }
            
            

        }
        
        
    }//GEN-LAST:event_combo_opcionesItemStateChanged

    private void edt_otroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edt_otroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edt_otroActionPerformed

    private void edt_otroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edt_otroMouseClicked
        // TODO add your handling code here:
        if(this.edt_otro.getText().toString().equals("Tipo de Cantidad")){           
                this.edt_otro.setText("");
           
        }
    }//GEN-LAST:event_edt_otroMouseClicked

    private void edt_buscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edt_buscarMouseClicked
        // TODO add your handling code here:
        if(this.edt_buscar.getText().toString().equals("Nombre de Insumo")){
            this.edt_buscar.setText("");
        }
    }//GEN-LAST:event_edt_buscarMouseClicked

    private void c_recomendacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c_recomendacionMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_c_recomendacionMouseClicked

    private void c_recomendacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_c_recomendacionItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String aux = c_recomendacion.getSelectedItem().toString(); 
            if(!aux.equals("Documentos Relacionados")){
                //txt_factura.setText(aux);
            }
            
           
        }
    }//GEN-LAST:event_c_recomendacionItemStateChanged

    private void c_recomendacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_c_recomendacionKeyReleased
        // TODO add your handling code here:
//        String consulta = this.c_recomendacion.getSelectedItem().toString();
//        System.out.println("tama;o del comob> " + documentos.length);
//
//        c_recomendacion.removeAllItems();
//        c_recomendacion.addItem("Documentos Relacionados");
//        for(int i = 0; i < documentos.length; i++ ){
//
//            if(documentos[i].toUpperCase().contains(consulta.toUpperCase())){
//                this.c_recomendacion.addItem(documentos[i]);
//
//            }
//
//        }
    }//GEN-LAST:event_c_recomendacionKeyReleased

    public boolean validarEntero(String x){
        try{
            Double.parseDouble(x);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_actualizar;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_editar_imagen;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JComboBox<String> c_recomendacion;
    private javax.swing.JCheckBox c_todos;
    private javax.swing.JComboBox<String> combo_d_retiros;
    private javax.swing.JComboBox<String> combo_opciones;
    private javax.swing.JComboBox<String> combo_tipos;
    private javax.swing.JButton d1_btn_insertar;
    private javax.swing.JTextField d1_text_insertar;
    private javax.swing.JButton d2_btn_retirar;
    private javax.swing.JTextField d2_text_retirar;
    private javax.swing.JLabel di_cantidad_insumo;
    private javax.swing.JTextArea di_descripcion_insumo;
    private javax.swing.JLabel di_imagen_insumo;
    private javax.swing.JTextField di_nombre_insumo;
    private javax.swing.JTextField di_precio_insumo;
    private javax.swing.JTextField edt_buscar;
    private javax.swing.JTextField edt_otro;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jCodigo;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel label_descripcion;
    private javax.swing.JLabel label_descripcion1;
    private javax.swing.JLabel label_descripcion2;
    private javax.swing.JLabel label_insumo;
    private javax.swing.JLabel label_resultados;
    private javax.swing.JPanel panelBoton;
    private javax.swing.JPanel panel_detalle;
    // End of variables declaration//GEN-END:variables
}
