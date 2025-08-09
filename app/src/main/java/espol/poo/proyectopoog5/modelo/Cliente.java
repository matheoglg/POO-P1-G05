package espol.poo.proyectopoog5.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Objects;

public class Cliente extends Persona {
    private String dirCliente;
    private ClienteTipo tipoCliente;
    private ArrayList<Vehiculo> vehiculos = new ArrayList<>();
    public static final String nomArchivoCliente = "clientes.ser";

    public Cliente() {
    }

    public Cliente(String idPersona, String nombrePersona, String telfPersona, String dirCliente, String tipoCliente) {
        super(idPersona, nombrePersona, telfPersona);
        this.dirCliente = dirCliente;
        this.tipoCliente = ClienteTipo.valueOf(tipoCliente);
    }

    public String getDirCliente() { return dirCliente; }

    public void setDirCliente(String dirCliente) { this.dirCliente = dirCliente; }

    public ClienteTipo getTipoCliente() { return tipoCliente; }

    public void setTipoCliente(String tipoCliente) { this.tipoCliente = ClienteTipo.valueOf(tipoCliente); }

    public ArrayList<Vehiculo> getVehiculos(){ return vehiculos; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return this.getIdPersona().equals(cliente.getIdPersona());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getIdPersona());
    }

    public static ArrayList<Cliente> obtenerClientes(){
        ArrayList<Cliente> lista = new ArrayList<>();
        Cliente c1 = new Cliente("0919292831", "Luis Pérez", "099111222", "Guayaquil", "Personal");
        Cliente c2 = new Cliente("0920292831", "Ana Torres", "099333444", "Quito", "Empresarial");
        Cliente c3 = new Cliente("0919212831", "Carlos Mina", "099555666", "Cuenca", "Personal");
        Cliente c4 = new Cliente("0919292131", "Julia Herrera", "099777888", "Manta", "Empresarial");

        Vehiculo v1 = new Vehiculo("ABC123", "Automóvil");
        Vehiculo v2 = new Vehiculo("XYZ789", "Motocicleta");
        Vehiculo v3 = new Vehiculo("MNO456", "Bus");
        Vehiculo v4 = new Vehiculo("JKL321", "Bus");

        c1.agregarVehiculo(v1);
        c2.agregarVehiculo(v2);
        c3.agregarVehiculo(v3);
        c4.agregarVehiculo(v4);

        lista.add(c1);
        lista.add(c2);
        lista.add(c3);
        lista.add(c4);

        return lista;
    }

    public static ArrayList<Cliente> cargarEmpleados(File directorio){
        ArrayList<Cliente> lista = new ArrayList<>();
        File f = new File(directorio, nomArchivoCliente);
        //se escribe la lista serializada
        if ( f.exists()) { //si no existe se crea la lista
            try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(f))) {
                lista = (ArrayList<Cliente>) is.readObject(); //establecer el ultimo id

            } catch (Exception e) {
                //quizas lanzar una excepcion personalizada
                new Exception(e.getMessage());
            }
        }
        return lista;
    }
    public static boolean crearDatosIniciales(File directorio) throws Exception{
        ArrayList<Cliente> lista = new ArrayList<>();
        boolean guardado = false;
        Cliente c1 = new Cliente("0919292831", "Luis Pérez", "099111222", "Guayaquil", "Personal");
        Cliente c2 = new Cliente("0920292831", "Ana Torres", "099333444", "Quito", "Empresarial");
        Cliente c3 = new Cliente("0919212831", "Carlos Mina", "099555666", "Cuenca", "Personal");
        Cliente c4 = new Cliente("0919292131", "Julia Herrera", "099777888", "Manta", "Empresarial");

        Vehiculo v1 = new Vehiculo("ABC123", "Automóvil");
        Vehiculo v2 = new Vehiculo("XYZ789", "Motocicleta");
        Vehiculo v3 = new Vehiculo("MNO456", "Bus");
        Vehiculo v4 = new Vehiculo("JKL321", "Bus");

        c1.agregarVehiculo(v1);
        c2.agregarVehiculo(v2);
        c3.agregarVehiculo(v3);
        c4.agregarVehiculo(v4);

        File f = new File(directorio, nomArchivoCliente);
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

    public static boolean guardarLista(File directorio,ArrayList<Cliente> lista) throws Exception{
        boolean guardado = false;
        File f = new File(directorio, nomArchivoCliente);
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(f))) {
            os.writeObject(lista);
            guardado = true;
        } catch (IOException e) {
            throw new Exception(e.getMessage());
        }
        return guardado;
    }


    public void agregarVehiculo(Vehiculo v){
        vehiculos.add(v);
    }

    @Override
    public String toString(){ return super.toString() + dirCliente + "-" + tipoCliente + "-"; }
}
