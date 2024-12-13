
package materia_prima_model;

import java.sql.Date;

public class MateriaPrima {
    String codigo;
    String fecha;
    int viajes;
    String localidad;
    float cantidad;
    String tipo;
    float total;
    String estado;

    public MateriaPrima() {
    }

    public MateriaPrima(String codigo, String fecha, int viajes, String localidad, float cantidad, String tipo, String estado)//Esto va antes de estado
    {
        this.codigo = codigo;
        this.fecha = fecha;
        this.viajes = viajes;
        this.localidad = localidad;
        this.cantidad = cantidad;
        this.tipo = tipo;
        //this.total = total;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getViajes() {
        return viajes;
    }

    public void setViajes(int viajes) {
        this.viajes = viajes;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "materiaPrima{" + "codigo=" + codigo + ", fecha=" + fecha + ", viajes=" + viajes + ", localidad=" + localidad + ", cantidad=" + cantidad + ", tipo=" + tipo + ", total=" + total + ", estado=" + estado + '}';
    }

 
}
