package espol.poo.proyectopoog5.activities.activitiesReportes;

import java.time.LocalDate;
import java.util.*;
import espol.poo.proyectopoog5.modelo.OrdenServicio;
/**
public class ReporteController {

    public static Map<String, Double> calcularIngresosPorServicio(List<OrdenServicio> ordenes, int anio, int mes) {
        Map<String, Double> resultado = new HashMap<>();

        for (OrdenServicio orden : ordenes) {
            LocalDate fecha = orden.getFecha();
            if (fecha.getAnio() == anio && fecha.getMes() == mes) {
                orden.getServicios().forEach(serv -> {
                    resultado.put(serv.getNombreServ(),
                            resultado.getOrDefault(serv.getNombreServ(), 0.0) + serv.getPrecioActual());
                });
            }
        }
        return resultado;
    }

    public static Map<String, Double> calcularIngresosPorTecnico(List<OrdenServicio> ordenes, int anio, int mes) {
        Map<String, Double> resultado = new HashMap<>();

        for (OrdenServicio orden : ordenes) {
            LocalDate fecha = orden.getFecha();
            if (fecha.getAnio() == anio && fecha.getMes() == mes) {
                String tecnico = orden.getTecnico().getNombrePersona();
                resultado.put(tecnico,
                        resultado.getOrDefault(tecnico, 0.0) + orden.getTotal());
            }
        }
        return resultado;
    }
}
**/