
package pe.edu.pucp.pazCitas.usuario.model;

import java.util.ArrayList;
import pe.edu.pucp.pazCitas.cita.model.Cita;
import pe.edu.pucp.pazCitas.ubicacion.model.Sede;


public class Recepcionista extends Usuario {
    private boolean activo;
    private Sede sede;
    private ArrayList<Cita> citas;

    public Recepcionista() {
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public ArrayList<Cita> getCitas() {
        return citas;
    }

    public void setCitas(ArrayList<Cita> citas) {
        this.citas = citas;
    }
    
    
}
