package espol.poo.proyectopoog5.modelo;

import java.util.ArrayList;
import java.util.List;

public class Servicio {
    private int codigoServ;
    private String nombreServ;
    private double precioActual;
    public static int ultCodigoServ;
    private List<Double> historialPrecios = new ArrayList<>();

    public Servicio(String nombreServ, double precioActual) {
        this.codigoServ = ++ultCodigoServ;
        this.nombreServ = nombreServ;
        this.precioActual = precioActual;
        this.historialPrecios.add(precioActual);
    }

    public String getCodigoSSS() {        //para obtener el código con el formato "S001", "S002"...
        return String.format("S%03d", codigoServ);
    }

    public int getCodigoServ() {
        return codigoServ;
    }

    public String getNombreServ() {
        return nombreServ;
    }

    public void setNombreServ(String nombreServ) {
        this.nombreServ = nombreServ;
    }

    public double getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(double precioNuevo) {
        this.precioActual = precioNuevo;
        this.historialPrecios.add(precioNuevo);
    }

    public List<Double> getHistorialPrecios() {
        return historialPrecios;
    }
}
