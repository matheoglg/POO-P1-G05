package espol.poo.proyectopoog5.activities.activitiesReportes;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import espol.poo.proyectopoog5.modelo.DetalleServicioOS;
import espol.poo.proyectopoog5.modelo.OrdenServicio;
import espol.poo.proyectopoog5.modelo.ReporteTecnico;
import espol.poo.proyectopoog5.modelo.Tecnico;

public class ControladorReportes extends AppCompatActivity {
    public List<espol.poo.proyectopoog5.activities.activitiesReportes.ReporteServicio> obtenerIngresosPorServicio(Context context, String anio, String mes) {
        ArrayList<OrdenServicio> ordenes = OrdenServicio.cargarOrdenes(context);
        Map<String, Double> acumulado = new HashMap<>();

        for (OrdenServicio orden : ordenes) {
            String fecha = orden.getFecha();  // formato dd-mm-yyyy
            String[] partes = fecha.split("-");
            if (partes.length == 3) {
                String mesOrden = partes[1];
                String anioOrden = partes[2];

                if (anioOrden.equals(anio) && mesOrden.equals(mes)) {
                    for (DetalleServicioOS detalle : orden.getListaDetalleServicios()) {
                        String nombreServicio = detalle.getServicio().getNombreServ();
                        double subtotal = detalle.getSubtotal();

                        acumulado.put(nombreServicio, acumulado.getOrDefault(nombreServicio, 0.0) + subtotal);
                    }
                }
            }
        }

        List<espol.poo.proyectopoog5.activities.activitiesReportes.ReporteServicio> lista = new ArrayList<>();
        for (Map.Entry<String, Double> entry : acumulado.entrySet()) {
            lista.add(new espol.poo.proyectopoog5.activities.activitiesReportes.ReporteServicio(entry.getKey(), entry.getValue()));
        }
        return lista;
    }

    public List<ReporteTecnico> obtenerIngresosPorTecnico(Context context, String anio, String mes) {
        ArrayList<OrdenServicio> ordenes = OrdenServicio.cargarOrdenes(context);
        Map<String, Double> acumulado = new HashMap<>();

        for (OrdenServicio orden : ordenes) {
            String fecha = orden.getFecha();
            String[] partes = fecha.split("-");
            if (partes.length == 3) {
                String mesOrden = partes[1];
                String anioOrden = partes[2];

                if (anioOrden.equals(anio) && mesOrden.equals(mes)) {
                    for (DetalleServicioOS detalle : orden.getListaDetalleServicios()) {
                        Tecnico tecnico = detalle.getTecnico();
                        if (tecnico != null) {
                            String nombreTecnico = tecnico.getNombrePersona();
                            double subtotal = detalle.getSubtotal();

                            acumulado.put(nombreTecnico, acumulado.getOrDefault(nombreTecnico, 0.0) + subtotal);
                        }
                    }
                }
            }
        } // <-- Aquí cierra el for de ordenes

        List<ReporteTecnico> lista = new ArrayList<>();
        for (Map.Entry<String, Double> entry : acumulado.entrySet()) {
            lista.add(new ReporteTecnico(entry.getKey(), entry.getValue()));
        }
        return lista;
    }
}