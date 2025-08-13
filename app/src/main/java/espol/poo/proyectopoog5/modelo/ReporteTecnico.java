package espol.poo.proyectopoog5.modelo;
public class ReporteTecnico {
    private String nombreTecnico;
    private double totalRecaudado;

    public ReporteTecnico(String nombreTecnico, double totalRecaudado) {
        this.nombreTecnico = nombreTecnico;
        this.totalRecaudado = totalRecaudado;
    }

    public String getNombreTecnico() {
        return nombreTecnico;
    }

    public double getTotalRecaudado() {
        return totalRecaudado;
    }
}
