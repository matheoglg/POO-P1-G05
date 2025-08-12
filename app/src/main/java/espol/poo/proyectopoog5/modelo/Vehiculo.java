package espol.poo.proyectopoog5.modelo;

import java.io.Serializable;

public class Vehiculo implements Serializable {
    private String numPlaca;
    private VehiculoTipo tipoVehiculo;

    private static final long serialVersionUID = 1L;

    public Vehiculo(){
    }
    public Vehiculo(String placa, String tipoVehiculo){
        this.numPlaca = placa;
        this.tipoVehiculo = VehiculoTipo.valueOf(tipoVehiculo);
    }
    public String getNumPlaca(){ return numPlaca; }
    public void setNumPlaca(String numPlaca){ this.numPlaca = numPlaca; }
    public VehiculoTipo getTipoVehiculo(){ return tipoVehiculo; }
}
