package pe.edu.pucp.pazcitas.usuario.model;

import java.util.ArrayList;
import pe.edu.pucp.pazcitas.atencion.model.HistorialMedico;
import pe.edu.pucp.pazcitas.cita.model.Cita;
import pe.edu.pucp.pazcitas.financiero.model.Seguro;

public class Paciente extends Usuario{
    private String direccion;
    private String telefono;
    private boolean activo;
    private ArrayList<Cita> citas;
    private Seguro seguro;
    private HistorialMedico historialMedico;

    public Paciente() {
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public ArrayList<Cita> getCitas() {
        return citas;
    }

    public void setCitas(ArrayList<Cita> citas) {
        this.citas = citas;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    public HistorialMedico getHistorialMedico() {
        return historialMedico;
    }

    public void setHistorialMedico(HistorialMedico historialMedico) {
        this.historialMedico = historialMedico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}