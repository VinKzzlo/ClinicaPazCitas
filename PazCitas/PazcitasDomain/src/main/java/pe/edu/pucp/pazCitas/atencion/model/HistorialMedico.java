
package pe.edu.pucp.pazCitas.atencion.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.pazCitas.usuario.model.Paciente;

/**
 *
 * @author Joel
 */
public class HistorialMedico {
    private int idhistorial;
    private LocalDate fechaActualizacion;
    private boolean activo;
    private ArrayList<NotaClinica> notasClinicas;
    private Paciente paciente;

    public HistorialMedico() {
    }

    public int getIdhistorial() {
        return idhistorial;
    }

    public void setIdhistorial(int idhistorial) {
        this.idhistorial = idhistorial;
    }

    
    public LocalDate getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDate fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public ArrayList<NotaClinica> getNotasClinicas() {
        return notasClinicas;
    }

    public void setNotasClinicas(ArrayList<NotaClinica> notasClinicas) {
        this.notasClinicas = notasClinicas;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    
}
