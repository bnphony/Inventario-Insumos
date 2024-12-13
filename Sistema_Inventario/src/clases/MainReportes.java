/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import ConexionBDD.Conexion;
import ConexionBDD.Timer1;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author windows
 */
public class MainReportes {
    
    private static Timer1 t = new Timer1();
    
    public static ArrayList<Accion> consultarReportes(String sql_enviado){
        t.iniciar1();
        ArrayList<Accion> acciones  = new ArrayList<Accion>();
        Conexion conn = new Conexion();
        
        String sql = "Select id_accion, cantidad_accion, producto_accion, "
                + "descripcion_accion, fecha_accion, usuario_accion, factura_accion from Acciones " + sql_enviado;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                                                                
                Accion a = new Accion();
                a.setId(rs.getInt(1));
                a.setCantidad(rs.getDouble(2));
                a.setProducto(rs.getString(3));
                a.setDescripcion(rs.getString(4));
                a.setFecha(rs.getString(5));
                a.setUsuario(rs.getString(6));  
                a.setFactura(rs.getString(7));
                acciones.add(a);
                
            }
            t.pausar("Consultar Reportes");
        } catch (SQLException ex) {
            System.out.println("primera excepcion");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("segunda excepcion");
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                conn.desconectar();
            } catch (Exception ex) {
            }
        }
        return acciones;
    
    }
    
    
    public static ArrayList<Insumo> consultarProductos(String sql_enviado){
        t.iniciar1();
        ArrayList<Insumo> insumos  = new ArrayList<Insumo>();
        Conexion conn = new Conexion();
        
        String sql = "Select *, (cantidad * precio_unitario) as calculo from insumo " + sql_enviado;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Insumo i = new Insumo();               
                i.setId(rs.getInt(1));
                i.setCantidad(rs.getDouble(2));
                i.setDescripcion(rs.getString(3));
                i.setEstado(rs.getBoolean(4));
                i.setImagen(rs.getBytes(5));
                i.setNombre(rs.getString(6));
                i.setPrecio(rs.getDouble(7));
                insumos.add(i);                
            }
            t.pausar("Consultar Productos");
        } catch (SQLException ex) {
            System.out.println("primera excepcion");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("segunda excepcion");
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                conn.desconectar();
            } catch (Exception ex) {
            }
        }
        return insumos;
    }
    
    public static ArrayList<ReporteInsumo> consultarProductos2(String sql_enviado){
        t.iniciar1();
        ArrayList<ReporteInsumo> insumos  = new ArrayList<ReporteInsumo>();
        Conexion conn = new Conexion();
        String sql = "select i.id_insumo, i.nombre, (a.ultima_cantidad + sum(a.cantidad_accion)) as Cantidad, "
                + "round(avg(a.ultimo_precio), 3) as PrecioUnitario, "
                + "sum(if(a.cantidad_accion < 0, a.cantidad_accion, 0)) as Retiros, "
                + "sum(if(a.cantidad_accion > 0, a.cantidad_accion, 0)) as Ingresos, "
                + "((a.ultima_cantidad + sum(a.cantidad_accion)) * avg(a.ultimo_precio)) as Calculo, date(a.fecha_accion) as Fecha from insumo as i "
                + "inner join acciones as a on i.id_insumo = a.fk_id_insumo " + sql_enviado;
 
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ReporteInsumo r = new ReporteInsumo();               
                r.setId_insumo(rs.getInt(1));
                r.setNombre(rs.getString(2));
                r.setCantidad(rs.getDouble(3));
                r.setPrecio_unitario(rs.getDouble(4));
                r.setRetiros(rs.getDouble(5));
                r.setIngresos(rs.getDouble(6));
                r.setTotal(rs.getDouble(7));
                r.setFecha(rs.getString(8));
                insumos.add(r);
            }
            t.pausar("Consultar Productos 2");
        } catch (SQLException ex) {
            System.out.println("primera excepcion");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("segunda excepcion");
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                conn.desconectar();
            } catch (Exception ex) {
            }
        }
        return insumos;
    }
    
    
    
    public static double conseguirUltimaCantidad(String id){
       
        double ultima_cantidad = 0;
        Conexion conn = new Conexion();
               
        String sql = "Select ultima_cantidad from Acciones "
                + "where fecha_accion = (select max(fecha_accion) from Acciones) and fk_id_insumo = " + id;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ultima_cantidad = rs.getDouble(1);              
            }
       
        } catch (SQLException ex) {
            System.out.println("primera excepcion");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("segunda excepcion");
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                conn.desconectar();
            } catch (Exception ex) {
            }
        }
        return ultima_cantidad;
    }
    
    public static void realizarConsulta(URL path, String consulta, ArrayList<String> parametros){
        t.iniciar1();
        Conexion conn = new Conexion();
        
        JasperReport reporte = null;
        
        Map<String, Object> datos = new HashMap<String, Object>();
        datos.put("consulta", consulta);
        datos.put("usuario", parametros.get(0));
        datos.put("retiros", parametros.get(1));
        datos.put("ingresos", parametros.get(2));
        datos.put("total", parametros.get(3));
        datos.put("year", parametros.get(4));
        datos.put("mes", parametros.get(5));
        datos.put("dia", parametros.get(6));        
        datos.put("logotipo", "Imagenes_insumos/sello_prolase.png");
        
        try {
            reporte = (JasperReport) JRLoader.loadObject(path);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, datos, conn.getConnection());
            JasperViewer viewer = new JasperViewer(jprint, false);
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            viewer.setVisible(true);
            t.pausar("Realizar Consulta");
        } catch (JRException ex) {
            System.out.println("segunda excepcion de reporte");
            System.out.println(ex.getMessage());
        } finally {
            try {
                        
                conn.desconectar();
            } catch (Exception ex) {
            }
        }
    
    }
    
    public static boolean validarControl(String[] fecha){
       
        boolean permitir = false;
        Conexion conn = new Conexion();
               
        String sql = "Select descripcion_accion from Acciones "
                + "where descripcion_accion = 'Control Diario' and year(fecha_accion) = "+fecha[0]+
                " and month(fecha_accion) = "+fecha[1]+" and day(fecha_accion) = " + fecha[2];
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                permitir = true;            
            }
            
        } catch (SQLException ex) {
            System.out.println("primera excepcion");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("segunda excepcion");
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                conn.desconectar();
            } catch (Exception ex) {
            }
        }
        return permitir;
    }
}
