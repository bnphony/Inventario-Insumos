/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import ConexionBDD.Conexion;
import ConexionBDD.Timer1;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author windows
 */
public class MainTransaccion {
    
    private static Timer1 t = new Timer1();
    
    public static void main(String[] args) {
    
    }
    
    public static ArrayList<Insumo> consultarInsumos2(String consulta){
        t.iniciar1();
        ArrayList<Insumo> insumos = new ArrayList<Insumo>();
        Conexion conn = new Conexion();
        
        String sql = "SELECT i.id_insumo, i.cantidad, i.descripcion, i.estado, i.imagen, i.nombre, "
                + "i.precio_unitario, i.tipo, n.cantidad FROM insumo as i "
                + "INNER JOIN notificacion as n on i.id_insumo = n.fk_insumo " + consulta;
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
                i.setTipo(rs.getString(8));
                i.setCondicion(rs.getDouble(9));
                insumos.add(i);                
            }
            t.pausar("Consultar Insumos 2");
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
    
    public static ArrayList<Insumo> consultarInsumos(String consulta){
        t.iniciar1();
        ArrayList<Insumo> insumos = new ArrayList<Insumo>();
        Conexion conn = new Conexion();
        String sql = "SELECT * FROM insumo " + consulta;
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
                i.setTipo(rs.getString(8));
                insumos.add(i);                
            }
            t.pausar("Consultar Insumos");
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
    
    

