package espol.poo.proyectopoog5.modelo;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetalleServicioOS implements Serializable {
    private Servicio servicio;
    private int cantidad;
    private Tecnico tecnico;
    private double subtotal;

    public DetalleServicioOS(Servicio servicio, int cantidad, Tecnico tecnico) {
        this.servicio = servicio;
        this.cantidad = cantidad;
        this.tecnico = tecnico;
        this.subtotal = cantidad * servicio.getPrecioActual();
    }

    public Servicio getServicio() {
        return servicio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        recalcularSubtotal();
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
        recalcularSubtotal();
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public void recalcularSubtotal() {
        if (servicio != null) {
            this.subtotal = cantidad * servicio.getPrecioActual();
        }
    }
}