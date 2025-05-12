
package pe.edu.pucp.pazCitas.atencion.model;

import java.time.LocalDate;
import java.util.ArrayList;


public class Receta {
    private int idReceta;
    private String indicaciones;
    private LocalDate fechaPrescripcion;
    private ArrayList<Medicamento> medicamentos;

    public Receta() {
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public ArrayList<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(ArrayList<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public LocalDate getFfechaPrescripcion() {
        return fechaPrescripcion;
    }

    public void setFechaPrescripcion(LocalDate fechaPreinscripcion) {
        this.fechaPrescripcion = fechaPreinscripcion;
    }
    
    
}
