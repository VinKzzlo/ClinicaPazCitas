
package pe.edu.pucp.pazCitas.usuario.model;

import java.util.ArrayList;
import pe.edu.pucp.pazCitas.financiero.model.Pago;
import pe.edu.pucp.pazCitas.ubicacion.model.Sede;

public class Administrador extends Usuario{
    private boolean activo;
    private Sede sede;
    

    public Administrador() {
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

    
    
    
}
