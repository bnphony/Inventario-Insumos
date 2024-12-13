/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proveedores;

import ConexionBDD.Conexion;
import ConexionBDD.Timer1;
import clases.Notificacion;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author windows
 */
public class MainProveedores {
    
    private static Timer1 t = new Timer1();
    
    public static ArrayList<Proveedor> listaProveedores(String consulta){
        t.iniciar1();
        ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
        Conexion conn = new Conexion();
        String sql = "SELECT * FROM proveedor " + consulta;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setCodigo(rs.getInt(1));
                p.setCedula(rs.getString(2));
                p.setNombres(rs.getString(3));
                p.setApellidos(rs.getString(4));
                p.setEmpresa(rs.getString(5));
                p.setEmail(rs.getString(6));
                p.setDireccion(rs.getString(7));
                p.setTelefono(rs.getString(8));
                p.setEstado(rs.getBoolean(9));         
                proveedores.add(p);              
            }
            t.pausar("Listar Proveedores");
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
        return proveedores;
    }
    
    public static void insertarProveedor(Proveedor p){
        t.iniciar1();
        Conexion conn = new Conexion();
        String sql = "INSERT INTO proveedor(cedula_prov,nombres_prov,apellidos_prov,empresa_prov,email_prov,"
                + "direccion_prov,telefono_prov,estado_prov) VALUES (?,?,?,?,?,?,?,?)";
       
        PreparedStatement ps = null;
        try{
            ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, p.getCedula());
            ps.setString(2, p.getNombres());
            ps.setString(3, p.getApellidos());
            ps.setString(4, p.getEmpresa());
            ps.setString(5, p.getEmail());
            ps.setString(6, p.getDireccion());
            ps.setString(7, p.getTelefono());
            ps.setBoolean(8, p.getEstado());
            ps.executeUpdate();
            t.pausar("Insertar Proveedor");
            JOptionPane.showMessageDialog(null, "Se creo un Nuevo Proveedor correctamente!");
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
    
    public static void actualizarProveedor(Proveedor p){
        t.iniciar1();
        Conexion conn = new Conexion();
        String sql = "UPDATE proveedor SET cedula_prov = ?, nombres_prov = ?, apellidos_prov = ?,"
                + " empresa_prov = ?, email_prov = ?, direccion_prov = ?, telefono_prov = ? WHERE id_proveedor = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, p.getCedula());
            ps.setString(2, p.getNombres());
            ps.setString(3, p.getApellidos());
            ps.setString(4, p.getEmpresa());
            ps.setString(5, p.getEmail());
            ps.setString(6, p.getDireccion());
            ps.setString(7, p.getTelefono());  
            ps.setInt(8, p.getCodigo());
            ps.executeUpdate();
            t.pausar("Actualizar Proveedor");
            JOptionPane.showMessageDialog(null, "Se actualizo correctamente el Proveedor!");
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
    
    public static void cambiarEstadoProveedor(int codigo_proveedor, int estado){
        t.iniciar1();
        Conexion conn = new Conexion();
        String sql = "UPDATE proveedor SET estado_prov = "+ estado +" WHERE id_proveedor = "+ codigo_proveedor;
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);          
            ps.executeUpdate();
            t.pausar("Cambiar Estado Proveedor");
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
    
    
    public static ArrayList<Documento> documentosProveedor(int codigo_proveedor, String consulta){
        t.iniciar1();
        ArrayList<Documento> documentos = new ArrayList<Documento>();
        Conexion conn = new Conexion();
        String sql = "SELECT * FROM documentacion WHERE fk_proveedor = "+codigo_proveedor+" "
                + "and estado_doc = 1 " + consulta + " ORDER BY fecha_doc DESC";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Documento d = new Documento();
                d.setCodigo(rs.getInt(1));
                d.setNombre(rs.getString(2));
                d.setFecha(rs.getString(3));
                d.setArchivo(rs.getBytes(4));
                d.setFk_proveedor(rs.getInt(5)); 
                d.setEstado(rs.getBoolean(6));
                documentos.add(d);              
               
            }
            t.pausar("Documentos Proveedor");
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
        return documentos;
    }
    
    public static ArrayList<Documento> consultarDocumentos(){
        t.iniciar1();
        ArrayList<Documento> documentos = new ArrayList<Documento>();
        Conexion conn = new Conexion();
        
        String sql = "SELECT id_doc, nombre_doc FROM documentacion "
                + "WHERE estado_doc = 1 "
                + "ORDER BY fecha_doc DESC";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Documento d = new Documento();                
                d.setCodigo(rs.getInt(1));
                d.setNombre(rs.getString(2));                
                documentos.add(d);              
            }
            t.pausar("Consultar Documentos");
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
        return documentos;
    }
    
    public static void ejecutarPdf(int codigo_doc) {
        t.iniciar1();
        Conexion conn = new Conexion();
        PreparedStatement ps = null;
        ResultSet rs = null;
        byte[] b = null;

        try {
            ps = conn.getConnection().prepareStatement("SELECT documento FROM documentacion WHERE id_doc = " + codigo_doc);                                
            rs = ps.executeQuery();
            while (rs.next()) {
                b = rs.getBytes(1);
            }
            InputStream bos = new ByteArrayInputStream(b);

            int tamanoInput = bos.available();
            byte[] datosPDF = new byte[tamanoInput];
            bos.read(datosPDF, 0, tamanoInput);

            OutputStream out = new FileOutputStream("new.pdf");
            out.write(datosPDF);
            

            //abrir archivo
            out.close();
            bos.close();
            ps.close();
            rs.close();
            conn.desconectar();
            t.pausar("Ejecutar PDF");
        } catch (IOException | NumberFormatException | SQLException ex) {
            System.out.println("Error al abrir archivo PDF " + ex.getMessage());
        }
    }
    
    public static void guardarPdf(Documento d){
        t.iniciar1();
        Conexion conn = new Conexion();
        String sql = "INSERT INTO documentacion(nombre_doc,fecha_doc,documento,"
                + "fk_proveedor,estado_doc) VALUES (?,?,?,?,?)";
      
        PreparedStatement ps = null;
        try{
            ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, d.getNombre());
            ps.setString(2, d.getFecha());
            ps.setBytes(3, d.getArchivo());
            ps.setInt(4, d.getFk_proveedor());
            ps.setBoolean(5, d.getEstado());            
            ps.executeUpdate();   
            t.pausar("Guardar PDF");
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
    
    public static void guardarPdf2(Documento d){
        t.iniciar1();
        Conexion conn = new Conexion();
        String sql = "INSERT INTO documentacion(nombre_doc,fecha_doc,documento,"
                + "estado_doc) VALUES (?,?,?,?)";
      
        PreparedStatement ps = null;
        try{
            ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, d.getNombre());
            ps.setString(2, d.getFecha());
            ps.setBytes(3, d.getArchivo());
            ps.setBoolean(4, d.getEstado());            
            ps.executeUpdate();   
            t.pausar("Guardar PDF 2");
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
    
    
    public static ArrayList<Proveedor> comprobarProveedor(){
        t.iniciar1();
        ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
        Conexion conn = new Conexion();
        String sql = "SELECT id_proveedor,empresa_prov FROM proveedor WHERE estado_prov = 1";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setCodigo(rs.getInt(1));
                p.setEmpresa(rs.getString(2));
                proveedores.add(p);              
            }
            t.pausar("Comprobar Proveedor");
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
        return proveedores;
    }
    
    public static void actualizarPdf(Documento d){
        t.iniciar1();
        Conexion conn = new Conexion();
        String sql = "UPDATE documentacion SET nombre_doc = ?, fecha_doc = ?, documento = ?, fk_proveedor = ? "
                + "WHERE id_doc = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);    
            ps.setString(1, d.getNombre());
            ps.setString(2, d.getFecha());
            ps.setBytes(3, d.getArchivo());
            
            if(d.getFk_proveedor() == 0){
                ps.setDate(4, null);
            }else{
                ps.setInt(4, d.getFk_proveedor()); 
            }
            
            
            ps.setInt(5, d.getCodigo());
            
           
            ps.executeUpdate();
            t.pausar("Actualizar PDF");
            
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
    
    public static void actualizarPdf2(Documento d){
        t.iniciar1();
        Conexion conn = new Conexion();
        String sql = "UPDATE documentacion SET nombre_doc = ?, fecha_doc = ?, documento = ? "
                + "WHERE id_doc = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);    
            ps.setString(1, d.getNombre());
            ps.setString(2, d.getFecha());
            ps.setBytes(3, d.getArchivo());           
            ps.setInt(4, d.getCodigo());
            ps.executeUpdate();
            t.pausar("Actualizar PDF 2");
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
    
    public static void eliminarPdf(int codigo){
        t.iniciar1();
        Conexion conn = new Conexion();
        String sql = "UPDATE documentacion SET estado_doc = 0 WHERE id_doc = " + codigo;
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);               
            ps.executeUpdate();
            t.pausar("Eliminar PDF");
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
    
    
    public static ArrayList<Documento> listaDocumentos(String consulta){
        t.iniciar1();
        ArrayList<Documento> documentos = new ArrayList<Documento>();
        Conexion conn = new Conexion();
        String sql = "SELECT * FROM documentacion " + consulta + " ORDER BY fecha_doc DESC";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Documento d = new Documento();
                d.setCodigo(rs.getInt(1));
                d.setNombre(rs.getString(2));
                d.setFecha(rs.getString(3));
                d.setArchivo(rs.getBytes(4));
                d.setFk_proveedor(rs.getInt(5)); 
                d.setEstado(rs.getBoolean(6));
                documentos.add(d);              
            }
            t.pausar("Lista Documentos");
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
        return documentos;
    }
}
