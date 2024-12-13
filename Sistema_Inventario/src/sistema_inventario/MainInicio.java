/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema_inventario;

import ConexionBDD.Conexion;
import ConexionBDD.Timer1;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import materia_prima_model.MateriaPrima;

/**
 *
 * @author windows
 */
public class MainInicio {
    
    private static Timer1 t = new Timer1();
    
    public static Usuario validarUsuario(String nombre, String password){
        t.iniciar1();
        Conexion conn = new Conexion();
        String sql = "SELECT * FROM usuario WHERE nombre_usuario = ? and password_usuario = ? "
                + "and estado_usuario = ?";
        ResultSet rs = null;
        PreparedStatement ps = null;
        Usuario u = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, password);
            ps.setBoolean(3, true);
            rs = ps.executeQuery();
            while (rs.next()) {
                u = new Usuario();               
                u.setNombre(rs.getString(1));
                u.setApellido(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setEmail(rs.getString(4));
                u.setTipo(rs.getString(5));
                u.setEstado(rs.getBoolean(6));                              
            }
            t.pausar("Validar Usuario");
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
        return u;
    }
    
    public static boolean existeUsuario(String nombre){
        t.iniciar1();
        Conexion conn = new Conexion();
        boolean validar = false;
        String sql = "SELECT * FROM usuario WHERE nombre_usuario = ? ";
        ResultSet rs = null;
        PreparedStatement ps = null;
        Usuario u = null;
        try {
            ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            while (rs.next()) {
                validar = true;                 
            }
            t.pausar("Existe Usuario");
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
        return validar;
    
    }
    
    public static void registrarUsuario(Usuario u){
        t.iniciar1();
        Conexion conn = new Conexion();
        String sql = "INSERT INTO usuario(nombre_usuario,apellido_usuario,password_usuario,"
                + "correo_usuario,tipo_usuario,estado_usuario) VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try{
            ps = conn.getConnection().prepareStatement(sql);
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getTipo());
            ps.setBoolean(6, u.getEstado());
            ps.executeUpdate();
            t.pausar("Registrar Usuario");
            JOptionPane.showMessageDialog(null, "Usuario Registrado Correctamente!");
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
    
    
    
}
