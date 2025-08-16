package espol.poo.proyectopoog5.modelo;

import android.content.Context;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

public class Factura implements Serializable {

    private static final long serialVersionUID = 2L;

    private String idFactura;  //si no, al reordenar la lista por fecha, se pierde la posicion anterior antes de reordenar y el boton de detalles abre el de otra factura
    private Cliente cliente;
    private String fechaCreacion;             // dd-MM-yyyy
    private String mes;
    private String anio;
    private String periodo;
    private ArrayList<DetalleServicioOS> listaServicios;
    private double total;       //  + 50

    public static final String NOM_ARCHIVO = "facturas.ser";

    public Factura(Cliente cliente, String fechaCreacion, String mes, String anio,
                   ArrayList<DetalleServicioOS> listaServicios) {
        this.cliente = cliente;
        this.fechaCreacion = fechaCreacion;
        this.mes = mes;
        this.anio = anio;
        this.periodo = mes + "-" + anio;
        this.listaServicios = (listaServicios != null) ? listaServicios : new ArrayList<>();
        recalcularTotal();
        // id simple
        this.idFactura = UUID.randomUUID().toString();  // se genera un ide unico al azar
    }

    public Cliente getCliente() { return cliente; }
    public String getFechaCreacion() { return fechaCreacion; }
    public String getMes() { return mes; }
    public String getAnio() { return anio; }
    public String getPeriodo() { return periodo; }
    public ArrayList<DetalleServicioOS> getListaServicios() { return listaServicios; }
    public double getTotal() { return total; }
    public String getIdFactura() { return idFactura; }


    public void recalcularTotal() {
        double suma = 0;
        for (DetalleServicioOS d : listaServicios) {
            suma += d.getSubtotal();
        }
        // Cargo fijo empresarial
        this.total = suma + 50.0;
    }

    /** Devuelve yyyymmdd para poder ordenar por fechaCreacion (dd-MM-yyyy) */
    public String getClaveOrdenacionFecha() {
        // dd-MM-yyyy -> yyyyMMdd
        try {
            String[] p = fechaCreacion.split("-");
            if (p.length == 3) {
                String dd = p[0]; String MM = p[1]; String yyyy = p[2];
                return yyyy + MM + dd;
            }
        } catch (Exception ignored) { }
        return "00000000";
    }

    public static ArrayList<Factura> cargarFacturas(Context ctx) {
        ArrayList<Factura> lista = new ArrayList<>();
        File f = new File(ctx.getFilesDir(), NOM_ARCHIVO);
        if (f.exists()) {
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(f))) {
                lista = (ArrayList<Factura>) is.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

    public static boolean guardarLista(Context ctx, ArrayList<Factura> lista) throws Exception {
        File f = new File(ctx.getFilesDir(), NOM_ARCHIVO);
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(f))) {
            os.writeObject(lista);
            return true;
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }
    }

    public static boolean crearDatosIniciales(Context ctx) throws Exception {
        // aqui se pueden crear datos por defecto
        File f = new File(ctx.getFilesDir(), NOM_ARCHIVO);
        if (!f.exists()) {
            try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(f))) {
                os.writeObject(new ArrayList<Factura>());
                return true;
            } catch (IOException e) {
                throw new Exception(e.getMessage());
            }
        }
        return true;
    }
}
