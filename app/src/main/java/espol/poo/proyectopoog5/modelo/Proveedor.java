package espol.poo.proyectopoog5.modelo;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Objects;

public class Proveedor extends Persona {
    private String descProveedor;
    public static final String nomArchivoProveedor = "proveedores.ser";
    public Proveedor() { }

    public Proveedor(String idPersona, String nombrePersona, String telfPersona, String descProveedor) {
        super(idPersona, nombrePersona, telfPersona);
        this.descProveedor = descProveedor;
    }

    public String getDescProveedor() { return descProveedor; }

    public void setDescProveedor(String descProveedor) { this.descProveedor = descProveedor; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proveedor proveedor = (Proveedor) o;
        return this.getIdPersona().equals(proveedor.getIdPersona());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getIdPersona());
    }

    public static ArrayList<Proveedor> obtenerProveedores(){
        ArrayList<Proveedor> lista = new ArrayList<>();
        Proveedor p1 = new Proveedor("0991919821", "LubriCentro SA", "098112233", "Aceites y filtros");
        Proveedor p2 = new Proveedor("0983192485", "Repuestos Max", "098445566", "Repuestos generales");

        lista.add(p1);
        lista.add(p2);

        return lista;
    }

    public static ArrayList<Proveedor> cargarProveedores(Context context){
        ArrayList<Proveedor> lista = new ArrayList<>();
        File f = new File(context.getFilesDir(), nomArchivoProveedor);
        //se escribe la lista serializada
        if ( f.exists()) { //si no existe se crea la lista
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(f))) {
                lista = (ArrayList<Proveedor>) is.readObject(); //establecer el ultimo id

            } catch (Exception e) {
                //quizas lanzar una excepcion personalizada
                new Exception(e.getMessage());
            }
        }
        return lista;
    }

    public static boolean crearDatosIniciales(Context context) throws Exception{
        ArrayList<Proveedor> lista = new ArrayList<>();
        boolean guardado = false;
        Proveedor p1 = new Proveedor("0991919821", "LubriCentro SA", "098112233", "Aceites y filtros");
        Proveedor p2 = new Proveedor("0983192485", "Repuestos Max", "098445566", "Repuestos generales");

        lista.add(p1);
        lista.add(p2);

        File f = new File(context.getFilesDir(), nomArchivoProveedor);
        if (! f.exists()) {
            try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(f))) {
                os.writeObject(lista);
                guardado = true;
            } catch (IOException e) {
                //quizas lanzar una excepcion personalizada
                throw new Exception(e.getMessage());
            }
        }else guardado = true;//si existe no hace nada
        return guardado;
    }

    public static boolean guardarLista(Context context,ArrayList<Proveedor> lista) throws Exception{
        boolean guardado = false;
        File f = new File(context.getFilesDir(), nomArchivoProveedor);
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(f))) {
            os.writeObject(lista);
            guardado = true;
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }
        return guardado;
    }

    public String toString(){ return super.toString() + descProveedor + "-";}
}
