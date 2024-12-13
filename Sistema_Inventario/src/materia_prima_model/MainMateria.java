/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package materia_prima_model;

import ConexionBDD.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author windows
 */
public class MainMateria {
    
    public static void crearMateria(MateriaPrima ma) {
        
        Conexion conn = new Conexion();
        String sql = "INSERT INTO MATERIAPRIMA (codigoMateria, fechaMateria, viajesMateria,"
                + " localidadMateria, cantidadMateria, tipoMateria, totalMateria, estadoMateria) values (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, ma.getCodigo());
            ps.setString(2, ma.getFecha());
            ps.setInt(3, ma.getViajes());
            ps.setString(4, ma.getLocalidad());
            ps.setFloat(5, ma.getCantidad());
            ps.setString(6, ma.getTipo());
            ps.setFloat(7, ma.getTotal());
            ps.setString(8, ma.getEstado());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Guardado Correctamente!");
        } catch (SQLException ex) {
            System.out.println("Primera Excepcion");
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Segunda Excepcion");
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                conn.desconectar();
            } catch (Exception ex) {
            }
        }

    }
    
    public static ArrayList<MateriaPrima> listaMaterias(){
        ArrayList<MateriaPrima> materias = new ArrayList<MateriaPrima>();
        Conexion conn = new Conexion();
        String sql = "SELECT * FROM materiaprima";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MateriaPrima ma = new MateriaPrima();               
                ma.setCodigo(rs.getString(1));
                ma.setFecha(rs.getString(2));
                ma.setViajes(rs.getInt(3));
                ma.setLocalidad(rs.getString(4));
                ma.setCantidad(rs.getFloat(5));
                ma.setTipo(rs.getString(6));
                ma.setTotal(rs.getFloat(7));
                ma.setEstado(rs.getString(8));
                materias.add(ma);                
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
        return materias;
    
    }
    
    public static void modificarMateria(MateriaPrima ma){
        Conexion conn = new Conexion();
        String sql = "UPDATE materiaprima SET  fechaMateria=?, viajesMateria=?, localidadMateria=?, cantidadMateria=?, tipoMateria=?, totalMateria=?, estadoMateria=? "
                + "WHERE codigoMateria = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, ma.getFecha());
            ps.setInt(2, ma.getViajes());
            ps.setString(3, ma.getLocalidad());
            ps.setFloat(4, ma.getCantidad());
            ps.setString(5, ma.getTipo());
            ps.setFloat(6, ma.getTotal());
            ps.setString(7, ma.getEstado());
            ps.setString(8, ma.getCodigo());        
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Actualizacion Correcta!");
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
    
    public static ArrayList<MateriaPrima> consultarMaterias(String tipo){
        ArrayList<MateriaPrima> materias = new ArrayList<MateriaPrima>();
        Conexion conn = new Conexion();
        String sql = "SELECT * FROM materiaprima WHERE tipomateria = ?";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, tipo);
            rs = ps.executeQuery();
            while (rs.next()) {
                MateriaPrima ma = new MateriaPrima();               
                ma.setCodigo(rs.getString(1));
                ma.setFecha(rs.getString(2));
                ma.setViajes(rs.getInt(3));
                ma.setLocalidad(rs.getString(4));
                ma.setCantidad(rs.getFloat(5));
                ma.setTipo(rs.getString(6));
                ma.setTotal(rs.getFloat(7));
                ma.setEstado(rs.getString(8));
                materias.add(ma);                
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
        return materias;
    
    }
    
    //Consultar por rutas
    public static ArrayList<MateriaPrima> consultarRutas(String localidad){
        ArrayList<MateriaPrima> materias = new ArrayList<MateriaPrima>();
        Conexion conn = new Conexion();
        String sql = "SELECT * FROM materiaprima WHERE localidadMateria = ?";
        ResultSet rs = null; 
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, localidad);
            rs = ps.executeQuery();
            while (rs.next()) {
                MateriaPrima ma = new MateriaPrima();               
                ma.setCodigo(rs.getString(1));
                ma.setFecha(rs.getString(2));
                ma.setViajes(rs.getInt(3));
                ma.setLocalidad(rs.getString(4));
                ma.setCantidad(rs.getFloat(5));
                ma.setTipo(rs.getString(6));
                ma.setTotal(rs.getFloat(7));
                ma.setEstado(rs.getString(8));
                materias.add(ma);                
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
        return materias;
    
    }
    //Consulta Rutas y tipo
    
        public static ArrayList<MateriaPrima> consultarRutasTipo(String tipos, String localidad){
        ArrayList<MateriaPrima> materias = new ArrayList<MateriaPrima>();
        Conexion conn = new Conexion();
        String sql = "SELECT * FROM materiaprima WHERE localidadMateria = ? && tipoMateria =? ";
        ResultSet rs = null; //SELECT * FROM materiaprima WHERE localidadMateria = 'LATACUNGA' && tipoMateria = 'Tipo A';
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1,tipos);
            ps.setString(1,localidad);
            rs = ps.executeQuery();
            while (rs.next()) {
                MateriaPrima ma = new MateriaPrima();               
                ma.setCodigo(rs.getString(1));
                ma.setFecha(rs.getString(2));
                ma.setViajes(rs.getInt(3));
                ma.setLocalidad(rs.getString(4));
                ma.setCantidad(rs.getFloat(5));
                ma.setTipo(rs.getString(6));
                ma.setTotal(rs.getFloat(7));
                ma.setEstado(rs.getString(8));
                materias.add(ma);                
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
        return materias;
    
    }
        
    public static void eliminarMateria(String codigo){
        Conexion conn = new Conexion();
        String sql = "DELETE FROM materiaprima "
                + "WHERE codigoMateria = ?";      
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, codigo);                            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Eliminacion Correcta!");
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
