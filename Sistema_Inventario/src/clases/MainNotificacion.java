/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import ConexionBDD.Conexion;
import ConexionBDD.Timer1;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author windows
 */
public class MainNotificacion {
    
    private static Timer1 t = new Timer1();
    public static void main(String[] args){
    
    }
    
    public static void actuaizarNotificacion(Notificacion n){
        t.iniciar1();
        Conexion conn = new Conexion();
        String sql = "UPDATE notificacion SET  cantidad = ? WHERE id_notificacion = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            ps.setDouble(1, n.getCantidadBase());           
            ps.setInt(2, n.getId());
            ps.executeUpdate();
            t.pausar("Actualizar Notificacion");
            JOptionPane.showMessageDialog(null, "Se actualizo correctamente la Notificacion!");
        } catch (SQLException ex) {
            System.out.println("Entre aqui xddddd");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                conn.desconectar();
            } catch (Exception ex) {
            }
        }
    
    
    }
    
    public static void cambiarEstadoNotificacion(int codigo){
        t.iniciar1();
        Conexion conn = new Conexion();
        String sql = "UPDATE notificacion SET estado = ? WHERE id_notificacion = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);   
            ps.setBoolean(1, false);
            ps.setInt(2, codigo);
            ps.executeUpdate();
            t.pausar("Cambiar Estado Notificacion");
            JOptionPane.showMessageDialog(null, "Se elimino la Notificacion correctamente!");
        } catch (SQLException ex) {
            System.out.println("Entre aqui xddddd");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                conn.desconectar();
            } catch (Exception ex) {
            }
        }
    }
    
    public static ArrayList<Notificacion> listaNotificaciones(String condicion){
        t.iniciar1();
        ArrayList<Notificacion> notificaciones = new ArrayList<Notificacion>();
        Conexion conn = new Conexion();
        String sql = "SELECT n.id_notificacion,n.cantidad,n.estado,n.f_creacion,n.fecha,n.fk_insumo,i.nombre, i.cantidad FROM insumo as i "
                + "inner join notificacion as n on n.fk_insumo = i.id_insumo " + condicion;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Notificacion n = new Notificacion();
                n.setId(rs.getInt(1));
                n.setCantidadBase(rs.getDouble(2));
                n.setEstado(rs.getBoolean(3));
                n.setF_creacion(rs.getString(4));
                n.setFecha(rs.getString(5));
                n.setCodigo_insumo(rs.getInt(6));
                n.setNombre_insumo(rs.getString(7));
                n.setCantidad_insumo(rs.getDouble(8));
                notificaciones.add(n);              
            }
            t.pausar("Lista Notificaciones");
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
        return notificaciones;
    }
    
    public static ArrayList<Notificacion> notificacionesEliminadas(){
        t.iniciar1();
        ArrayList<Notificacion> notificaciones = new ArrayList<Notificacion>();
        Conexion conn = new Conexion();
        String sql = "SELECT n.id_notificacion,n.cantidad,n.f_creacion,i.nombre FROM insumo as i "
                + "inner join notificacion as n on n.fk_insumo = i.id_insumo WHERE n.estado = 0";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Notificacion n = new Notificacion();
                n.setId(rs.getInt(1));
                n.setCantidadBase(rs.getDouble(2));                   
                n.setF_creacion(rs.getString(3));              
                n.setNombre_insumo(rs.getString(4));
                notificaciones.add(n);              
            }
            t.pausar("Notificaciones Eliminadas");
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
        return notificaciones;
    
    }
    
    public static void activarNotificacion(int codigo_notificacion){
        t.iniciar1();
        Conexion conn = new Conexion();
        String sql = "UPDATE notificacion SET estado = ? WHERE id_notificacion = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);   
            ps.setBoolean(1, true);
            ps.setInt(2, codigo_notificacion);
            ps.executeUpdate();
            t.pausar("Activar Notificacion");
        } catch (SQLException ex) {
            System.out.println("Entre aqui xddddd");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                conn.desconectar();
            } catch (Exception ex) {
            }
        }
    }
}
