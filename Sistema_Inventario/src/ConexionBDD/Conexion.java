/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author windows
 */
public class Conexion {
    static String bd = "trabajo1"; // Colocar la Base de datos
    static String login = "prolase"; // Colocar el Usuario
    static String password = ""; // Colocar el password
    static String url = "jdbc:mysql://localhost:3308/" + bd;// Si nos le funciona cambien el puerto o el local host
    Connection connection = null;
  
    public Conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, login, password);
            if (connection != null) {
                System.out.println("Conexión a base de datos " + bd + " OK\n");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public Connection getConnection() {
        return connection;
    }
    public void desconectar() {
        try {
            connection.close();
        } catch (Exception ex) {
        }
    }   
}



