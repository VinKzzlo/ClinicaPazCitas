package pe.edu.pucp.pazcitas.atencion.model;

import java.util.Date;
import java.util.ArrayList;

public class Receta {
    private int idReceta;
    private String indicaciones;
    private Date fechaPrescripcion;
    private ArrayList<LineaRecetaMedicamento> lineasReceta;
    private boolean activo;
    
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

    public Date getFechaPrescripcion() {
        return fechaPrescripcion;
    }

    public void setFechaPrescripcion(Date fechaPrescripcion) {
        this.fechaPrescripcion = fechaPrescripcion;
    }

    public ArrayList<LineaRecetaMedicamento> getLineasReceta() {
        return lineasReceta;
    }

    public void setLineasReceta(ArrayList<LineaRecetaMedicamento> lineasReceta) {
        this.lineasReceta = lineasReceta;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    
}
