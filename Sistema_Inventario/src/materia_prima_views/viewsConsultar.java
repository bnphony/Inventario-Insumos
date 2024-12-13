
package materia_prima_views;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import materia_prima_model.MainMateria;
import materia_prima_model.MateriaPrima;



public class viewsConsultar extends javax.swing.JPanel {


    public viewsConsultar() {
        initComponents();
             //  listarMateria();
 
        
    }

    private void consultarMateria(JTable tabla, String tipo){
        DefaultTableModel modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        modelo.addColumn("Codigo");
        modelo.addColumn("Fecha");
        modelo.addColumn("N Viajes");
        modelo.addColumn("Ruta o Sector");
        modelo.addColumn("Cantidad");       
        modelo.addColumn("Tipo");
        modelo.addColumn("Total");
        
        Object[] fila = new Object[modelo.getColumnCount()];
        ArrayList<MateriaPrima> materias = new ArrayList<MateriaPrima>();
        MateriaPrima ma = new MateriaPrima();
        materias = MainMateria.consultarMaterias(tipo);
        for (int i = 0; i < materias.size(); i++) {
            ma = materias.get(i);
            
            fila[0] = ma.getCodigo();
            fila[1] = ma.getFecha();
            fila[2] = ma.getViajes();
            fila[3] = ma.getLocalidad();
            fila[4] = ma.getCantidad();
            fila[5] = ma.getTipo();
            fila[6] = ma.getTotal();
            
            modelo.addRow(fila);

            tabla.setModel(modelo);
            tabla.setRowHeight(30);
            
            
        }
        alinearTabla();    
    }
    
    //Buscar por rutas
    private void consultarRuta(JTable tabla, String localidad){
        DefaultTableModel modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        modelo.addColumn("Codigo");
        modelo.addColumn("Fecha");
        modelo.addColumn("N Viajes");
        modelo.addColumn("Ruta o Sector");
        modelo.addColumn("Cantidad");       
        modelo.addColumn("Tipo");
        modelo.addColumn("Total");
        
        Object[] fila = new Object[modelo.getColumnCount()];
        ArrayList<MateriaPrima> materias = new ArrayList<MateriaPrima>();
        MateriaPrima ma = new MateriaPrima();
        materias = MainMateria.consultarRutas(localidad);
        for (int i = 0; i < materias.size(); i++) {
            ma = materias.get(i);
            
            fila[0] = ma.getCodigo();
            fila[1] = ma.getFecha();
            fila[2] = ma.getViajes();
            fila[3] = ma.getLocalidad();
            fila[4] = ma.getCantidad();
            fila[5] = ma.getTipo();
            fila[6] = ma.getTotal();
            
            modelo.addRow(fila);

            tabla.setModel(modelo);
            tabla.setRowHeight(30);
            
            
        }
        alinearTabla();    
    }
    // Consultar por rutas y localidad
    
    private void consultarRutaTipo(JTable tabla,String tipo, String localidad){
        DefaultTableModel modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        modelo.addColumn("Codigo");
        modelo.addColumn("Fecha");
        modelo.addColumn("N Viajes");
        modelo.addColumn("Ruta o Sector");
        modelo.addColumn("Cantidad");       
        modelo.addColumn("Tipo");
        modelo.addColumn("Total");
        
        Object[] fila = new Object[modelo.getColumnCount()];
        ArrayList<MateriaPrima> materias = new ArrayList<MateriaPrima>();
        MateriaPrima ma = new MateriaPrima();
        materias = MainMateria.consultarRutasTipo(tipo,localidad);
        for (int i = 0; i < materias.size(); i++) {
            ma = materias.get(i);
            
            fila[0] = ma.getCodigo();
            fila[1] = ma.getFecha();
            fila[2] = ma.getViajes();
            fila[3] = ma.getLocalidad();
            fila[4] = ma.getCantidad();
            fila[5] = ma.getTipo();
            fila[6] = ma.getTotal();
            
            modelo.addRow(fila);

            tabla.setModel(modelo);
            tabla.setRowHeight(30);
            
            
        }
        alinearTabla();    
    }
    
    private void alinearTabla(){
    
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpTipo = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblConsultas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        bntBuscar = new javax.swing.JButton();
        bntCancelar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        rbtA = new javax.swing.JRadioButton();
        rbtC = new javax.swing.JRadioButton();
        rbtB = new javax.swing.JRadioButton();
        txtRutas = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        tblConsultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblConsultas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblConsultasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblConsultas);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("COSULTAS REPORTES");

        bntBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/materia_prima_views/buscar3.png"))); // NOI18N
        bntBuscar.setText("Buscar");
        bntBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntBuscarActionPerformed(evt);
            }
        });

        bntCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/materia_prima_views/cancelar2.png"))); // NOI18N
        bntCancelar.setText("Cancelar");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("BUSCAR POR TIPO:");

        grpTipo.add(rbtA);
        rbtA.setText("TIPO A");
        rbtA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtAActionPerformed(evt);
            }
        });

        grpTipo.add(rbtC);
        rbtC.setText("TIPO C");

        grpTipo.add(rbtB);
        rbtB.setText("TIPO B");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("BUSCAR POR RUTA:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(235, 235, 235)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(563, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRutas, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bntBuscar)
                        .addGap(18, 18, 18)
                        .addComponent(bntCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rbtA)
                        .addGap(26, 26, 26)
                        .addComponent(rbtB)
                        .addGap(18, 18, 18)
                        .addComponent(rbtC))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addGap(56, 56, 56))
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtA)
                    .addComponent(rbtB)
                    .addComponent(rbtC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtRutas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntBuscar)
                    .addComponent(bntCancelar))
                .addGap(77, 77, 77))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblConsultasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblConsultasMouseClicked

    }//GEN-LAST:event_tblConsultasMouseClicked
    
    
    
    private void bntBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntBuscarActionPerformed
         String Rutas =" ";
         Rutas=txtRutas.getText();
         
        if(rbtA.isSelected()){
            consultarMateria(tblConsultas, "Tipo A");
            consultarRuta(tblConsultas, Rutas);
            System.out.println(this.rbtA.getText().toString());
        }else if(rbtB.isSelected()){
            consultarMateria(tblConsultas, "Tipo B");
            consultarRuta(tblConsultas, Rutas);
            System.out.println(this.rbtA.getText().toString());
        }else if (rbtC.isSelected()){
            consultarMateria(tblConsultas, "Tipo C");
            consultarRuta(tblConsultas, Rutas);
            //consultarRutaTipo(tblConsultas, "Tipo C", Rutas);
            System.out.println(this.rbtA.getText().toString());
        }else{
            //consultarRutaTipo(tblConsultas, "Tipo C", Rutas);
            JOptionPane.showMessageDialog(null, "Debe Seleccionar un Tipo de Materia Prima a buscar");
        }
      

    }//GEN-LAST:event_bntBuscarActionPerformed

    private void rbtAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtAActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bntBuscar;
    public javax.swing.JButton bntCancelar;
    private javax.swing.ButtonGroup grpTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbtA;
    private javax.swing.JRadioButton rbtB;
    private javax.swing.JRadioButton rbtC;
    public javax.swing.JTable tblConsultas;
    private javax.swing.JTextField txtRutas;
    // End of variables declaration//GEN-END:variables
}
