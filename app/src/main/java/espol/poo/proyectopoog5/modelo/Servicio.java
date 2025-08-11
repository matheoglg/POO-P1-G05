package espol.poo.proyectopoog5.modelo;

import android.content.Context;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Servicio implements Serializable {
    private String codigoServ;
    private String nombreServ;
    private double precioActual;
    private List<Double> historialPrecios = new ArrayList<>();
    public static final String nomArchivoServicio = "servicio.ser";

    private static int contadorCodigo = 1;

    public Servicio(String nombreServ, double precioActual) {
        this.codigoServ = String.format("S%03d", contadorCodigo++);
        this.nombreServ = nombreServ;
        this.precioActual = precioActual;
        this.historialPrecios.add(precioActual);
    }

    public String getCodigoServ() { return codigoServ; }
    public String getNombreServ() { return nombreServ; }
    public double getPrecioActual() { return precioActual; }

    public void setNombreServ(String nombreServ) { this.nombreServ = nombreServ; }
    public void setPrecioActual(double precioNuevo) {
        this.precioActual = precioNuevo;
        this.historialPrecios.add(precioNuevo);
    }

    public static ArrayList<Servicio> cargarServicios(Context context) {
        ArrayList<Servicio> lista = new ArrayList<>();
        File f = new File(context.getFilesDir(), nomArchivoServicio);
        if (f.exists()) {
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(f))) {
                lista = (ArrayList<Servicio>) is.readObject();
                contadorCodigo = lista.size() + 1; // actualizar último código
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

    public static boolean guardarLista(Context context, ArrayList<Servicio> lista) throws Exception {
        File f = new File(context.getFilesDir(), nomArchivoServicio);
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(f))) {
            os.writeObject(lista);
            return true;
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }
    }

    public static boolean crearDatosIniciales(Context context) throws Exception {
        ArrayList<Servicio> lista = new ArrayList<>();
        lista.add(new Servicio("Cambio de aceite", 25.00));
        lista.add(new Servicio("Alineación", 30.00));

        File f = new File(context.getFilesDir(), nomArchivoServicio);
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
        ArrayList<Servicio> listaVacia = new ArrayList<>();
        try {
            guardarLista(context, listaVacia);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