    public static boolean crearInsumo(Insumo i){
        t.iniciar1();
        boolean validar = false;
        Conexion conn = new Conexion();
        String sql = "INSERT INTO insumo(nombre,descripcion,cantidad,imagen,estado,precio_unitario,tipo) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try{
            ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, i.getNombre());
            ps.setString(2, i.getDescripcion());
            ps.setDouble(3, i.getCantidad());
            ps.setBytes(4, i.getImagen());
            ps.setBoolean(5, i.getEstado());      
            ps.setDouble(6, i.getPrecio());
            ps.setString(7, i.getTipo());
            ps.executeUpdate();
            t.pausar("Crear Insumo");
            JOptionPane.showMessageDialog(null, "Se creo un nuevo Insumo correctamente!");
            validar = true;
            
        }catch(SQLException ex){
            System.out.println("Primera Excepcion");
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println("Segunda Excepcion");
            System.out.println(ex.getMessage());
        }finally{
            try{
                ps.close();
                conn.desconectar();
            }catch(Exception ex){
            }
        }
        return validar;
    }
    
    public static void crearNotificacion(Notificacion n){
        t.iniciar1();
        Conexion conn = new Conexion();
        String consulta = "SELECT MAX(id_insumo) FROM insumo";
        String sql = "INSERT INTO notificacion(cantidad,f_creacion,fecha,estado,fk_insumo) VALUES (?,?,?,?,?)";
        int codigo = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = conn.getConnection().prepareStatement(consulta);
            rs = ps.executeQuery();
            
            while(rs.next()){
                codigo = rs.getInt(1);
            }
            ps = conn.getConnection().prepareStatement(sql);
            ps.setDouble(1, n.getCantidadBase());           
            ps.setString(2, n.getF_creacion());
            ps.setString(3, n.getFecha());
            ps.setBoolean(4, n.getEstado());
            ps.setInt(5, codigo);
            ps.executeUpdate();
            t.pausar("Crear Notificacion");
            System.out.println("Se creo una nueva Notificacion para este isnumo");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            try{
                ps.close();
                conn.desconectar();
            }catch(Exception ex){
            }
        
        }
        
    }
    
    public static Insumo buscarInsumo(int codigo){
        t.iniciar1();
        Conexion conn = new Conexion();
        String sql = "SELECT * FROM insumo WHERE id_insumo = "+codigo;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Insumo i = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                i = new Insumo();               
                i.setId(rs.getInt(1));
                i.setCantidad(rs.getDouble(2));
                i.setDescripcion(rs.getString(3));
                i.setEstado(rs.getBoolean(4));
                i.setImagen(rs.getBytes(5));
                i.setNombre(rs.getString(6));   
                i.setPrecio(rs.getDouble(7));
            }
            t.pausar("Buscar Insumo");
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
        return i;
    }
    
    public static void actualizarInsumo1(Insumo i){
        t.iniciar1();
        Conexion conn = new Conexion();
        String sql = "UPDATE insumo SET  nombre = ?, descripcion = ?, cantidad = ?, imagen = ?, precio_unitario = ?, "
                + "tipo = ? WHERE id_insumo = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, i.getNombre());
            ps.setString(2, i.getDescripcion());
            ps.setDouble(3, i.getCantidad());
            ps.setBytes(4, i.getImagen());
            ps.setDouble(5, i.getPrecio());
            ps.setString(6, i.getTipo());
            ps.setInt(7, i.getId());
            
            ps.executeUpdate();
            t.pausar("Actualizar Insumo");
            JOptionPane.showMessageDialog(null, "Se actualizo correctamente el Insumo!");
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
    
    public static void actualizarInsumo2(Insumo i){
        t.iniciar1();
        Conexion conn = new Conexion();
        String sql = "UPDATE insumo SET  nombre = ?, descripcion = ?, cantidad = ?, precio_unitario = ?, "
                + "tipo = ? WHERE id_insumo = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, i.getNombre());
            ps.setString(2, i.getDescripcion());
            ps.setDouble(3, i.getCantidad());
            ps.setDouble(4, i.getPrecio());
            ps.setString(5, i.getTipo());
            ps.setInt(6, i.getId());
            
            ps.executeUpdate();
            t.pausar("Actualizar Insumo 2");
            JOptionPane.showMessageDialog(null, "Se actualizo correctamente el Insumo!");
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
    
    public static ArrayList<Notificacion> consultarNotificaciones(int codigo_insumo){
        t.iniciar1();
        ArrayList<Notificacion> notificaciones = new ArrayList<Notificacion>();
        Conexion conn = new Conexion();
        String sql = "SELECT id_notificacion,cantidad,estado,f_creacion,fecha FROM notificacion "
                + "WHERE fk_insumo = "+codigo_insumo;
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
                notificaciones.add(n);              
            }
            t.pausar("Consultar Notificaciones");
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
    
    public static void cambiarEstadoInsumo(int codigo_insumo){
        t.iniciar1();
        Conexion conn = new Conexion();
        String sql = "UPDATE insumo SET estado = ? WHERE id_insumo = "+codigo_insumo;
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql); 
            ps.setBoolean(1, false);
            ps.executeUpdate();
            t.pausar("Cambiar Estado Insumo");
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
    
    public static void cambiarEstados(){
        t.iniciar1();
        Conexion conn = new Conexion();
        String sql = "UPDATE insumo SET estado = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql); 
            ps.setBoolean(1, true);
            ps.executeUpdate();
            t.pausar("Cambiar Estado");
            JOptionPane.showMessageDialog(null, "Se cambio el estado de todos los Insumos");
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
    
    public static ArrayList<Insumo> insumosEliminados(){
        t.iniciar1();
        ArrayList<Insumo> insumos = new ArrayList<Insumo>();
        Conexion conn = new Conexion();
        String sql = "SELECT * FROM insumo WHERE estado = 0";
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
                insumos.add(i);
                
            }
            t.pausar("Insumos Eliminados");
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
    
    public static void activarInsumo(int codigo_insumo){
        t.iniciar1();
        Conexion conn = new Conexion();
        String sql = "UPDATE insumo SET estado = ? WHERE id_insumo = "+codigo_insumo;
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql); 
            ps.setBoolean(1, true);
            ps.executeUpdate();           
            t.pausar("Activar Insumo");
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
    
    public static double conseguirUltimoPrecio(int codigo){
        
        double ultimo_precio = 0;
        Conexion conn = new Conexion();
        String sql = "SELECT ultimo_precio from acciones "
                + "WHERE fk_id_insumo = " + codigo +" and descripcion_accion in ('Ingreso Nuevo Producto','Ingreso') "
                + "ORDER BY id_accion desc LIMIT 1";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ultimo_precio = rs.getDouble(1);                
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
        return ultimo_precio;
    
    
    }
    
    public static void registrarAccion(Accion a, int codigo_insumo){
        
        Conexion conn = new Conexion();
             
        String sql = "INSERT INTO Acciones(cantidad_accion, producto_accion, descripcion_accion, "
                + "usuario_accion, ultima_cantidad, fk_id_insumo, ultimo_precio) "
                + "VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = null;

        try{
            ps = conn.getConnection().prepareStatement(sql);
            ps.setDouble(1, a.getCantidad());
            ps.setString(2, a.getProducto());
            ps.setString(3, a.getDescripcion());
            ps.setString(4, a.getUsuario());
            ps.setDouble(5, a.getUltima_cantidad());
            ps.setInt(6, codigo_insumo);
            ps.setDouble(7, a.getUltimo_precio());
            ps.executeUpdate();
            
        }catch(SQLException ex){
            System.out.println("Primera Excepcion");
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println("Segunda Excepcion");
            System.out.println(ex.getMessage());
        }finally{
            try{
                ps.close();
                conn.desconectar();
            }catch(Exception ex){
            }
        }
    }
    
    public static void registrarAccion2(Accion a) {
        
        Conexion conn = new Conexion();

        String sql2 = "SELECT max(id_insumo) FROM insumo";
        
        String sql = "INSERT INTO Acciones(cantidad_accion, producto_accion, descripcion_accion, usuario_accion, "
                + "ultima_cantidad, fk_id_insumo, ultimo_precio) "
                + "VALUES (?,?,?,?,?,?,?)";
        ResultSet rs = null;
        PreparedStatement ps = null;

        int codigo = 0;
        try{
            ps = conn.getConnection().prepareStatement(sql2);
            rs = ps.executeQuery();
            while(rs.next()){
                codigo = rs.getInt(1);
            }
            System.out.println("Codigo consultado: " + codigo);
            
            ps = conn.getConnection().prepareStatement(sql);
            ps.setDouble(1, a.getCantidad());
            ps.setString(2, a.getProducto());
            ps.setString(3, a.getDescripcion());
            ps.setString(4, a.getUsuario());
            ps.setDouble(5, a.getUltima_cantidad());
            ps.setInt(6, codigo);
            ps.setDouble(7, a.getUltimo_precio());
            ps.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Se registro dsfsdf sfla Accion con codigo foraneo!");
        }catch(SQLException ex){
            System.out.println("Primera Excepcion");
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println("Segunda Excepcion");
            System.out.println(ex.getMessage());
        }finally{
            try{
                ps.close();
                conn.desconectar();
            }catch(Exception ex){
            }
        }
    }
    
    public static void registrarAccion3(Accion a, int codigo_insumo){
        Conexion conn = new Conexion();
             
        String sql = "INSERT INTO Acciones(cantidad_accion, producto_accion, descripcion_accion, "
                + "usuario_accion, ultima_cantidad, fk_id_insumo, ultimo_precio, factura_accion) "
                + "VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;

        try{
            ps = conn.getConnection().prepareStatement(sql);
            ps.setDouble(1, a.getCantidad());
            ps.setString(2, a.getProducto());
            ps.setString(3, a.getDescripcion());
            ps.setString(4, a.getUsuario());
            ps.setDouble(5, a.getUltima_cantidad());
            ps.setInt(6, codigo_insumo);
            ps.setDouble(7, a.getUltimo_precio());
            ps.setString(8, a.getFactura());
            ps.executeUpdate();

        }catch(SQLException ex){
            System.out.println("Primera Excepcion");
            System.out.println(ex.getMessage());
        }catch(Exception ex){
            System.out.println("Segunda Excepcion");
            System.out.println(ex.getMessage());
        }finally{
            try{
                ps.close();
                conn.desconectar();
            }catch(Exception ex){
            }
        }
    
    }
    
    public static void actualizarCantidad(int codigo, double cantidad, double precio){
        t.iniciar1();
        Conexion conn = new Conexion();
        String sql = "UPDATE insumo SET cantidad = ?, precio_unitario = ?  WHERE id_insumo = "+codigo;
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql); 
            ps.setDouble(1, cantidad);
            ps.setDouble(2, precio);
            ps.executeUpdate(); 
            t.pausar("Actualizar Cantidad");
            JOptionPane.showMessageDialog(null, "Actualizado Correctamente");
              
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
    
    public static void actualizarCantidad2(int codigo, double cantidad){
        t.iniciar1();
        Conexion conn = new Conexion();
        String sql = "UPDATE insumo SET cantidad = ? WHERE id_insumo = " + codigo;
               
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql); 
            ps.setDouble(1, cantidad);
            ps.executeUpdate(); 
            t.pausar("Actualizar Cantidad 2");
            JOptionPane.showMessageDialog(null, "Actualizado Correctamente");
            
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
