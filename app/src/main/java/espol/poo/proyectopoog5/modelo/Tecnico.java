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

public class Tecnico extends Persona {
    private String espTecnico;
    public static final String nomArchivoTecnico = "tecnicos.ser";
    public Tecnico() {
    }
    public Tecnico(String idPersona, String nombrePersona, String telfPersona, String espTecnico) {
        super(idPersona, nombrePersona, telfPersona);
        this.espTecnico = espTecnico;
    }
    public String getEspTecnico() { return espTecnico; }
    public void setEspTecnico(String espTecnico) { this.espTecnico = espTecnico; }

    @Override
    public String getNombrePersona() {
        return super.getNombrePersona();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tecnico tecnico = (Tecnico) o;
        return this.getIdPersona().equals(tecnico.getIdPersona());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getIdPersona());
    }
    public static ArrayList<Tecnico> obtenerTecnicos(){
        ArrayList<Tecnico> lista = new ArrayList<>();
        Tecnico t1 = new Tecnico("0948238498", "Alvaro López", "099111222", "Frenos");
        Tecnico t2 = new Tecnico("0912834921", "Mario Barcos", "099333444", "Motor");
        lista.add(t1);
        lista.add(t2);

        return lista;
    }

    public static ArrayList<Tecnico> cargarTecnicos(Context context){
        ArrayList<Tecnico> lista = new ArrayList<>();
        File f = new File(context.getFilesDir(), nomArchivoTecnico);
        //se escribe la lista serializada
        if ( f.exists()) { //si no existe se crea la lista
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(f))) {
                lista = (ArrayList<Tecnico>) is.readObject(); //establecer el ultimo id

            } catch (Exception e) {
                //quizas lanzar una excepcion personalizada
                new Exception(e.getMessage());
            }
        }
        return lista;
    }

    public static boolean crearDatosIniciales(Context context) throws Exception{
        ArrayList<Tecnico> lista = new ArrayList<>();
        boolean guardado = false;
        Tecnico t1 = new Tecnico("0948238498", "Alvaro López", "099111222", "Frenos");
        Tecnico t2 = new Tecnico("0912834921", "Mario Barcos", "099333444", "Motor");
        lista.add(t1);
        lista.add(t2);

        File f = new File(context.getFilesDir(), nomArchivoTecnico);
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

    public static boolean guardarLista(Context context,ArrayList<Tecnico> lista) throws Exception{
        boolean guardado = false;
        File f = new File(context.getFilesDir(), nomArchivoTecnico);
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(f))) {
            os.writeObject(lista);
            guardado = true;
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }
        return guardado;
    }

    @Override
    public String toString(){ return super.toString() + espTecnico + "-"; }
}
