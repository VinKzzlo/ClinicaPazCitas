
package pe.edu.pucp.pazcitas.usuario.model;

import pe.edu.pucp.pazcitas.ubicacion.model.Sede;

public class AsistenteMedico extends Usuario{
    private String codigoPersonal;
    private boolean activo;
    private Medico medico;
    private Sede sede;
    

    public AsistenteMedico() {
    }

    public String getCodigoPersonal() {
        return codigoPersonal;
    }

    public void setCodigoPersonal(String codigoPersonal) {
        this.codigoPersonal = codigoPersonal;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    
    
    
}

