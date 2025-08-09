package espol.poo.proyectopoog5.modelo;

import java.io.Serializable;

public abstract class Persona implements Serializable {
    private String idPersona;
    private String nombrePersona;
    private String telfPersona;

    public Persona() {
    }

    public Persona(String idPersona, String nombrePersona, String telfPersona) {
        this.idPersona = idPersona;
        this.nombrePersona = nombrePersona;
        this.telfPersona = telfPersona;
    }

    public String getIdPersona() { return idPersona; }

    public void setIdPersona(String idPersona) { this.idPersona = idPersona;}

    public String getNombrePersona() { return nombrePersona; }

    public void setNombrePersona(String nombrePersona) { this.nombrePersona = nombrePersona; }

    public String getTelfPersona() { return telfPersona; }

    public void setTelfPersona(String telfPersona) { this.telfPersona = telfPersona; }

    @Override
    public String toString(){ return idPersona + "-" + nombrePersona + "-" + telfPersona + "-"; }

}
