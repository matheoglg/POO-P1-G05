package espol.poo.proyectopoog5.modelo;

import java.time.LocalDate;
import java.util.List;

public class OrdenServicio {
    private LocalDate fecha;
    private List<Servicio> servicios;
    private Tecnico tecnico;
    private double total;

    public List<Servicio> getServicios() {
        return servicios;
    }

    public double getTotal() {
        return total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }
}
