package espol.poo.proyectopoog5.modelo;

public class Vehiculo {
    private String numPlaca;
    private VehiculoTipo tipoVehiculo;
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
