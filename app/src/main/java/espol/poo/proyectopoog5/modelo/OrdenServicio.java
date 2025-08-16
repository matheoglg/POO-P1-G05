package espol.poo.proyectopoog5.modelo;
import android.content.Context;
import android.util.Log;

import java.io.*;
import java.util.ArrayList;

public class OrdenServicio implements Serializable {
    private Cliente cliente;
    private Vehiculo vehiculo;
    private String fecha;  //formtato dd-mm-yyyy
    private ArrayList<DetalleServicioOS> listaServicios = new ArrayList<>();
    private double total;

    public static final String nomArchivoOrden = "ordenservicios.ser";

    public OrdenServicio(Cliente cliente, Vehiculo vehiculo, String fecha) {
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.fecha = fecha;
        this.total = 0;
    }

    public Cliente getCliente() {return cliente;}
    public Vehiculo getVehiculo() {return vehiculo;}
    public String getFecha() {return fecha;}
    public ArrayList<DetalleServicioOS> getListaDetalleServicios() {return listaServicios;}
    public double getTotal() {return total;}

    public void agregarDetalle(DetalleServicioOS detalle) {
        listaServicios.add(detalle);
        recalcularTotal();
    }

    public void eliminarDetalle(int index) {
        if (index >= 0 && index < listaServicios.size()) {
            listaServicios.remove(index);
            recalcularTotal();
        }
    }

    public void recalcularTotal() {
        total = 0;
        for (DetalleServicioOS d : listaServicios) {
            total += d.getSubtotal();
        }
    }



    public static ArrayList<OrdenServicio> cargarOrdenes(Context context) {
        ArrayList<OrdenServicio> lista = new ArrayList<>();
        File f = new File(context.getFilesDir(), nomArchivoOrden);

        Log.d("OrdenServicio", "Intentando cargar archivo: " + f.getAbsolutePath());

        if (f.exists()) {
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(f))) {
                lista = (ArrayList<OrdenServicio>) is.readObject();
                Log.d("OrdenServicio", "Se cargaron " + lista.size() + " órdenes desde el archivo.");
            } catch (Exception e) {
                Log.e("OrdenServicio", "Error al leer el archivo: " + e.getMessage(), e);
            }
        } else {
            Log.w("OrdenServicio", "El archivo de órdenes no existe todavía.");
        }
        return lista;
    }


    public static boolean guardarLista(Context context, ArrayList<OrdenServicio> lista) throws Exception {
        File f = new File(context.getFilesDir(), nomArchivoOrden);
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(f))) {
            os.writeObject(lista);
            return true;
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }
    }

    public static boolean crearDatosIniciales(Context context) throws Exception {
        ArrayList<OrdenServicio> lista = new ArrayList<>();
        // Ordenes de prueba
        File f = new File(context.getFilesDir(), nomArchivoOrden);
        if (!f.exists()) {
            try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(f))) {
                os.writeObject(lista);
                return true;
            } catch (IOException e) {
                throw new Exception(e.getMessage());
            }
        }
        return true;
    }

    public static void limpiarLista(Context context) {
        ArrayList<OrdenServicio> listaVacia = new ArrayList<>();
        try {
            guardarLista(context, listaVacia);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<String> generarReportePorServicio(Context context, int mes, int anio) {
        ArrayList<OrdenServicio> ordenes = cargarOrdenes(context);
        ArrayList<String> reporte = new ArrayList<>();

        // Mapa para acumular totales por nombre de servicio
        java.util.Map<String, Double> totalesPorServicio = new java.util.HashMap<>();

        for (OrdenServicio orden : ordenes) {
            try {
                String[] partesFecha = orden.getFecha().split("-");
                int mesOrden = Integer.parseInt(partesFecha[1]);
                int anioOrden = Integer.parseInt(partesFecha[2]);

                if (mesOrden == mes && anioOrden == anio) {
                    for (DetalleServicioOS detalle : orden.getListaDetalleServicios()) {
                        String nombreServicio = detalle.getServicio().getNombreServ();
                        double subtotal = detalle.getSubtotal();
                        totalesPorServicio.put(nombreServicio,
                                totalesPorServicio.getOrDefault(nombreServicio, 0.0) + subtotal);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Convertir a lista para mostrar en RecyclerView o ListView
        for (String servicio : totalesPorServicio.keySet()) {
            double total = totalesPorServicio.get(servicio);
            reporte.add(servicio + " - Total: $" + total);
        }
        return reporte;
    }
}
