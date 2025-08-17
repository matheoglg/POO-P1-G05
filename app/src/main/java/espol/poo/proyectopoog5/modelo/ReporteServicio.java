package espol.poo.proyectopoog5.modelo;

public class ReporteServicio {
    private String nombreServicio;
    private double totalRecaudado;

    public ReporteServicio(String nombreServicio, double totalRecaudado) {
        this.nombreServicio = nombreServicio;
        this.totalRecaudado = totalRecaudado;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public double getTotalRecaudado() {
        return totalRecaudado;
    }
}
