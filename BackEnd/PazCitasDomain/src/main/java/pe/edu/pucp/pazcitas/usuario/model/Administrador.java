
package pe.edu.pucp.pazcitas.usuario.model;


public class Administrador extends Usuario{
    
    private boolean activo;
    
    public Administrador() {
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }


}