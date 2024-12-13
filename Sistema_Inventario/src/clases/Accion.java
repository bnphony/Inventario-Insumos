/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author windows
 */
public class Accion {
    int id;
    String producto;
    String descripcion;
    double cantidad;
    String fecha;
    String Usuario;
    double ultima_cantidad;
    double ultimo_precio;
    String factura;
    
    public Accion(){
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public double getUltima_cantidad() {
        return ultima_cantidad;
    }

    public void setUltima_cantidad(double ultima_cantidad) {
        this.ultima_cantidad = ultima_cantidad;
    }

    public double getUltimo_precio() {
        return ultimo_precio;
    }

    public void setUltimo_precio(double ultimo_precio) {
        this.ultimo_precio = ultimo_precio;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }
    
    
    
    
    
}
